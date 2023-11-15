package in.rafiit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rafiit.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
