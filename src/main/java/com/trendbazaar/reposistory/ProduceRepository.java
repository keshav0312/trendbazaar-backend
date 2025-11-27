package com.trendbazaar.reposistory;

import com.trendbazaar.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduceRepository extends JpaRepository<Product,Long> {
}
