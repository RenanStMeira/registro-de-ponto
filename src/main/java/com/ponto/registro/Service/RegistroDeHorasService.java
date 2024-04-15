package com.ponto.registro.Service;

import com.ponto.registro.DTO.RegistroDeHoras.RegistroDeHorasDTO;
import com.ponto.registro.Models.RegistroDeHoras;
import com.ponto.registro.Models.Usuario;
import com.ponto.registro.Repository.RegistroDeHorasRepository;
import com.ponto.registro.Repository.UsuarioRepository;
import com.ponto.registro.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class RegistroDeHorasService {

    private final RegistroDeHorasRepository registroDeHorasRepository;
    private final UsuarioRepository usuarioRepository;

    public RegistroDeHorasDTO iniciarRegistro(Long usuarioId) throws RegraDeNegocioException {
        RegistroDeHoras registro = new RegistroDeHoras();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RegraDeNegocioException("Usuário não encontrado"));
        registro.setUsuario(usuario);
        registro.setDataHoraEntrada(LocalDateTime.now());
        RegistroDeHoras savedRegistro = registroDeHorasRepository.save(registro);
        return toDTO(savedRegistro);
    }

    public RegistroDeHorasDTO finalizarRegistro(Long id) throws RegraDeNegocioException {
        RegistroDeHoras registro = registroDeHorasRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Registro não encontrado"));
        registro.setDataHoraSaida(LocalDateTime.now());
        RegistroDeHoras updatedRegistro = registroDeHorasRepository.save(registro);
        return toDTO(updatedRegistro);
    }

    public List<RegistroDeHorasDTO> buscarTodosRegistros() {
        List<RegistroDeHoras> registros = registroDeHorasRepository.findAll();
        return registros.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RegistroDeHorasDTO buscarRegistroPorId(Long id) throws RegraDeNegocioException {
        RegistroDeHoras registro = registroDeHorasRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Registro não encontrado"));
        return toDTO(registro);
    }

    public RegistroDeHorasDTO atualizarRegistro(RegistroDeHorasDTO registroDTO) throws RegraDeNegocioException {
        RegistroDeHoras registro = registroDeHorasRepository.findById(registroDTO.getId())
                .orElseThrow(() -> new RegraDeNegocioException("Registro não encontrado"));
        registro.setDataHoraEntrada(registroDTO.getDataHoraEntrada());
        registro.setDataHoraSaida(registroDTO.getDataHoraSaida());
        RegistroDeHoras updatedRegistro = registroDeHorasRepository.save(registro);
        return toDTO(updatedRegistro);
    }

    public void deletarRegistro(Long id) {
        registroDeHorasRepository.deleteById(id);
    }

    private RegistroDeHorasDTO toDTO(RegistroDeHoras registro) {
        RegistroDeHorasDTO dto = new RegistroDeHorasDTO();
        dto.setId(registro.getId());
        dto.setUsuarioId(registro.getUsuario().getIdUsuario());
        dto.setDataHoraEntrada(registro.getDataHoraEntrada());
        dto.setDataHoraSaida(registro.getDataHoraSaida());
        return dto;
    }

    private RegistroDeHoras toEntity(RegistroDeHorasDTO dto) {
        RegistroDeHoras registro = new RegistroDeHoras();
        registro.setId(dto.getId());
        registro.setDataHoraEntrada(dto.getDataHoraEntrada());
        registro.setDataHoraSaida(dto.getDataHoraSaida());
        return registro;
    }
}
