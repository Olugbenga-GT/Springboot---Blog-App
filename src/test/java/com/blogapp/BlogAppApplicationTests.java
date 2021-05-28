package com.blogapp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class BlogAppApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void testThatApplicationCanConnectToTheDatabase(){
		assertThat(dataSource).isNotNull();
		log.info("Data source object is created");
		try(Connection connection = dataSource.getConnection())	{
			assertThat(dataSource).isNotNull();
			assertThat(connection.getCatalog()).isEqualTo("blogdb");
			log.info("Connection --> {}", connection.getCatalog());
		}
		catch (SQLException throwables){
			log.info("Exception occured while connecting to the database --> {}", throwables.getMessage());
		}
	}
}
