package com.ponto.registro.Repository;

import com.ponto.registro.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    Usuario findByEmail(String email);
//
//    boolean existsByEmail(String email);
//
//    Optional<Usuario> findByCpf(String cpf);

    boolean existsByEmail(String email);
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);

}
