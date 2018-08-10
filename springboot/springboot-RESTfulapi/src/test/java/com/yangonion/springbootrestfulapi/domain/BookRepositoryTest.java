package com.yangonion.springbootrestfulapi.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private  BookRepository bookRepository;

    @Before
    public  void setUp(){
        // 创建10条记录
        bookRepository.save(new Book("设计模式", 28D,""));
        bookRepository.save(new Book("Think in java", 35d,"经典书籍"));
        bookRepository.save(new Book("java并发编程", 60D,""));
    }


    @Test
    public  void testBookRepository(){

        Book book = bookRepository.findByName("Think in java");
        Double price = book.getPrice();
        Assert.assertTrue(price==35d);

        Book book2 = bookRepository.findByNameAndPrice("java并发编程",60D);
        Assert.assertNotNull(book2);
    }
}