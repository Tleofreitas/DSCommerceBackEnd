package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// Arquitetura por padrão de camadas
// Aqui é o serviço que chama o repository para realizar a busca no banco de dados
@Service
public class ProductService {

    @Autowired // Dependencia | Chamando o repository
    private ProductRepository repository;

    @Transactional(readOnly = true) // Lock de leitura - Implementar busca no banco de dados
    public ProductDTO findById(Long id) {
        // Buscar no banco de dados o Id e atribuir na variável
        Product product = repository.findById(id).get();

        // Converter o Product para ProductDTO e retornar para o controlador
        return new ProductDTO(product);
    }

}
