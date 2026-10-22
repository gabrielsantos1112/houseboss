package br.com.housebossinterno.houseboss.dtos.atendimentodtos;

import br.com.housebossinterno.houseboss.enums.StatusPagamento;
import br.com.housebossinterno.houseboss.enums.TipoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AtendimentoClienteResponse(Long id,
                                         String descricao,
                                         LocalDateTime dataHorario,
                                         TipoPagamento tipoPagamento,
                                         StatusPagamento statusPagamento,
                                         BigDecimal valorATendimento,
                                         Long idFuncionario,
                                         String nomeFuncionario) {
}
