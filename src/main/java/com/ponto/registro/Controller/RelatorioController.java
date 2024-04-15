package com.ponto.registro.Controller;

import com.ponto.registro.DTO.RelatorioDeHoras.RelatorioDTO;
import com.ponto.registro.Service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioDeHorasService;

    @GetMapping
    public ResponseEntity<List<RelatorioDTO>> buscarTodosRelatorios() {
        List<RelatorioDTO> relatorios = relatorioDeHorasService.buscarTodosRelatorios();
        return ResponseEntity.ok(relatorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDTO> buscarPorId(@PathVariable Long id) {
        RelatorioDTO relatorio = relatorioDeHorasService.buscarPorId(id);
        return ResponseEntity.ok(relatorio);
    }
}