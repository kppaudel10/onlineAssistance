package com.online_dtie_tracker.Dto;

import com.online_dtie_tracker.model.Income;
import com.online_dtie_tracker.model.User;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpensesDto {
    private Integer id;

    @NotEmpty(message = "must not be empty")
    private String expensesSource;

    @NotNull(message = "must not be null.")
    private Double expensesAmount;

    @Column(updatable = false)
    private Date paidDate;

    private List<Income> incomeList;

    private User user;

}
