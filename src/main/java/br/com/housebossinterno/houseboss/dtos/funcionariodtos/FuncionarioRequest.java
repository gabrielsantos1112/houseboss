package br.com.housebossinterno.houseboss.dtos.funcionariodtos;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioRequest(@NotBlank(message = "Nome é obrigatório! ") String nome,
                                 @NotBlank(message = "Email é obrigatório! ") String email,
                                 @NotBlank(message = "Senha é obrigatória! ") String senha) {
}
