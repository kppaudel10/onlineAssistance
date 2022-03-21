package com.online_dtie_tracker.Dto;

import com.online_dtie_tracker.model.User;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeDto {
    private Integer id;
    private String source;
    private Double amount;
    private Date incomeDate;
    private User user;
    private List<ExpensesDto> expensesDtoList;
}
