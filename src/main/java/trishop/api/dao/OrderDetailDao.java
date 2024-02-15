package trishop.api.dao;

import org.springframework.data.repository.CrudRepository;
import trishop.api.entity.OrderDetail;

public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {

}
