package com.snowman.batch.config;

import com.snowman.batch.entity.Book;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2020/11/6 15:53
 * @Version 1.0
 **/
@Component(value = "bookFieldSetMapper")
public class BookFieldSetMapper implements FieldSetMapper<Book> {

    @Override
    public Book mapFieldSet(FieldSet fieldSet) {
        Book book = new Book();
        book.setName(fieldSet.readString(0));
        book.setAuthor(fieldSet.readString(1));
        book.setPaperback(fieldSet.readInt(2));
        book.setPublisher(fieldSet.readString(3));
        book.setPrice(fieldSet.readDouble(4));
        book.setDescription(fieldSet.readString(5));
        book.setIsbn10(fieldSet.readString(6));
        return book;
    }
}
