package com.opencode.practice.assistClass;

import com.opencode.practice.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserScore {
    private User user;

    private int score;

    public UserScore(User user, int score) {
        this.user = user;
        this.score = score;
    }
}
