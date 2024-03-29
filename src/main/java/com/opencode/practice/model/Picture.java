package com.opencode.practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String pictureName;
    private String author;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    User user;

    public Picture(String pictureName,String author) {

        this.pictureName = pictureName;
        this.author=author;
    }
}
