package com.online_dtie_tracker.repo.todo;

import com.online_dtie_tracker.model.ToDo;
import com.online_dtie_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Integer> {
    @Query(value = "SELECT * FROM tbl_to_do u WHERE u.to_do_date = ?1 and u.user_id = ?2", nativeQuery = true)
    List<ToDo> getTaskByDate(LocalDate localDate,Integer userId);


    @Query(value = "SELECT * FROM tbl_to_do u WHERE u.to_do_date != ?1 and u.user_id = ?2 and u.to_do_status = 0", nativeQuery = true)
    List<ToDo> getPreviousPendingTask(LocalDate localDate, Integer userId);

    @Query(value = "SELECT * FROM tbl_to_do u WHERE u.user_id = ?1 and u.to_do_status = 1", nativeQuery = true)
    List<ToDo> getDoneTask(Integer userId);

    @Query(value = "SELECT * FROM tbl_to_do u WHERE u.user_id = ?1", nativeQuery = true)
    List<ToDo> findAllTaskOfAuthorizedUser(Integer userId);
}
