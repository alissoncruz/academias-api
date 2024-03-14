package com.core.mensalidade;

import com.core.model.Mensalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MensalidadeRepository extends MongoRepository<Mensalidade, String> {
    @Query(value="{'ano': ?0}")
    Page<Mensalidade> findAll(int ano, Pageable pageable);

    @Query(value="{'mes': ?0 && 'ano': ?0}")
    Page<Mensalidade> findAllByMes( int ano, int mes, Pageable pageable);
}
