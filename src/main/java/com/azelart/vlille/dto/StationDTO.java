package com.azelart.vlille.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Weather DTO.
 * @author Corentin Azelart
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StationDTO implements Serializable {

    /**
     * IDx.
     */
    private Integer idx;

    /**
     * Number of bikes.
     */
    private Short bikes;

    /**
     * Number.
     */
    private Short number;

    /**
     * Free bikes.
     */
    private Short free;

    /**
     * Name.
     */
    private String name;

    /**
     * Latitude.
     */
    private Integer latitude;

    /**
     * Longitude.
     */
    private Integer longitude;
}
