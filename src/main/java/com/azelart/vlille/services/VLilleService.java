package com.azelart.vlille.services;

import com.azelart.vlille.dto.InstanceDTO;
import com.azelart.vlille.dto.ServerDTO;
import com.azelart.vlille.dto.StationDTO;
import com.azelart.vlille.dto.StationResponseDTO;
import com.azelart.vlille.exception.SynchronisationException;
import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Weather service.
 * @author Corentin Azelart
 */
@Service
@PropertySource("classpath:application.properties")
public class VLilleService {

    /**
     * Inject WS Endpoint for VLille.
     */
    @Value("${vlille.ws}")
    private String villeWs;

    /**
     * The cache instance.
     */
    private HazelcastInstance hazelcastInstance;

    /**
     * Station MAP.
     */
    public final static String STATION_MAP = "STATION_MAP";

    /**
     * Init Hazelcast after Spring injection.
     */
    @PostConstruct
    public void init() {
        // 1. Create and configure new hazelcast instance
        this.hazelcastInstance = Hazelcast.getOrCreateHazelcastInstance(new ClasspathXmlConfig("hazelcast.xml"));
    }

    /**
     * Get hazelcast status.
     * @return a server DTO.
     */
    public ServerDTO getCacheStatus() {
        final ServerDTO serverDTO = new ServerDTO();
        final Set<Member> members = this.hazelcastInstance.getCluster().getMembers();
        final List<InstanceDTO> instances = new ArrayList<InstanceDTO>(members.size());
        for(final Member member : this.hazelcastInstance.getCluster().getMembers()) {
            final InstanceDTO instanceDTO = new InstanceDTO();
            instanceDTO.setId(member.getUuid());
            instanceDTO.setHost(member.getInetSocketAddress().getHostString());
            instanceDTO.setLocal(member.localMember());
            instanceDTO.setName(this.hazelcastInstance.getConfig().getGroupConfig().getName());
            instances.add(instanceDTO);
        }
        serverDTO.setInstances(instances);
        serverDTO.setHazelcastName(this.hazelcastInstance.getConfig().getNetworkConfig().getPublicAddress());
        serverDTO.setHazelcastPort(this.hazelcastInstance.getConfig().getNetworkConfig().getPort());

        return serverDTO;
    }


    /**
     * Find all stations status.
     * @return a list of stations.
     * @throws SynchronisationException if we can't synchronise the station list with remote API.
     */
    public StationResponseDTO findAll() throws SynchronisationException {
        final StationResponseDTO stationResponseDTO = new StationResponseDTO();

        // Check if we have stations in the map...
        final IMap<Integer, StationDTO> stations = this.hazelcastInstance.getMap(STATION_MAP);

        if(stations.size() == 0) {
            final List<StationDTO> loadedStations = this.performSynchronisation();

            // We add a station in map for each entry
            for(final StationDTO station : loadedStations) {
                stations.put(station.getIdx(), station);
            }

            stationResponseDTO.setCache(Boolean.FALSE);
            stationResponseDTO.setStations(loadedStations);
        } else {
            stationResponseDTO.setCache(Boolean.TRUE);
            stationResponseDTO.setStations(new ArrayList<StationDTO>(stations.values()));
        }

        return stationResponseDTO;
    }

    /**
     * Perform a synchronisation with CityBik API
     * @return a list of stations.
     * @throws SynchronisationException if we can't synchronise the station list with remote API.
     */
    public List<StationDTO> performSynchronisation() throws SynchronisationException {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<StationDTO>> parameterizedTypeReference = new ParameterizedTypeReference<List<StationDTO>>() {};
        final ResponseEntity<List<StationDTO>> response = restTemplate.exchange(villeWs, HttpMethod.GET, null, parameterizedTypeReference);
        return response.getBody();
    }
}
