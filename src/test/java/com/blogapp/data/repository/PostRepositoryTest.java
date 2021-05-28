package com.blogapp.data.repository;

import com.blogapp.data.models.Author;
import com.blogapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
@SpringBootTest
@Sql(scripts = {"classpath:db/insert.sql"})
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    void savePostToDBTest(){
        Post blogPost  = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorem ipsum is just a yada yada yada");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

    }

    @Test
    void throwExceptionWhenSavingPostWithoutDuplicateTitle(){
        Post blogPost  = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorem ipsum is just a yada yada yada");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

        Post blogPost2 = new Post();
        blogPost2.setTitle("What Is FinTech?");
        blogPost2.setContent("Lorem ipsum is just a yada yada yada");
        log.info("Created a blog post --> {}", blogPost2);
        assertThrows(DataIntegrityViolationException.class, () -> postRepository.save(blogPost2));
    }

    @Test
    void whenPostIsSaved_alsoSaveAuthor(){
        Post blogPost  = new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("Lorem ipsum is just a yada yada yada");

        log.info("Created a blog post --> {}", blogPost);

        Author author = new Author();
        author.setFirstName("Malik");
        author.setLastName("Andrei");
        author.setEmail("malikandrei@gnmail.com");
        author.setPhoneNumber("08175031568");

//        Maap Relationship
        blogPost.setAuthor(author);
        author.addPost(blogPost);

    }
}