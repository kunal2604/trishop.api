package trishop.api.dao;

import trishop.api.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import trishop.api.entity.OrderDetail;

public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
    public List<OrderDetail> findByBuyer(User user);
}
