package com.online_dtie_tracker.repo.user;

import com.online_dtie_tracker.Dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDto, Integer> {
}
