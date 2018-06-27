package ro.microservices.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.microservices.store.entity.Product;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public Collection<Product> findByCategoryId(final Long id);

    Collection<Product> findByCode(String code);
}
