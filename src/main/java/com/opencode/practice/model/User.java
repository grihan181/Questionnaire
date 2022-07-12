package com.opencode.practice.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Grihan,Artem
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "AppUser")
public class User extends SecurityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    /**
     * @author Grihan
     */
    @ManyToMany
    @JoinTable(
            name = "UsersAnswer",
            joinColumns = {@JoinColumn(name = "appUser_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")}
    )
    private List<Answer> answers = new LinkedList<>();


}
