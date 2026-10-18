package br.com.housebossinterno.houseboss.dtos.atendimentodtos;

import br.com.housebossinterno.houseboss.enums.StatusPagamento;
import br.com.housebossinterno.houseboss.enums.TipoPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtendimentoRequest(@NotBlank(message = "O atributo descrição é obrigatório! ") String descricaoAtendimento,
                                 @NotNull(message = "O atributo tipo de pagamento é obrigatório! ") TipoPagamento tipoPagamento,
                                 @NotNull(message = "O atributo status do pagamento é obrigatório! ") StatusPagamento statusPagamento,
                                 @NotNull(message = "O atributo valor do atendimento é obrigatório! ") BigDecimal valorAtendimento,
                                 @NotNull(message = "É necessário informar o id do cliente! ") Long idCliente,
                                 @NotNull(message = "É necessário informar o id do funcionário! ") Long idFuncionario) {
}
