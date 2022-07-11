package com.opencode.practice.assistClass;

import com.opencode.practice.projection.UserView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserScore {
    private UserView user;

    private int score;

    public UserScore(UserView user, int score) {
        this.user = user;
        this.score = score;
    }
}
