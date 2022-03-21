package com.online_dtie_tracker.repo.expenses;

import com.online_dtie_tracker.Dto.ExpensesDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface expensesRepo extends JpaRepository<ExpensesDto,Integer> {
}
