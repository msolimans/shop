package mum.edu.shop.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import mum.edu.shop.domain.Product;
import mum.edu.shop.domain.ProductType;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Serializable>{

	public List<Product> findByProductNameLikeOrDescriptionLikeAllIgnoreCase(String productName, String description);
	public List<Product> findByProductType(ProductType productType);
	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
