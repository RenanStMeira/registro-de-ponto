package com.ponto.registro.Models;

import javax.persistence.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RelatorioDeHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate data;
    private int horasTrabalhadas;
}