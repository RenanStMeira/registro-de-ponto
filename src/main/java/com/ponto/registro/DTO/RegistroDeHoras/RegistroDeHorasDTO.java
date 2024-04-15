package com.ponto.registro.DTO.RegistroDeHoras;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroDeHorasDTO {
    private Long id;
    private Long usuarioId;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
}