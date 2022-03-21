package com.online_dtie_tracker.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user",uniqueConstraints = {
        @UniqueConstraint(name = "user_contact",columnNames = "contact"),
        @UniqueConstraint(name = "user_email",columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(generator = "user_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name ="user_sequence",sequenceName = "user_sequence",allocationSize = 10)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
}
