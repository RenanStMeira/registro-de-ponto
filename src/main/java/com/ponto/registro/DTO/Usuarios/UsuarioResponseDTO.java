package com.ponto.registro.DTO.Usuarios;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private String nome;
    private String email;
    private String cpf;
    private String cargo;
    private Long idCargo;
}
