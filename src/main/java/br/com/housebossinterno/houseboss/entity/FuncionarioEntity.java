package br.com.housebossinterno.houseboss.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String senha;

    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY)
    private List<AtendimentoEntity> atendimentosFuncionario = new ArrayList<>();
}
