package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_order") // Nome da tabela no banco de dados
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id auto incrementável
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderStatus status;

    // Relacionamento MUITOS para UM com User
    @ManyToOne
    @JoinColumn(name = "client_id") // Chave estrangeira do banco
    private User client;


}
