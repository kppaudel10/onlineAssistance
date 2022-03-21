package com.online_dtie_tracker.repo.income;

import com.online_dtie_tracker.Dto.IncomeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<IncomeDto,Integer> {
}
