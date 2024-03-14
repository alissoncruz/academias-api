package com.core.aluno;

import com.core.exception.BusinessException;
import com.core.model.Aluno;
import com.core.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final String OBJECT_NOTFOUND = "Aluno nao encontrado.";

    @Transactional
    public void save(Aluno aluno) {
        validateAluno(aluno);
        repository.save(aluno);
    }

    private void validateAluno(Aluno aluno) {
        if (Objects.isNull(aluno.getCpf())) throw new BusinessException("Campo CPF não pode ser null");
        if (Objects.isNull(aluno.getNome())) throw new BusinessException("Campo nome não pode ser null");
        if (Objects.isNull(aluno.getDataNascimento())) throw new BusinessException("Campo dataNascimento não pode ser null");
        //if(DateUtil.getAge(aluno.getDataNascimento()) < 18) throw new BusinessException("Por favor, informar o responsavel");
    }

    @Transactional
    public void update(String id, Aluno aluno) {
        Aluno alunoFound = findById(id);
        var alunoBuilder = fillAluno(aluno, alunoFound);
        repository.save(alunoBuilder);
    }

    private static Aluno fillAluno(Aluno aluno, Aluno alunoFound) {
        return Aluno.builder()
                .id(alunoFound.getId())
                .cpf(Objects.isNull(aluno.getCpf()) ? alunoFound.getCpf() : aluno.getCpf())
                .altura(Objects.isNull(aluno.getAltura()) ? alunoFound.getAltura() : aluno.getAltura())
                .nome(Objects.isNull(aluno.getNome()) ? alunoFound.getNome() : aluno.getNome())
                .peso(Objects.isNull(aluno.getPeso()) ? alunoFound.getPeso() : aluno.getPeso())
                .anoNascimento(Objects.isNull(aluno.getAnoNascimento()) ? alunoFound.getAnoNascimento() : aluno.getAnoNascimento())
                .dataInicio(Objects.isNull(aluno.getDataInicio()) ? alunoFound.getDataInicio() : aluno.getDataInicio())
                .dataNascimento(Objects.isNull(aluno.getDataNascimento()) ? alunoFound.getDataNascimento() : aluno.getDataNascimento())
                .endereco(Objects.isNull(aluno.getEndereco()) ? alunoFound.getEndereco() : aluno.getEndereco())
                .graduacao(Objects.isNull(aluno.getGraduacao()) ? alunoFound.getGraduacao() : aluno.getGraduacao())
                .responsavel(Objects.isNull(aluno.getResponsavel()) ? alunoFound.getResponsavel() : aluno.getResponsavel())
                .termoAceite(Objects.isNull(aluno.getTermoAceite()) ? alunoFound.getTermoAceite() : aluno.getTermoAceite())
                .status(Objects.isNull(aluno.getStatus()) ? alunoFound.getStatus() : aluno.getStatus())
                .build();
    }

    @Transactional
    public void delete(String id) {
        var aluno = findById(id);
        repository.delete(aluno);
    }

    public Aluno findById(String id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> new BusinessException(OBJECT_NOTFOUND));
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }
    public Page<Aluno> findAllPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");
        return repository.findAll(pageRequest);
    }
}
