package com.ponto.registro.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ponto.registro.DTO.UsuarioRequestDTO;
import com.ponto.registro.DTO.UsuarioResponseDTO;
import com.ponto.registro.Models.Cargo;
import com.ponto.registro.Models.Usuario;
import com.ponto.registro.Repository.UsuarioRepository;
import com.ponto.registro.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return Optional.empty();
    }

    public Usuario CriarUsuario(UsuarioRequestDTO usuarioRequestDTO, Cargo cargo) throws RegraDeNegocioException {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        usuario.setCargo(cargo);

        return usuarioRepository.save(usuario);
    }

    private void validarDadosUsuario(UsuarioRequestDTO usuario) throws RegraDeNegocioException {
        if (usuario.getEmail() == null || usuario.getSenha() == null) {
            throw new RegraDeNegocioException("Email e senha são obrigatórios");
        }
    }

    private void verificarEmailExistete(String email) throws RegraDeNegocioException {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RegraDeNegocioException("Já existe um usuário cadastrado com este email");
        }
    }

    private UsuarioResponseDTO toDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setCargo(usuario.getCargo().getAuthority());
        return dto;
    }

    private Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCpf(dto.getCpf());
        usuario.setCargo(dto.getCargo());
        return usuario;
    }
}
