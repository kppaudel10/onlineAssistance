package com.online_dtie_tracker.repo.user;

import com.online_dtie_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
