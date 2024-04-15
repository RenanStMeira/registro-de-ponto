package com.ponto.registro.DTO.Usuarios;

import com.ponto.registro.Models.Cargo;
import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDTO {
    private Long idUsuario;
    private String email;
    private String cpf;
    private String nome;
    private Long idCargo;
}
