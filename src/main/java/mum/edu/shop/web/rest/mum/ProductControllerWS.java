package mum.edu.shop.web.rest.mum;


import mum.edu.shop.domain.Product;
import mum.edu.shop.service.mum.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by msoliman on 5/24/2017.
 */
@RestController
@RequestMapping("/rest/product")
public class  ProductControllerWS {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> products = productService.getAllProduct();
        if (products.size()==0) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") int id) {
        Product foundProduct = productService.getProduct(id);
        if (foundProduct == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            productService.delete(foundProduct);
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody Product product) {
        Product foundProduct = productService.getProduct(product.getId());

        if (foundProduct == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            productService.save(product);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }




}
