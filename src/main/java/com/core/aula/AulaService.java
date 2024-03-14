package com.core.aula;

import com.core.exception.BusinessException;
import com.core.model.Aula;
import com.core.model.Checkin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AulaService {

    private static final String OBJECT_NOTFOUND = "Aula nao encontrada";
    private AulaRepository repository;

    @Transactional
    public void criarAula(Aula aula) {
        aula.setCreatedDate(LocalDate.now());
        repository.save(aula);
    }

    @Transactional
    public void fazerCheckin(Checkin checkin) {
        Aula aula = findById(checkin.getIdAula());
        List<String> addAluno = aula.getListaPresenca();
        addAluno.add(checkin.getIdAluno());
        aula.setListaPresenca(addAluno);
        repository.save(aula);
    }

    public Aula findById(String id){
        return repository.findById(id).orElseThrow(() -> new BusinessException(OBJECT_NOTFOUND));
    }

    public List<Aula> getAll() {
        return repository.findAll();
    }
}
