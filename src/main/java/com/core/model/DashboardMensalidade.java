package com.core.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardMensalidade {

    private int quantidadePagasMes;

    private int quantidadeAtrasadasMes;

}
