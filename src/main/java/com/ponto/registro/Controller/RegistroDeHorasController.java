package com.ponto.registro.Controller;

import com.ponto.registro.DTO.RegistroDeHoras.RegistroDeHorasDTO;
import com.ponto.registro.Service.RegistroDeHorasService;
import com.ponto.registro.exceptions.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
public class RegistroDeHorasController {

    private final RegistroDeHorasService registroDeHorasService;

    @PostMapping("/iniciar/{usuarioId}")
    public ResponseEntity<RegistroDeHorasDTO> iniciarRegistro(@PathVariable Long usuarioId) throws RegraDeNegocioException {
        RegistroDeHorasDTO savedRegistro = registroDeHorasService.iniciarRegistro(usuarioId);
        return ResponseEntity.ok(savedRegistro);
    }

    @GetMapping
    public ResponseEntity<List<RegistroDeHorasDTO>> buscarTodosRegistros() {
        List<RegistroDeHorasDTO> registros = registroDeHorasService.buscarTodosRegistros();
        return ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDeHorasDTO> buscarRegistroPorId(@PathVariable Long id) throws RegraDeNegocioException {
        RegistroDeHorasDTO registro = registroDeHorasService.buscarRegistroPorId(id);
        return ResponseEntity.ok(registro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroDeHorasDTO> atualizarRegistro(@PathVariable Long id, @RequestBody RegistroDeHorasDTO registroDTO) throws RegraDeNegocioException {
        registroDTO.setId(id);
        RegistroDeHorasDTO updatedRegistro = registroDeHorasService.atualizarRegistro(registroDTO);
        return ResponseEntity.ok(updatedRegistro);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<RegistroDeHorasDTO> finalizarRegistro(@PathVariable Long id) throws RegraDeNegocioException {
        RegistroDeHorasDTO updatedRegistro = registroDeHorasService.finalizarRegistro(id);
        return ResponseEntity.ok(updatedRegistro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable Long id) {
        registroDeHorasService.deletarRegistro(id);
        return ResponseEntity.noContent().build();
    }
}