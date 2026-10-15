package br.com.housebossinterno.houseboss.dtos.clientedtos;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(@NotBlank(message = "Nome é obrigatório! ") String nome,
                             @NotBlank(message = "Número é obrigatório! ") String numero) {
}
