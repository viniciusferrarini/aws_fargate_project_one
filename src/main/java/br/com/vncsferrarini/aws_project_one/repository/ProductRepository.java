package br.com.vncsferrarini.aws_project_one.repository;

import br.com.vncsferrarini.aws_project_one.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByCode(String code);

}
