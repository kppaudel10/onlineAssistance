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
@Table(name = "tbl_income")
public class Income {
    @Id
    @GeneratedValue(generator = "income_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name ="income_sequence",sequenceName = "income_sequence",
            allocationSize = 15)
    private Integer id;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private Double amount;

    @Column(updatable = false)
    private Double fixedAmount;

    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.DATE)
    private Date incomeDate;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_user_income"))
    private User user;

    @ManyToMany(mappedBy = "incomeList")
    private List<Expenses> expensesList;
}
