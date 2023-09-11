package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Responder pela web
@RequestMapping(value = "/products") // Rota de mapeamento da Web
public class ProductController {

    @Autowired // Dependencia | Chamando o serviço
    private ProductService service;

    // Teste com acesso ao banco de dados
    @GetMapping(value = "/{id}") // Retorno da consulta
    public ProductDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
