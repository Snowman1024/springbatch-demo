package com.snowman.batch.config;

import com.snowman.batch.entity.Book;
import com.snowman.batch.repository.BookJpa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description https://blog.csdn.net/qq_35325367/article/details/89531866
 * @Author Snowman2014
 * @Date 2020/11/6 15:44
 * @Version 1.0
 **/
@EnableBatchProcessing
@Configuration
@Slf4j
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final BookJpa bookJpa;

    @Autowired
    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, BookJpa bookJpa) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.bookJpa = bookJpa;
    }


    @Bean
    public Job bookImportJop(@Qualifier("bookJobListener") JobExecutionListener jobListener
            , @Qualifier("step1") Step step1) {
        return jobBuilderFactory.get("bookImportJob")
                .listener(jobListener)
                .start(step1).build();
    }

    @Bean
    protected Step step1(@Qualifier("itemReader") ItemReader<Book> reader,
                         @Qualifier("itemProcessor") ItemProcessor<Book, Book> processor,
                         @Qualifier("itemWriter") ItemWriter<Book> writer,
                         @Qualifier("BookReadListener") ItemReadListener<Book> readListener,
                         @Qualifier("BookProcessListener") ItemProcessListener<Book, Book> processListener,
                         @Qualifier("BookWriterListener") ItemWriteListener<Book> writeListener) {
        return stepBuilderFactory.get("step1")
                .<Book, Book>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(readListener)
                .listener(processListener)
                .listener(writeListener)
                .build();
    }


    //    @Bean
//    protected Step step2(Tasklet tasklet) {
//        return stepBuilderFactory.get("step2")
//                .tasklet(tasklet)
//                .build();
//    }


    @Bean(value = "itemReader")
    public FlatFileItemReader<Book> bookItemRead(@Qualifier("bookFieldSetMapper") FieldSetMapper<Book> fieldSetMapper) {
        return new FlatFileItemReaderBuilder<Book>()
                .name("bookItemReader")
                //设置文件位置
                .resource(new ClassPathResource("data.txt"))
                //字段分割方法
                .lineTokenizer(new DelimitedLineTokenizer())
                //将读取的内容映射到对象
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean(value = "itemProcessor")
    public ItemProcessor<Book, Book> processor() {
        return item -> {
            if (null != item) {
                item.setDescription("already pass through the process");
            } else {
                log.info("book is null");
            }
            return item;
        };
    }


    @Bean(value = "itemWriter")
    public ItemWriter<Book> writer() {
        log.info("begin writer.......");
        return list -> {
            log.info("write dataList : " + list);
            bookJpa.saveAll(list);
        };
    }


}
