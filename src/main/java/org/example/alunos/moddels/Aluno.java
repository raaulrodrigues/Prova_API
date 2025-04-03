package org.example.alunos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "alunos")
public class Aluno {
    @Id
    private String id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @Email(message = "Email deve ser válido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;
}