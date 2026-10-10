package br.com.housebossinterno.houseboss.entity;

import br.com.housebossinterno.houseboss.enums.StatusPagamento;
import br.com.housebossinterno.houseboss.enums.TipoPagamento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "atendimentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AtendimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, name = "descricao_atendimento")
    private String descricaoAtendimento;

    @CreationTimestamp
    @Column(name = "data_horario")
    private LocalDateTime dataHorario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento", nullable = false)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = false)
    private StatusPagamento statusPagamento;

    @Column(name = "valor_atendimento", nullable = false)
    private BigDecimal valorAtendimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionarioEntity funcionario;

}
