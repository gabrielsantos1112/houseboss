package br.com.housebossinterno.houseboss.mapper;

import br.com.housebossinterno.houseboss.dtos.funcionariodtos.FuncionarioRequest;
import br.com.housebossinterno.houseboss.dtos.funcionariodtos.FuncionarioResponse;
import br.com.housebossinterno.houseboss.entity.FuncionarioEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FuncionarioMapper {

    public static FuncionarioEntity paraFuncionarioEntity(FuncionarioRequest funcionarioRequest) {
        return FuncionarioEntity.builder()
                .nome(funcionarioRequest.nome())
                .email(funcionarioRequest.email())
                .senha(funcionarioRequest.senha())
                .build();
    }

    public static FuncionarioResponse paraFuncionarioResponse(FuncionarioEntity entity) {
        return new FuncionarioResponse(
                entity.getId(),
                entity.getNome(),
                entity.getEmail()
        );
    }

}
