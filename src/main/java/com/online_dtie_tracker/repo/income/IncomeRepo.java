package com.online_dtie_tracker.repo.income;

import com.online_dtie_tracker.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<Income, Integer> {
}
