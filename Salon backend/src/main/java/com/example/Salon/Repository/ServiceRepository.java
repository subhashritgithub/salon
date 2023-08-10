package com.example.Salon.Repository;

import com.example.Salon.Model.Products;
import com.example.Salon.Model.Services;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Services,Integer> {

}
