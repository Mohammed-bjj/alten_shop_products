package alten.shop.backend.backEnd.repositories;


import alten.shop.backend.backEnd.utils.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);

}
