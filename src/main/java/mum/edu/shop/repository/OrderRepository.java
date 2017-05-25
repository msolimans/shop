package mum.edu.shop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.edu.shop.domain.Order;
import mum.edu.shop.domain.Person;
import mum.edu.shop.domain.Product;


public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findDistinctOrderByOrderLines_Product(Product product);
	List<Order> findOrderByPerson(Person person);
	List<Order> findOrderByOrderDateBetween(Date minDate, Date maxDate);


}
