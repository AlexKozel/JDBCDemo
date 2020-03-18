package com.example.dataBasedemo.jdbc;

import com.example.dataBasedemo.bean.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonJdbcDao {


    JdbcTemplate jdbcTemplate;

    /**
     * query to sql and using standard mapper for response
     *
     * @return List of Person
     */
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
    }

    /**
     * query to sql and using standard mapper for response with generic
     *
     * @param id
     * @return single Person
     */
    public Person findById(int id) {
        return jdbcTemplate.queryForObject("select * from person where id=?",
                new Object[]{id}, new BeanPropertyRowMapper<Person>(Person.class));
    }

    /**
     * delete by Person Id - query without mappers
     *
     * @param id
     * @return int - how much rows updated
     */
    public int deleteById(int id) {
        return jdbcTemplate.update("delete from person where id=?",
                new Object[]{id});
    }

    /**
     * insert to DB new Person
     * @param person
     * @return int - how much rows updated
     */
    public int insert(Person person) {
        return jdbcTemplate.update("insert into person (id, name, location, birth_date) " +
                        "values ( ?, ?, ?, ? )",
                new Object[] {person.getId(),
                        person.getName(),
                        person.getLocation(),
                        person.getBirthDate()});
    }

    /**
     * Update person by id
     * @param person
     * @return int - how much rows updated
     */
    public int update(Person person) {
        return jdbcTemplate.update("update person " +
                        " set  name=?, location=?, birth_date=? " +
                        " where id=?",
                new Object[] {
                        person.getName(),
                        person.getLocation(),
                        person.getBirthDate(),
                        person.getId(),});
    }

    PersonJdbcDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
