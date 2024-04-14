package com.ponto.registro.DTO;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private String nome;
    private String email;
    private String cpf;
    private String cargo;
}
