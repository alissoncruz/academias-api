package com.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Funcionario {


    private String id;
    private String nome;
    private String cpf;
    private Contato contato;
}
