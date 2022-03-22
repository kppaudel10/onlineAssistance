package com.online_dtie_tracker.repo.user;

import com.online_dtie_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM tbl_user u WHERE u.email = ?1 or u.contact = ?1", nativeQuery = true)
    User getUserByUserName(String userName);
}
