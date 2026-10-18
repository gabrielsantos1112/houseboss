package br.com.housebossinterno.houseboss.service;

import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoRequest;
import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoRequestPatch;
import br.com.housebossinterno.houseboss.dtos.atendimentodtos.AtendimentoResponse;
import br.com.housebossinterno.houseboss.entity.AtendimentoEntity;
import br.com.housebossinterno.houseboss.entity.ClienteEntity;
import br.com.housebossinterno.houseboss.entity.FuncionarioEntity;
import br.com.housebossinterno.houseboss.enums.StatusPagamento;
import br.com.housebossinterno.houseboss.exception.ResourceNotFoundException;
import br.com.housebossinterno.houseboss.mapper.AtendimentoMapper;
import br.com.housebossinterno.houseboss.repository.AtendimentoRepository;
import br.com.housebossinterno.houseboss.repository.ClienteRepository;
import br.com.housebossinterno.houseboss.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public AtendimentoResponse salvarAtendimentos(AtendimentoRequest atendimentoRequest) {

        ClienteEntity clienteEntity = clienteRepository.findById(atendimentoRequest.idCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Identificador de cliente não encontrado! "));

        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(atendimentoRequest.idFuncionario())
                .orElseThrow(() -> new ResourceNotFoundException("Identificador de funcionário não encontrado! "));

        AtendimentoEntity atendimento = AtendimentoMapper.paraAtendimentoEntity
                (atendimentoRequest, clienteEntity, funcionarioEntity);

        AtendimentoEntity atendimentoSalvo = atendimentoRepository.save(atendimento);

        return AtendimentoMapper.paraAtendimentoResponse(atendimentoSalvo);
    }

    public Page<AtendimentoResponse> encontrarAtendimentosPaginado(Pageable pageable) {
        Page<AtendimentoEntity> atendimentosPaginado = atendimentoRepository.findAll(pageable);
        return AtendimentoMapper.paraAtendimentoResponsePaginado(atendimentosPaginado);
    }

    public AtendimentoResponse encontrarAtendimentoPorId(Long identificador) {

        AtendimentoEntity atendimentoEntity = atendimentoRepository.findById(identificador)
                .orElseThrow(() -> new ResourceNotFoundException("Identificador de atendimento não encontrado! "));

        return AtendimentoMapper.paraAtendimentoResponse(atendimentoEntity);
    }

    public List<AtendimentoResponse> encontrarAtendimentosPorStatus(StatusPagamento status) {
        return atendimentoRepository.findAllByStatusPagamento(status)
                .stream().map(AtendimentoMapper::paraAtendimentoResponse).toList();
    }

    public AtendimentoResponse atualizarAtendimentoPorId
            (Long identificador, AtendimentoRequestPatch atendimento) {

        AtendimentoEntity atendimentoEntity = atendimentoRepository.findById(identificador).orElseThrow
                (() -> new ResourceNotFoundException("Identificador de cliente não encontrado! "));

        atendimentoEntity.setStatusPagamento(atendimento.status());
        atendimentoEntity.setValorAtendimento(atendimento.preco());

        AtendimentoEntity save = atendimentoRepository.save(atendimentoEntity);

        return AtendimentoMapper.paraAtendimentoResponse(save);
    }

    public void deletarAtendimentoPorId(Long identificador) {
        atendimentoRepository.deleteById(identificador);
    }
}
