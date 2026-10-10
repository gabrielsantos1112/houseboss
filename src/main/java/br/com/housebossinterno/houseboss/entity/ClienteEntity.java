package br.com.housebossinterno.houseboss.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String numero;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<AtendimentoEntity> atendimentosCliente = new ArrayList<>();
}
