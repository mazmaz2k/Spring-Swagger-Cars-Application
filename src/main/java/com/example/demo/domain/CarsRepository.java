package com.example.demo.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarsRepository extends PagingAndSortingRepository<Car, Long> {
}

