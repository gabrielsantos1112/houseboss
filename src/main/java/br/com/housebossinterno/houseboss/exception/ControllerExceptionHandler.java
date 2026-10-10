package br.com.housebossinterno.houseboss.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, String>> badRequestExceptions(BadRequestException exception) {
        Map<String, String> response =  new HashMap<>();
        response.put("Erro: ", exception.getMessage());
        response.put("Mensagem: ", "Por favor, insira uma informação válida! ");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> resourceNotFoundExceptions(ResourceNotFoundException exception) {
        Map<String, String> response = new HashMap<>();
        response.put("Erro: ", exception.getMessage());
        response.put("Mensagem: ", "Por favor, insira uma informação válida! ");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
