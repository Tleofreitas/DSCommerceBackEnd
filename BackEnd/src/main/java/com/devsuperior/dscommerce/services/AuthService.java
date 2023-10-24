package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Controle de Acessos
public class AuthService {

	@Autowired
	private UserService userService;

	public void validateSelfOrAdmin(long userId) {
		User me = userService.authenticated();
		// Verificar se o User é ADMIN ou dono do PEDIDO
		if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
			// Sendo negativo lança a Exceção
			throw new ForbiddenException("Access denied");
		}
	}
}
