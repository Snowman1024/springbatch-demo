package com.snowman.batch.repository;

import com.snowman.batch.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2020/11/6 14:30
 * @Version 1.0
 **/
public interface BookJpa extends JpaRepository<Book, Integer> {
}
