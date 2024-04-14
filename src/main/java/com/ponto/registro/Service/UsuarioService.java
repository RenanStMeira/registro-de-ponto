package com.ponto.registro.Service;

import com.ponto.registro.Models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return Optional.empty();
    }
}
