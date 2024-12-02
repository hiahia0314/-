package com.example.demo.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String username);

    Optional<User> findAllById(String id);

    Optional<User> findAllByName(String name);

    Optional<User> findById(String id);

    User deleteById(String id);
}
