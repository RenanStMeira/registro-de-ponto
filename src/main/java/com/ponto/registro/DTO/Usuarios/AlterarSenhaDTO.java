package com.ponto.registro.DTO.Usuarios;

import lombok.Data;

@Data
public class AlterarSenhaDTO {
    private Long idUsuario;
    private String senhaAtual;
    private String novaSenha;

}