package com.online_dtie_tracker.Dto;

import com.online_dtie_tracker.enums.ToDoStatus;
import com.online_dtie_tracker.model.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDoDto {
    private Integer id;
    private String title;
    private ToDoStatus toDoStatus;
    private Date toDoDate;
    private User user;
}
