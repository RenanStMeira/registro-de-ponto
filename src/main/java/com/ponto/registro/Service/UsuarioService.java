package com.ponto.registro.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ponto.registro.DTO.UsuarioDTO;
import com.ponto.registro.DTO.UsuarioRequestDTO;
import com.ponto.registro.DTO.UsuarioResponseDTO;
import com.ponto.registro.Models.Cargo;
import com.ponto.registro.Models.Usuario;
import com.ponto.registro.Repository.CargoRepository;
import com.ponto.registro.Repository.UsuarioRepository;
import com.ponto.registro.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final ObjectMapper objectMapper;

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return Optional.empty();
    }

    public List<UsuarioResponseDTO> buscarTodosUsuarios() throws RegraDeNegocioException {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new RegraDeNegocioException("Nenhum usuário encontrado");
        }
        return objectMapper.convertValue(usuarios, List.class);
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
        return toDTO(usuario);
    }

    public UsuarioResponseDTO buscarUsuarioPorCpf(String cpf) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
        return toDTO(usuario);
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) throws RegraDeNegocioException {
        verificarEmailExistete(usuarioRequestDTO.getEmail());
        validarDadosUsuario(usuarioRequestDTO);

        Cargo cargo = cargoRepository.findById(usuarioRequestDTO.getIdCargo())
                .orElseThrow(() -> new RegraDeNegocioException("Cargo não encontrado"));

        Usuario usuario = toEntity(usuarioRequestDTO);
        usuario.setCargo(cargo);

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return toDTO(savedUsuario);
    }

    public UsuarioResponseDTO atualizarUsuario(UsuarioDTO usuarioDTO) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setCpf(usuarioDTO.getCpf());

        Cargo cargo = cargoRepository.findById(usuarioDTO.getIdCargo())
                .orElseThrow(() -> new RegraDeNegocioException("Cargo não encontrado"));
        usuario.setCargo(cargo);

        Usuario savedUsuario = usuarioRepository.save(usuario);
        return toDTO(savedUsuario);
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
        dto.setIdCargo(usuario.getCargo().getIdCargo());
        return dto;
    }

    private Usuario toEntity(UsuarioRequestDTO dto) throws RegraDeNegocioException {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCpf(dto.getCpf());

        Cargo cargo = cargoRepository.findById(dto.getIdCargo())
                .orElseThrow(() -> new RegraDeNegocioException("Cargo não encontrado"));
        usuario.setCargo(cargo);

        return usuario;
    }
}
