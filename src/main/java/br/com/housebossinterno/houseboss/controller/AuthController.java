package br.com.housebossinterno.houseboss.controller;

import br.com.housebossinterno.houseboss.config.TokenService;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.FuncionarioRequest;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.FuncionarioResponse;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.LoginRequest;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.LoginResponse;
import br.com.housebossinterno.houseboss.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registrarFuncionario
            (@Valid @RequestBody FuncionarioRequest funcionarioRequest) {
        FuncionarioResponse funcionarioResponse = authService.salvarFuncionario(funcionarioRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("Mensagem: ", "Funcionário cadastrado com sucesso! ");
        response.put("Dados do funcionário: ", funcionarioResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login
            (@Valid @RequestBody LoginRequest loginRequest) {

        LoginResponse login = authService.login(loginRequest);

        return ResponseEntity.ok().body(login);
    }
}
