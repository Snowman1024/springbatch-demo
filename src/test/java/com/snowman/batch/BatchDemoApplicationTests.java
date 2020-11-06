package com.snowman.batch;

import com.snowman.batch.entity.Book;
import com.snowman.batch.repository.BookJpa;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class BatchDemoApplicationTests {

    @Autowired
    private BookJpa bookJpa;

    @Test
    public void contextLoads() {
        Book book = new Book();
        book.setAuthor("伊凡·蒲宁");
        book.setDescription("《林荫幽径》是诺贝尔文学奖获得者蒲宁暮年的最后一部自选集，用八年时间写成，是他一生中写得最好、最富独创性的一个集子。《林荫幽径》真切地表现了作者对于故国的怀念和对于逝去的青年时代的眷恋。作品构思玲珑剔透，非常精致，有着磁石般的强烈吸引力；文字精练，抒情性强，擅 长表现人物内心的微妙情感。具有悠远的意境和发人遐思的哲理，展现了永恒的艺术魅力。");
        book.setIsbn10("9787540786618");
        book.setLanguage("中文");
        book.setName("林荫幽径");
        book.setPaperback(200);
        book.setPrice(45.00);
        book.setPublisher("漓江出版社");
        Book savedBook = bookJpa.save(book);
        Assert.assertNotNull(savedBook);
        log.info(savedBook.toString());
    }

}
