package br.com.housebossinterno.houseboss.repository;

import br.com.housebossinterno.houseboss.entity.AtendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Long> {
}
