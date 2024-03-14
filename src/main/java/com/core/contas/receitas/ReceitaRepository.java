package com.core.contas.receitas;

import com.core.model.Receita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends MongoRepository<Receita, String>  {
    List<Receita> findByMes(int mes);
}
