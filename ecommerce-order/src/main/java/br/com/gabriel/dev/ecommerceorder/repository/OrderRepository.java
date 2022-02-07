package br.com.gabriel.dev.ecommerceorder.repository;

import br.com.gabriel.dev.ecommerceorder.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
