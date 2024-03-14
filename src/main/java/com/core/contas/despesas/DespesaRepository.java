package com.core.contas.despesas;

import com.core.model.Despesa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends MongoRepository<Despesa, String>  {

    List<Despesa> findByMes(int mes);
}
