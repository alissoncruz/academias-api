package com.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("aula")
public class Aula {
    @Id
    private String id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dia;
    private String horario;
    private String professor;
    private String observacao;
    private TipoAula tipoAula;
    private LocalDate createdDate;

    /**
        Lista de ids dos alunos
     */
    private List<String> listaPresenca;

    @Getter
    @AllArgsConstructor
    public enum TipoAula {

        JIUJTSU("JIUJTSU", "Jiu-jitsu"),
        MUAYTHAI("MUAYTHAI", "MuayThai");
        private final String id;
        private final String description;
    }
}
