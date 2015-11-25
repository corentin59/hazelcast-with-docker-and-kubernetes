package com.azelart.vlille.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Instance DTO.
 * @author Corentin Azelart
 */
@Getter
@Setter
public class InstanceDTO {

    /**
     * Instance id.
     */
    private String id;

    /**
     * Instance host.
     */
    private String host;

    /**
     * Local.
     */
    private Boolean local;

    /**
     * Config name.
     */
    private String name;
}
