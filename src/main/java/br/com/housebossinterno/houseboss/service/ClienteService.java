package br.com.housebossinterno.houseboss.service;

import br.com.housebossinterno.houseboss.dtos.clientedtos.ClienteRequest;
import br.com.housebossinterno.houseboss.dtos.clientedtos.ClienteResponse;
import br.com.housebossinterno.houseboss.entity.ClienteEntity;
import br.com.housebossinterno.houseboss.exception.BadRequestException;
import br.com.housebossinterno.houseboss.exception.ResourceNotFoundException;
import br.com.housebossinterno.houseboss.mapper.ClienteMapper;
import br.com.housebossinterno.houseboss.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse salvarClientes(ClienteRequest clienteRequest) {
        ClienteEntity clienteEntity = ClienteMapper.paraClienteEntity(clienteRequest);

        if (clienteRepository.existsByNumero(clienteEntity.getNumero())) {
            throw new BadRequestException("Número já existente no banco de dados! ");
        }

        ClienteEntity clienteSalvo = clienteRepository.save(clienteEntity);

        ClienteResponse clienteResponse = ClienteMapper.paraClienteResponse(clienteSalvo);

        return clienteResponse;
    }

    public List<ClienteResponse> encontrarClientes() {
        List<ClienteResponse> clientes = clienteRepository.findAll().stream().map(ClienteMapper::paraClienteResponse).toList();
        return clientes;
    }

    public ClienteResponse encontrarClientePorId(Long identificador) {
        ClienteEntity clienteEntity = clienteRepository.findById(identificador).orElseThrow(
                () -> new ResourceNotFoundException("Identificador de cliente não encontrado! "));

        return ClienteMapper.paraClienteResponse(clienteEntity);
    }

    public ClienteResponse atualizarClientePorIdentificador(Long identificador, ClienteRequest request) {
        ClienteEntity clienteEntity = clienteRepository.findById(identificador).
                orElseThrow(() -> new ResourceNotFoundException("Identificador de cliente não encontrado! "));

        clienteEntity.setNome(request.nome());
        clienteEntity.setNumero(request.numero());

        ClienteEntity clienteSalvo = clienteRepository.save(clienteEntity);

        return ClienteMapper.paraClienteResponse(clienteSalvo);
    }

    @Transactional
    public void excluirClientePorIdentificador(Long identificador) {
        ClienteEntity clienteEntity = clienteRepository.findById(identificador).
                orElseThrow(() -> new ResourceNotFoundException("Identificador de cliente não encontrado! "));

        if (!clienteEntity.getAtendimentosCliente().isEmpty()) {
            throw new BadRequestException("Não é possível excluir o cliente pois ele possui atendimentos vinculados!");
        }

        clienteRepository.delete(clienteEntity);
    }
}
