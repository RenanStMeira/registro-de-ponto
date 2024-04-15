package com.ponto.registro.DTO.RelatorioDeHoras;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class RelatorioDTO {
    private Long id;
    private Long usuarioId;
    private LocalDate data;
    private Duration minutosTrabalhados;
}
