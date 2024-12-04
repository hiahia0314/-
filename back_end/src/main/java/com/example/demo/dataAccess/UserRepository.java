package com.example.demo.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    List<User> findByName(String username);

    Optional<User> findAllById(String id);

    Optional<User> findAllByName(String name);

    Optional<User> findById(String id);

    @Transactional
    @Query("delete from User x where x.id = (?1)")
    @Modifying
    void deleteById(String id);

    @Transactional
    @Query("INSERT INTO User (id, name, password, age, e_mail) VALUES (:id, :name, :password, :age, :e_mail)")
    @Modifying
    void addUser(@Param("id")String id, @Param("name")String name, @Param("password") String password, @Param("age") int age, @Param("e_mail") String e_mail);
}
