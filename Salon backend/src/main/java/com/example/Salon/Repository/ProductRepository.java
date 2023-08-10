package com.example.Salon.Repository;

import com.example.Salon.Model.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Products,Integer> {
}
