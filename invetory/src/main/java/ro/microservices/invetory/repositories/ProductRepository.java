package ro.microservices.invetory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservices.invetory.entities.Product;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
        Collection<Product> findByCode(final String prodCode);
}
