package com.online_dtie_tracker.Dto;

import com.online_dtie_tracker.model.Expenses;
import com.online_dtie_tracker.model.User;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeDto {
    private Integer id;

    @NotEmpty(message = "must not be empty")
    private String source;

    @NotNull(message = "must not be null")
    private Double amount;

    private Date incomeDate;

    private User user;

    private List<Expenses> expensesList;
}
