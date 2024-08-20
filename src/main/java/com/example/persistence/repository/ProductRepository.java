package com.example.persistence.repository;
import com.example.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {

    @Query("SELECT avg(p.price) FROM Product p JOIN p.category c WHERE c.id=?1")
    Double avgPriceByCategoryId (Integer categoryId);
    @Query("SELECT min(p.price) FROM Product p JOIN p.category c WHERE c.id=?1")
    int minPriceByCategoryId (Integer categoryId);
    @Query("SELECT max(p.price) FROM Product p JOIN p.category c WHERE c.id=?1")
    int maxPriceByCategoryId (Integer categoryId);
    @Query("SELECT count(p) FROM Product p JOIN p.category c WHERE c.id=?1")
    int countByCategoryId (Integer categoryId);
}
