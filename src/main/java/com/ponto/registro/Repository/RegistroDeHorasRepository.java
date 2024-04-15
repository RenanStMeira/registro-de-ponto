package com.ponto.registro.Repository;

import com.ponto.registro.Models.RegistroDeHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDeHorasRepository extends JpaRepository<RegistroDeHoras, Long> {
}
