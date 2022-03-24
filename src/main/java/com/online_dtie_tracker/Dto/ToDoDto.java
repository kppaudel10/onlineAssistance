package com.online_dtie_tracker.Dto;

import com.online_dtie_tracker.enums.ToDoStatus;
import com.online_dtie_tracker.model.User;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoDto {
    private Integer id;

    @NotEmpty(message = "must not be empty")
    private String title;

    private ToDoStatus toDoStatus;

    @Column(updatable = false)
    private Date toDoDate;

    private User user;
}
