package com.yangonion.springbootrestfulapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Yang
 */
public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByName(String name);

    Book findByNameAndPrice(String name,Double price);

    @Query("from Book u where u.name=:name")
    Book findBook(@Param("name") String name);
}
