package com.tcc.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SalaUpdateDto {

    @JsonProperty
    private String especialidade;
    @JsonProperty
    private String responsavel;
}
