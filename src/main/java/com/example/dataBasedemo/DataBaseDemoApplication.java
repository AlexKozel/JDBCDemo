package com.example.dataBasedemo;

import com.example.dataBasedemo.bean.Person;
import com.example.dataBasedemo.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DataBaseDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	final
	PersonJdbcDao dao;

	@Autowired
	public DataBaseDemoApplication(PersonJdbcDao dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(DataBaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("\nAll users -> {} ",dao.findAll());
		logger.info("\nUser with id {} -> {} ", 10002 ,dao.findById(10002));
		logger.info("\nhow much users delete -> {} ",dao.deleteById(10003));
		logger.info("\nAll users -> {} ",dao.findAll());
		logger.info("\nnew  user -> {} ",dao.insert(new Person(10004, "Alex", "Belarus", new Date())));
		logger.info("\nnew  user -> {} ",dao.update(new Person(10001, "NoRanga", "Belarus", new Date())));
		logger.info("\nAll users -> {} ",dao.findAll());

	}
}
