package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override // Buscar o usuário, se não existir, lança exceção
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
        if(result.size() == 0) {
            throw new UsernameNotFoundException("User not found!");
        }
        User user = new User();
        // Setar o usuário
        user.setEmail(username);
        // Setar a senha
        user.setPassword(result.get(0).getPassword());
        // Setar as permissões
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        // Retornar o User
        return user;
    }

    // Buscar o usuário logado
    protected User authenticated(){
        try {
            // Pegar o usuário logado
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // Cast para JWT
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            // Pegar o email
            String username = jwtPrincipal.getClaim("username");
            // Devolver o email
            return repository.findByEmail(username).get();

        } catch (Exception e){
            throw new UsernameNotFoundException("Email not found");
        }
    }

    // Pegar o usuário logado baseado no Token
    @Transactional(readOnly = true) // Método de leitura, pra não dar Lock no banco
    public UserDTO getMe() {
        User entity = authenticated();
        return new UserDTO(entity);
    }
}
