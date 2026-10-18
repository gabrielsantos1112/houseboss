package br.com.housebossinterno.houseboss.controller;

import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoRequest;
import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoRequestPatch;
import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoResponse;
import br.com.housebossinterno.houseboss.enums.StatusPagamento;
import br.com.housebossinterno.houseboss.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/atendimentos")
@RequiredArgsConstructor
@Valid
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> cadastrarAtendimento
            (@Valid @RequestBody AtendimentoRequest atendimentoRequest) {

        AtendimentoResponse atendimentoResponse = atendimentoService
                .salvarAtendimentos(atendimentoRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("Mensagem: ", "Atendimento cadastrado com sucesso! ");
        response.put("Dados do atendimento: ", atendimentoResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<AtendimentoResponse>> buscarAtendimentosPorPaginacao
            (@PageableDefault(page = 0, size = 10)Pageable pageable) {
        Page<AtendimentoResponse> atendimentos= atendimentoService
                .encontrarAtendimentosPaginado(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);
    }

    @GetMapping("/{identificador}")
    public ResponseEntity<AtendimentoResponse> buscarAtendimentoPorIdentificador
            (@PathVariable Long identificador) {
        AtendimentoResponse atendimento = atendimentoService
                .encontrarAtendimentoPorId(identificador);
        return ResponseEntity.status(HttpStatus.OK).body(atendimento);
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<AtendimentoResponse>> buscarAtendimentosPorStatus
            (@RequestParam(value = "status", required = true) StatusPagamento status) {
        List<AtendimentoResponse> atendimentos = atendimentoService.encontrarAtendimentosPorStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);
    }

    @PatchMapping("/{identificador}")
    public ResponseEntity<AtendimentoResponse> atualizarAtendimentoPorIdentificador
            (@PathVariable Long identificador, @RequestBody AtendimentoRequestPatch atendimento) {

        AtendimentoResponse atendimentoResponse = atendimentoService.atualizarAtendimentoPorId
                (identificador, atendimento);
        return ResponseEntity.status(HttpStatus.OK).body(atendimentoResponse);
    }

    @DeleteMapping("/{identificador}")
    public ResponseEntity<Void> deletarAtendimentoPorIdentificador(Long identificador) {
        atendimentoService.deletarAtendimentoPorId(identificador);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
