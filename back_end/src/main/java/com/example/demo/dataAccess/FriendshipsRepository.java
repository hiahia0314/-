package com.example.demo.dataAccess;

import com.example.demo.DTO.FriendDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipsRepository extends JpaRepository<Friendships,Long> {
//    boolean existsByUserAndFriend(long user, long friend);
//    List<Friendships> findByUserOrFriend(long user,long friend);

//    boolean existsByUserAndFriend(String userId, String friendId);
    Optional<Friendships> findByApplicantAndReceiver(String applicantId, String receiverId);

    @Transactional
    @Query("delete from Friendships x where x.applicant=(?1) and x.receiver=(?2)")
    @Modifying
    void deleteByApplicantAndReceiver(String applicantId, String receiverId);

    @Transactional
    @Query("SELECT e FROM Friendships e where ( e.applicant=(?1) or e.receiver=(?1)) and e.status='accepted' ")
    @Modifying
    List<Friendships> findByApplicantOrReceiver(String applicantId);

    List<Friendships> findByReceiver(String Receiver);
}
