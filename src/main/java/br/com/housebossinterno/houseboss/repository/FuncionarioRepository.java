package br.com.housebossinterno.houseboss.repository;

import br.com.housebossinterno.houseboss.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    Optional<UserDetails> findUserByEmail(String username);
}
