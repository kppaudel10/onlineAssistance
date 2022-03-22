package com.online_dtie_tracker.repo.todo;

import com.online_dtie_tracker.model.ToDo;
import com.online_dtie_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Integer> {
    @Query(value = "SELECT * FROM tbl_to_do u WHERE u.to_do_date = ?1 and u.user_id = ?2", nativeQuery = true)
    List<ToDo> getTaskByDate(LocalDate localDate,Integer userId);
}
