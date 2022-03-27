package com.online_dtie_tracker.model;

import com.online_dtie_tracker.enums.ToDoStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_toDo")
public class ToDo {
    @Id
    @GeneratedValue(generator = "toDo_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name ="toDO_sequence",sequenceName = "toDo_sequence",allocationSize = 15)
    private Integer id;

    @Column(nullable = false,updatable = false)
    private String title;

    @Column(nullable = false)
    private ToDoStatus toDoStatus;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date toDoDate;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_todo"))
    private User user;
}
