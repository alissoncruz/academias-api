package com.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Document
public class Mensalidade implements Serializable {

    @Id
    private String id;
    private String idAluno;
    private LocalDate createdDate;
    private int mes;
    private int ano;

    private String comentario;

    private Pagamento pagamento;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Document(collation = "pagamento")
    public static class Pagamento {
        private Status status;
        private BigDecimal valor;
        private LocalDate vencimento;
        private LocalDate dataPagamento;
        private String recibo;
        private String qrCodePix;
        @Getter
        @AllArgsConstructor
        public enum Status {
            EM_ABERTO ("EM_ABERTO", "Em aberto"),
            PROCESSANDO ("PROCESSANDO", "Processando"),
            PAGO ("PAGO", "Pago");
            private String id;
            private String descricao;

            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class VO {
                private String id;
                private String descricao;
            }
        }

        @Getter
        @AllArgsConstructor
        public enum FormaPagamento {
            PIX ("PIX", "Pix"),
            CREDITO ("CREDITO", "Crédito"),
            DEBITO ("DEBITO", "Débito"),
            ESPECIE ("ESPECIE", "Espécie");
            private String id;
            private String descricao;

            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class VO {
                private String id;
                private String descricao;
            }
        }
    }

}
