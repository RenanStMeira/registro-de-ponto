package com.ponto.registro.DTO;

import com.ponto.registro.Models.Cargo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Data
public class UsuarioRequestDTO {

    @NotNull
    @Schema(description = "Nome do usuário", example = "João", required = true)
    private String nome;

    @NotNull
    @Schema(description = "Email do usuário", example = "joao@enail.com")
    private String email;

    @NotNull
    @Schema(description = "Senha do usuário", example = "senha123", required = true)
    private String senha;

    @NotNull
    @Schema(description = "CPF do usuário", example = "123.456.789-00", required = true)
    @CPF
    private String cpf;

    @NotNull
    @Schema(description = "ID do cargo do usuário", example = "1", required = true)
    private Long idCargo;
}
