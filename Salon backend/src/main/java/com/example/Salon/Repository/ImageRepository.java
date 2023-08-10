package com.example.Salon.Repository;

import com.example.Salon.Model.Images;
import org.springframework.data.repository.CrudRepository;
public interface ImageRepository extends CrudRepository<Images,Integer> {
}
