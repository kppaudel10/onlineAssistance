package com.online_dtie_tracker.Dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpensesDto {
    private Integer id;
    private String expensesSource;
    private Double expensesAmount;
    private Date paidDate;
    private List<IncomeDto> incomeDtoList;
}
