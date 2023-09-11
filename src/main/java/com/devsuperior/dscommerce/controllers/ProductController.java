package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Responder pela web
@RequestMapping(value = "/products") // Rota de mapeamento da Web
public class ProductController {

    @Autowired // Dependencia | Chamando o serviço
    private ProductService service;

    // Buscar produto por Id
    @GetMapping(value = "/{id}") // Retorno da consulta
    public ProductDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Buscar todos os produtos de forma paginada
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        // Pageable = Listagem paginada
        return service.findAll(pageable);
    }
}
