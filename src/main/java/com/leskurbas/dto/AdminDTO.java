package com.leskurbas.dto;

import java.util.List;

public class AdminDTO extends BaseUserDTO {
    public List<PlaysDTO> plays;

    public List<PlaysDTO> getPlays() {
        return plays;
    }

    public void setPlays(List<PlaysDTO> plays) {
        this.plays = plays;
    }
}
