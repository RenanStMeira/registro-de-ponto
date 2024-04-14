package com.ponto.registro.Models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARGOS")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCargo;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "cargo")
    private List<Usuario> usuarios;


    public String getAuthority() {
        return tipo;
    }

}