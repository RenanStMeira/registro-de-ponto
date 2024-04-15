package com.ponto.registro.Repository;

import com.ponto.registro.Models.RelatorioDeHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelatorioRepository extends JpaRepository<RelatorioDeHoras, Long> {
    Optional<RelatorioDeHoras> findByUsuarioIdUsuario(Long idUsuario);
}

