package br.com.housebossinterno.houseboss.repository;

import br.com.housebossinterno.houseboss.entity.AtendimentoEntity;
import br.com.housebossinterno.houseboss.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Long> {
    List<AtendimentoEntity> findAllByStatusPagamento(StatusPagamento status);
}
