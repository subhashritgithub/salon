package com.example.Salon.Repository;

import com.example.Salon.Model.Contacts;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contacts,Integer> {
}
