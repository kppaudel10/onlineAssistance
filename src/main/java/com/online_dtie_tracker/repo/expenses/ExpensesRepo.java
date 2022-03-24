package com.online_dtie_tracker.repo.expenses;

import com.online_dtie_tracker.model.Expenses;
import com.online_dtie_tracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Integer> {

}
