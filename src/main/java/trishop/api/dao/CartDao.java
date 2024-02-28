package trishop.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trishop.api.entity.Cart;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {
}
