package com.core.mensalidade;

import com.core.aluno.AlunoService;
import com.core.exception.BusinessException;
import com.core.mensalidade.dto.MensalidadeResponse;
import com.core.model.Mensalidade;
import com.core.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MensalidadeService {

    private final String OBJECT_NOTFOUND = "Aluno nao encontrado.";

    private MensalidadeRepository repository;

    private AlunoService alunoRepository;

    public void save(Mensalidade mensalidade) {
        mensalidade.setAno(DateUtil.getYear(mensalidade.getCreatedDate()));
        mensalidade.setMes(DateUtil.getMoth(mensalidade.getCreatedDate()));
        repository.save(mensalidade);
    }

    public Page<MensalidadeResponse> findAllAno(int ano, int page, int size)
    {
        var dateNow = LocalDate.now();
        var anoAtual = DateUtil.getYear(dateNow);
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "ano");
        if(ano > 0){
            anoAtual = ano;
        }
        var list = repository.findAll(anoAtual,  pageRequest);
        List<MensalidadeResponse> response = list.getContent().stream().map(m -> {
            return MensalidadeResponse.builder()
                    .mensalidade(m)
                    .aluno(alunoRepository.findById(m.getIdAluno()))
                    .build();
        }).collect(Collectors.toList());

        return new PageImpl<>(response, pageRequest, response.size());
    }

    public Page<MensalidadeResponse> findAllByMes(int mes, int ano, Mensalidade.Pagamento.Status status, int page, int size)
    {
        var dateNow = LocalDate.now();
        var anoAtual = DateUtil.getYear(dateNow);
        var mesAtual = DateUtil.getMoth(dateNow);

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.DESC,
                "ano");
        if(ano > 0){
            anoAtual = ano;
        }
        if(mes > 0){
            mesAtual = mes;
        }

        var list = repository.findAllByMes(anoAtual, mesAtual, pageRequest);

        List<MensalidadeResponse> response = list.getContent().stream().map(m -> {
            return MensalidadeResponse.builder()
                    .mensalidade(m)
                    .aluno(alunoRepository.findById(m.getIdAluno()))
                    .build();
        }).collect(Collectors.toList());

        return new PageImpl<>(response, pageRequest, response.size());
    }

    public void pay(String id, String comentario) {
        var mensalidade = this.findById(id);
        var pagamento = mensalidade.getPagamento();
        pagamento.setStatus(Mensalidade.Pagamento.Status.PAGO);
        pagamento.setDataPagamento(LocalDate.now());
        mensalidade.setPagamento(pagamento);
        mensalidade.setComentario(comentario);
        repository.save(mensalidade);
    }

    public Mensalidade findById(String id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> new BusinessException(OBJECT_NOTFOUND));
    }
}
