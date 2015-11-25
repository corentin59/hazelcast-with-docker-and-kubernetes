package com.azelart.vlille.ws;

import com.azelart.vlille.dto.ServerDTO;
import com.azelart.vlille.services.VLilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Server WS.
 * @author Corentin Azelart
 */
@RestController
@RequestMapping("/api/server")
public class ServerWS {

    /**
     * VLille Service to access Hazelcast.
     */
    @Autowired
    private VLilleService vLilleService;

    /**
     * Perform a who.
     * @param httpServletRequest is the HTTP Servlet Request
     * @return a string with ip and port
     */
    @RequestMapping(method = RequestMethod.GET, value = "/who")
    public ServerDTO who(final HttpServletRequest httpServletRequest) {
        final ServerDTO server = vLilleService.getCacheStatus();
        server.setName(httpServletRequest.getServerName());
        server.setPort(httpServletRequest.getServerPort());
        return server;
    }


}
