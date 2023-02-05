package ru.bullsncows.passive.passive.model;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Data
@Getter
public class Move {

    private static final Pattern guessPattern = Pattern.compile("\\d{4}");

    private String guess;
    private MoveResult result;

    public Move(String guess) {
        this.guess = guess;
    }

    public boolean isCorrect() {
        return StringUtils.isNotEmpty(guess) && guessPattern.matcher(guess).matches();
    }

    public Move result(MoveResult moveResult) {
        this.result = moveResult;
        return this;
    }

    public Move result(int bulls, int cows) {
        this.result = new MoveResult(bulls, cows);
        return this;
    }
}
