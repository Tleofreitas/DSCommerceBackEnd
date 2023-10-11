package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController // Responder pela web
@RequestMapping(value = "/products") // Rota de mapeamento da Web
public class ProductController {

    @Autowired // Dependencia | Chamando o serviço
    private ProductService service;

    // ResponseEntity = Padronização de retorno de resposta

    // Buscar produto por Id
    @GetMapping(value = "/{id}") // Retorno da consulta
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto); // Retornar Status 200
    }

    // Buscar todos os produtos de forma paginada
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        // Pageable = Listagem paginada
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto); // Retornar Status 200
    }

    // Adicionar um produto
    @PostMapping
    public ResponseEntity<ProductDTO> insert
        (@Valid /* Para checar as validações inseridas no DTO*/
         @RequestBody ProductDTO dto) {
        // @RequestBody = Corpo da requisição
        dto = service.insert(dto); // Chamar o serviço de inserção e passar os dados
        // URI = link do recurso criado | Boa prática
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto); // Retornar Status 201 Created
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto); // Chamar o serviço de atualização com o Id passado e as infos de atualização
        return ResponseEntity.ok(dto); // Retornar Status 200
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id); // Chamar o serviço de deletar com o Id passado
        return ResponseEntity.noContent().build(); // Retornar Status 204 = Sem retorno
    }
}
