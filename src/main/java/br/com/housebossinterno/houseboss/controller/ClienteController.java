package br.com.housebossinterno.houseboss.controller;

import br.com.housebossinterno.houseboss.dtos.clientedtos.ClienteRequest;
import br.com.housebossinterno.houseboss.dtos.clientedtos.ClienteResponse;
import br.com.housebossinterno.houseboss.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> cadastrarClientes(@Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteResponse = clienteService.salvarClientes(clienteRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("Mensagem: ", "Cliente cadastrado com sucesso! ");
        response.put("Dados do cliente: ", clienteResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<ClienteResponse> clienteResponses = clienteService.encontrarClientes();
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponses);
    }

    @GetMapping("/{identificador}")
    public ResponseEntity<ClienteResponse> buscarClientePorIdentificador(@PathVariable Long identificador) {
        ClienteResponse clienteResponse = clienteService.encontrarClientePorId(identificador);
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponse);
    }

    @PutMapping("/{identificador}")
    public ResponseEntity<ClienteResponse> atualizarClientePorIdentificador
            (@PathVariable Long identificador, @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteResponse = clienteService.atualizarClientePorIdentificador(identificador, clienteRequest);
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponse);
    }

    @DeleteMapping("/{identificador}")
    public ResponseEntity<Void> deletarClientePorIdentificador(@PathVariable Long identificador) {
        clienteService.excluirClientePorIdentificador(identificador);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
