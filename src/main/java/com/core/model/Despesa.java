package com.core.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@Builder
public class Despesa {
    @Id
    private String id;
    private String descricao;
    private BigDecimal valor;
    private int mes;
    private LocalDateTime createdDate;

}
