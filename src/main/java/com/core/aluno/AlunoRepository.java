package com.core.aluno;


import com.core.model.Aluno;
import com.core.model.Mensalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {

    Page<Aluno> findAll( Pageable pageable);
}
