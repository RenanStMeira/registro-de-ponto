package com.ponto.registro.Service;

import com.ponto.registro.DTO.RelatorioDeHoras.RelatorioDTO;
import com.ponto.registro.Models.RelatorioDeHoras;

import com.ponto.registro.Repository.RelatorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RelatorioService {

    private final RelatorioRepository relatorioDeHorasRepository;

    public List<RelatorioDTO> buscarTodosRelatorios() {
        List<RelatorioDeHoras> relatorios = relatorioDeHorasRepository.findAll();
        return relatorios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RelatorioDTO buscarPorId(Long id) {
        System.out.println("Buscando relatório com o ID: " + id);
        RelatorioDeHoras relatorio = relatorioDeHorasRepository.findById(id)
                .orElseThrow(() -> {
                    System.out.println("Nenhum relatório encontrado com o ID: " + id);
                    return new RuntimeException("Relatório não encontrado com o ID: " + id);
                });
        return toDTO(relatorio);
    }

    private RelatorioDTO toDTO(RelatorioDeHoras relatorio) {
        RelatorioDTO dto = new RelatorioDTO();
        dto.setId(relatorio.getId());
        dto.setUsuarioId(relatorio.getUsuario().getIdUsuario());
        dto.setData(relatorio.getData());
        dto.setMinutosTrabalhados(relatorio.getMinutosTrabalhados());
        return dto;
    }
}