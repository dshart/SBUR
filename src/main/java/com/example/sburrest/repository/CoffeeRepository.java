package com.example.sburrest.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.sburrest.model.Coffee;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {}
