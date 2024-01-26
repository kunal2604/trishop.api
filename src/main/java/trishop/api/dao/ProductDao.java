package trishop.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trishop.api.entity.Product;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
}
