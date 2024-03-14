package com.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Endereco {

    private Integer numero;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String cep;
}
