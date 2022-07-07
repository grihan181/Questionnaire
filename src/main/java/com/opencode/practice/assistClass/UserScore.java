package com.opencode.practice.assistClass;

import com.opencode.practice.model.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserScore {
    private AppUser user;

    private int score;

    public UserScore(AppUser user, Integer score) {
        this.user = user;
        this.score = score;
    }
}
