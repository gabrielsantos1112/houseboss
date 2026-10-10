package br.com.housebossinterno.houseboss.repository;

import br.com.housebossinterno.houseboss.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Boolean existsByNumero(String numero);

    @Override
    Optional<ClienteEntity> findById(Long id);
}
