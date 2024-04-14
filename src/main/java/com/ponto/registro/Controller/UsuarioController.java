package com.ponto.registro.Controller;

import com.ponto.registro.DTO.UsuarioRequestDTO;
import com.ponto.registro.DTO.UsuarioResponseDTO;
import com.ponto.registro.Models.Cargo;
import com.ponto.registro.Service.UsuarioService;
import com.ponto.registro.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "usuario", description = "Endpoint Para Usuario")
@RequestMapping("/usuario")public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios() throws RegraDeNegocioException {
        List<UsuarioResponseDTO> usuarios = usuarioService.buscarTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(
            @PathVariable Long id) throws RegraDeNegocioException {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO)
            throws RegraDeNegocioException {
        UsuarioResponseDTO usuarioCriado = usuarioService.criarUsuario(usuarioRequestDTO);
        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }
}
