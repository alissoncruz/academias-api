package com.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("aluno")
public class Aluno {

    @Id
    private String id;

    @NotBlank(message = "Campo Nome nao pode ser vazio")
    private String nome;
    @NotBlank(message = "Campo CPF nao pode ser vazio")
    private String cpf;
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    private LocalDate dataInicio;

    private int anoNascimento;
    private Double peso;
    private Double altura;

    private Graduacao graduacao;

    private Endereco endereco;

    private Responsavel responsavel;

    /** doc
     * Termo de aceite para alunos que precisam assinar o laudo de doenca
     */
    private Boolean termoAceite;
    private Boolean status;

}
