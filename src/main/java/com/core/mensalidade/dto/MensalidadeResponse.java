package com.core.mensalidade.dto;

import com.core.model.Aluno;
import com.core.model.Mensalidade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class MensalidadeResponse {

    private Mensalidade mensalidade;
    private Aluno aluno;

}
