package br.com.housebossinterno.houseboss.dtos.atendimentodtos;

import br.com.housebossinterno.houseboss.enums.StatusPagamento;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtendimentoRequestPatch(@NotNull(message = "É obrigatório informar o status!") StatusPagamento status,
                                      @NotNull(message = "É obrigatório informar o preço!") BigDecimal preco) {
}
