package com.example.demo.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipsRepository extends JpaRepository<Friendships,Long> {
    boolean existsByUserAndFriend(long user, long friend);
    List<Friendships> findByUserOrFriend(long user,long friend);

}
