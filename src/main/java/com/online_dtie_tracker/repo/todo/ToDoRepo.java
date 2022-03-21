package com.online_dtie_tracker.repo.todo;

import com.online_dtie_tracker.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<ToDo, Integer> {
}
