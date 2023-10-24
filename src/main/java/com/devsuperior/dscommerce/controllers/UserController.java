package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Responder pela web
@RequestMapping(value = "/users") // Rota de mapeamento da Web
public class UserController {
    @Autowired // Dependencia | Chamando o serviço
    private UserService service;

    // ResponseEntity = Padronização de retorno de resposta
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe() {
    	UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto); // Retornar Status 200
    }
}
