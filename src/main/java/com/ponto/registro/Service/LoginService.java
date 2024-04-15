package com.ponto.registro.Service;

import com.ponto.registro.DTO.Auth.LoginDTO;
import com.ponto.registro.Models.Usuario;
import com.ponto.registro.Repository.UsuarioRepository;
import com.ponto.registro.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario login(LoginDTO loginDTO) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new RegraDeNegocioException("Senha incorreta");
        }

        return usuario;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
