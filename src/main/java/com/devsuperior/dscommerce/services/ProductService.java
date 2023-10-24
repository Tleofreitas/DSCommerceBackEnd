package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// Arquitetura por padrão de camadas
// Aqui é o serviço que chama o repository para realizar a busca no banco de dados
@Service
public class ProductService {

    @Autowired // Dependencia | Chamando o repository
    private ProductRepository repository;

    @Transactional(readOnly = true) // Lock de leitura - Implementar busca no banco de dados
    public ProductDTO findById(Long id) {
        // Buscar no banco de dados o Id e atribuir na variável
        Product product = repository.findById(id).
                // Se não encontrar o Id, lança exceção
                orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado!"));

        // Converter o Product para ProductDTO e retornar para o controlador
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable) {
        // Buscar no banco de dados a lista de Produtos | Pageable = Listagem paginada
        Page<Product> result = repository.searchByName(name, pageable);

        // Converter a lista de  Product para ProductDTO e retornar para o controlador
        return result.map(x -> new ProductDTO(x));
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        // Instanciar um Product
        Product entity = new Product();

        // Copiar os dados do dto para a entidade
        copyDtoToEntity(dto, entity);

        // Salvar no banco de dados
        entity = repository.save(entity);

        // Retornar como DTO
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            // Instanciar um Product com a referência do Id
            Product entity = repository.getReferenceById(id);

            // Copiar os dados do dto para a entidade
            copyDtoToEntity(dto, entity);

            // Salvar no banco de dados
            entity = repository.save(entity);

            // Retornar como DTO
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            // Se não encontrar o Id, lança exceção
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }
    /*
    @Transactional
    public void delete(Long id) {
        // Buscar no banco de dados o Id e deletar
        repository.deleteById(id);
    }*/

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        // Verificar se Id existe
        if (!repository.existsById(id)) {
            // Se não encontrar o Id, lança exceção
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
