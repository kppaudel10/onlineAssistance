package com.online_dtie_tracker.repo.todo;

import com.online_dtie_tracker.Dto.ToDoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<ToDoDto,Integer> {
}
