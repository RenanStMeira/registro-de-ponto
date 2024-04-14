package com.ponto.registro.Models;

import lombok.*;
import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "senha")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @OneToMany(mappedBy = "usuario")
    private List<RegistroDeHoras> registrosDeHoras;

    @OneToMany(mappedBy = "usuario")
    private List<RelatorioDeHoras> relatoriosDeHoras;


}
