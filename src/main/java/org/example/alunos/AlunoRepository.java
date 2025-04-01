package org.example.alunos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.example.alunos.Aluno;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {
}
