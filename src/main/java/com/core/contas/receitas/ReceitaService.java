package com.core.contas.receitas;

import com.core.model.Receita;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class ReceitaService {

    private ReceitaRepository repository;

    public void save(Receita receita) {
        repository.save(receita);
    }

    public List<Receita> findAll() {
        return repository.findAll();
    }

    public List<Receita> findByMes(final int mes) {
        return repository.findByMes(mes);
    }

}
