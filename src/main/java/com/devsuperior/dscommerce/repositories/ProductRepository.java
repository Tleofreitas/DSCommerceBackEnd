package com.devsuperior.dscommerce.repositories;

import com.devsuperior.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// Camada de acesso ao banco de dados dos Produtos
public interface ProductRepository extends JpaRepository<Product, Long> {
}
