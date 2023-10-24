package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.dto.OrderItemDTO;
import com.devsuperior.dscommerce.entities.*;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));

        // Verificar se User é ADMIN ou dono do PEDIDO
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    // Salvar um Pedido
    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order order = new Order();
        // Hora atual
        order.setMoment(Instant.now());
        // Status Inicial
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        // Pegar o usuário
        User user = userService.authenticated();
        order.setClient(user);

        // Pegar os itens e associar com o cliente
        for (OrderItemDTO itemDto : dto.getItems()) {
            // Pegar o produto
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            // Produto, Pedido, Quantidade e Preço
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            // Associar Item ao Pedido
            order.getItems().add(item);
        }
        // Salvar o pedido
        repository.save(order);
        orderItemRepository.saveAll(order.getItems());
        // Retornar como DTO
        return new OrderDTO(order);
    }
}
