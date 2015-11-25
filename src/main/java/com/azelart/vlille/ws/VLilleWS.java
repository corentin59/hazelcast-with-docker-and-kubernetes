package com.azelart.vlille.ws;

import com.azelart.vlille.dto.StationResponseDTO;
import com.azelart.vlille.exception.SynchronisationException;
import com.azelart.vlille.services.VLilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * VLille WS.
 * @author Corentin Azelart
 */
@RestController
@RequestMapping("/api/stations")
public class VLilleWS {

    /**
     * VLille Service.
     */
    @Autowired
    private VLilleService vLilleService;

    /**
     * Return a list of stations.
     * @return a list of stations.
     */
    @RequestMapping(method = RequestMethod.GET)
    public StationResponseDTO findAll() throws SynchronisationException {
        final Long startTime = System.currentTimeMillis();
        final StationResponseDTO stationResponseDTO = vLilleService.findAll();
        stationResponseDTO.setTime(System.currentTimeMillis() - startTime);
        return stationResponseDTO;
    }
}
