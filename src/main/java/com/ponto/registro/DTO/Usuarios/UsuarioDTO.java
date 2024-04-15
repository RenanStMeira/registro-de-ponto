package com.ponto.registro.DTO.Usuarios;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String email;
    private String cpf;
    private String nome;
    private Long idCargo;
}
