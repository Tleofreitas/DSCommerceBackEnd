package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.CategoryDTO;
import com.devsuperior.dscommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Responder pela web
@RequestMapping(value = "/categories") // Rota de mapeamento da Web
public class CategoryController {
    @Autowired // Dependencia | Chamando o serviço
    private CategoryService service;

    // ResponseEntity = Padronização de retorno de resposta

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok(list); // Retornar Status 200
    }
}
