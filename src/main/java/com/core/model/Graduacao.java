package com.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Graduacao {
    private Faixa faixa;
    private String quantidadeGrau;

    @Getter
    @AllArgsConstructor
    public enum Faixa {

        BRANCA("BRANCA", "Branca"),
        AZUL("AZUL", "Azul"),
        ROXA("ROXA", "Roxa"),
        MARRON("MARROM", "Marrom"),
        PRETA("PRETA", "Preta"),

        AMARELA("PRETA", "Preta"),
        ZINZA("ZINZA", "Zinza");

        private final String id;
        private final String description;
    }
}
