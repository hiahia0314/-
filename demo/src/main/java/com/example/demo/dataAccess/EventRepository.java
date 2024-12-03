package com.example.demo.dataAccess;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    @Transactional
    @Query("delete from Event x where x.uid =(?1)")
    @Modifying
    void deleteByUid(Long uid);

    @Transactional
    @Query("SELECT distinct e.date FROM Event e WHERE e.user = (?1)")
    @Modifying
    List<LocalDate> findDateByUser(String UserId);

    List<Event> findByDateAndUser(LocalDate date, String user);


}
