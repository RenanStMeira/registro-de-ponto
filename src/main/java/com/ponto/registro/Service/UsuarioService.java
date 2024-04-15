package com.ponto.registro.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ponto.registro.DTO.Usuarios.AlterarSenhaDTO;
import com.ponto.registro.DTO.Usuarios.UsuarioDTO;
import com.ponto.registro.DTO.Usuarios.UsuarioRequestDTO;
import com.ponto.registro.DTO.Usuarios.UsuarioResponseDTO;
import com.ponto.registro.Models.Cargo;
import com.ponto.registro.Models.Usuario;
import com.ponto.registro.Repository.CargoRepository;
import com.ponto.registro.Repository.UsuarioRepository;
import com.ponto.registro.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CargoRepository cargoRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

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

        String senhaCriptografada = passwordEncoder.encode(usuarioRequestDTO.getSenha());

        Cargo cargo = cargoRepository.findById(usuarioRequestDTO.getIdCargo())
                .orElseThrow(() -> new RegraDeNegocioException("Cargo não encontrado"));

        Usuario usuario = toEntity(usuarioRequestDTO);
        usuario.setSenha(senhaCriptografada);
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

    public void alterarSenha(AlterarSenhaDTO alterarSenhaDTO) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findById(alterarSenhaDTO.getIdUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));

        if (!passwordEncoder.matches(alterarSenhaDTO.getSenhaAtual(), usuario.getSenha())) {
            throw new RegraDeNegocioException("Senha atual incorreta");
        }

        String novaSenhaCriptografada = passwordEncoder.encode(alterarSenhaDTO.getNovaSenha());
        usuario.setSenha(novaSenhaCriptografada);

        usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
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
