package com.tcc.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PacienteCriticidadeUpdateDto {
    @JsonProperty
    private int criticidade;
}
