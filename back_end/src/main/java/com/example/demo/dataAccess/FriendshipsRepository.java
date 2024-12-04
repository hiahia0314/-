package com.example.demo.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipsRepository extends JpaRepository<Friendships,Long> {
//    boolean existsByUserAndFriend(long user, long friend);
//    List<Friendships> findByUserOrFriend(long user,long friend);

//    boolean existsByUserAndFriend(String userId, String friendId);
    Optional<Friendships> findByApplicantAndReceiver(String applicantId, String receiverId);
}
