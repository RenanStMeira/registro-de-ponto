package com.ponto.registro.security;

import com.ponto.registro.Models.Usuario;
import com.ponto.registro.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorEmail(email);
        return (UserDetails) usuario
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inválido"));
    }
}
