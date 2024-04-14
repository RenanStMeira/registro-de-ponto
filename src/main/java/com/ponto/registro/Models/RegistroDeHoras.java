package com.ponto.registro.Models;


import javax.persistence.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "REGISTRO_DE_HORAS")
public class RegistroDeHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;

}