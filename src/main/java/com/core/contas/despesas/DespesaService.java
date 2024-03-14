package com.core.contas.despesas;

import com.core.exception.BusinessException;
import com.core.exception.NotFoundException;
import com.core.model.Despesa;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DespesaService {

    private DespesaRepository repository;

    public void save(Despesa despesa) {
        repository.save(despesa);
    }

    public List<Despesa> findAll() {
        return repository.findAll();
    }

    public Despesa findById(String id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException("Despesa nao encontrada"));
    }

    public List<Despesa> findByMes(int mes){
        return repository.findByMes(mes);
    }

}
