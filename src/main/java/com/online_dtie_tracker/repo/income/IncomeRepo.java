package com.online_dtie_tracker.repo.income;

import com.online_dtie_tracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepo extends JpaRepository<Income, Integer> {

    @Query(value = "SELECT * FROM tbl_income u WHERE u.user_id = ?1 and u.amount > 0", nativeQuery = true)
    List<Income> getAllIncomeList(Integer userId);

    @Query(value = "UPDATE tbl_income amount set amount = ?1 WHERE id = ?2", nativeQuery = true)
    void updateAmount(Double amount, Integer incomeId);

}
