package br.com.housebossinterno.houseboss.mapper;

import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoRequest;
import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoResponse;
import br.com.housebossinterno.houseboss.entity.AtendimentoEntity;
import br.com.housebossinterno.houseboss.entity.ClienteEntity;
import br.com.housebossinterno.houseboss.entity.FuncionarioEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AtendimentoMapper {

    public static AtendimentoEntity paraAtendimentoEntity
            (AtendimentoRequest atendimentoRequest, ClienteEntity cliente, FuncionarioEntity funcionario) {
        return AtendimentoEntity.builder()
                .descricaoAtendimento(atendimentoRequest.descricaoAtendimento())
                .tipoPagamento(atendimentoRequest.tipoPagamento())
                .statusPagamento(atendimentoRequest.statusPagamento())
                .valorAtendimento(atendimentoRequest.valorAtendimento())
                .cliente(cliente)
                .funcionario(funcionario)
                .build();
    }

    public static AtendimentoResponse paraAtendimentoResponse(AtendimentoEntity atendimento) {
        return new AtendimentoResponse(
                atendimento.getId(),
                atendimento.getDescricaoAtendimento(),
                atendimento.getDataHorario(),
                atendimento.getTipoPagamento(),
                atendimento.getStatusPagamento(),
                atendimento.getValorAtendimento(),
                atendimento.getCliente().getId(),
                atendimento.getCliente().getNome(),
                atendimento.getFuncionario().getId(),
                atendimento.getFuncionario().getNome()
        );
    }

    public static Page<AtendimentoResponse> paraAtendimentoResponsePaginado(Page<AtendimentoEntity> atendimentos) {
        return atendimentos.map(atendimento -> new AtendimentoResponse(
                atendimento.getId(),
                atendimento.getDescricaoAtendimento(),
                atendimento.getDataHorario(),
                atendimento.getTipoPagamento(),
                atendimento.getStatusPagamento(),
                atendimento.getValorAtendimento(),
                atendimento.getCliente().getId(),
                atendimento.getCliente().getNome(),
                atendimento.getFuncionario().getId(),
                atendimento.getFuncionario().getNome()
        ));
    }
}
