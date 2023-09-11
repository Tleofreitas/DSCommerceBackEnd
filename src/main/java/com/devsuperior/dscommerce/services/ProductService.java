package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
        Product product = repository.findById(id).get();

        // Converter o Product para ProductDTO e retornar para o controlador
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        // Buscar no banco de dados a lista de Produtos | Pageable = Listagem paginada
        Page<Product> result = repository.findAll(pageable);

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
        // Instanciar um Product com a referência do Id
        Product entity = repository.getReferenceById(id);

        // Copiar os dados do dto para a entidade
        copyDtoToEntity(dto, entity);

        // Salvar no banco de dados
        entity = repository.save(entity);

        // Retornar como DTO
        return new ProductDTO(entity);
    }
}
