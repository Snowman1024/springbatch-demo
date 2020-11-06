package com.snowman.batch.config.listener;

import com.snowman.batch.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2020/11/6 16:01
 * @Version 1.0
 **/
@Component(value = "BookWriterListener")
@Slf4j
public class BookWriterListener implements ItemWriteListener<Book> {

    @Override
    public void beforeWrite(List<? extends Book> items) {
        log.info("before write..." + Optional.ofNullable(items));
    }

    @Override
    public void afterWrite(List<? extends Book> items) {
        log.info("after write..." + Optional.ofNullable(items));
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Book> items) {
        log.info("write error...");
    }
}
