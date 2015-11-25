package com.azelart.vlille.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Station response DTO.
 * @author Corentin Azelart
 */
@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StationResponseDTO {

    /**
     * We have perform a cache access.
     */
    private Boolean cache;

    /**
     * Stations.
     */
    private List<StationDTO> stations;

    /**
     * Time in MS.
     */
    private Long time;
}
