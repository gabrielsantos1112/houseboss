package br.com.housebossinterno.houseboss.mapper;

import br.com.housebossinterno.houseboss.dtos.ClienteRequest;
import br.com.housebossinterno.houseboss.dtos.ClienteResponse;
import br.com.housebossinterno.houseboss.entity.ClienteEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClienteMapper {

    public static ClienteEntity paraClienteEntity(ClienteRequest request) {
        return ClienteEntity.builder()
                .nome(request.nome())
                .numero(request.numero())
                .build();
    }

    public static ClienteResponse paraClienteResponse(ClienteEntity entity) {
        return new ClienteResponse(
                entity.getId(),
                entity.getNome(),
                entity.getNumero()
        );
    }

}
