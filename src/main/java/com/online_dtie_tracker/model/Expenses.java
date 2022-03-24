package com.online_dtie_tracker.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_expenses")
public class Expenses {
    @Id
    @GeneratedValue(generator = "expenses_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "expenses_sequence", sequenceName = "expenses_sequence",
            allocationSize = 25)
    private Integer id;

    @Column(nullable = false)
    private String expenses_source;

    @Column(nullable = false)
    private Double expensesAmount;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date paidDate;

    @ManyToMany
    @JoinTable(
            name = "tbl_income_expenses",
            joinColumns = @JoinColumn(name = "income_id"),
            inverseJoinColumns = @JoinColumn(name = "expenses_id")
    )
    private List<Income> incomeList;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_expenses"))
    private User user;


}
