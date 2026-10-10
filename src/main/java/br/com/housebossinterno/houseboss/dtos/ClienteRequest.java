package br.com.housebossinterno.houseboss.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(@NotBlank String nome, @NotBlank String numero) {
}
