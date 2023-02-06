package ru.bullsncows.passive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Move")
public class Move {

    private static final Pattern guessPattern = Pattern.compile("\\d{4}");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guess;
    @ManyToOne
    private GameModel game;
    private int bulls;
    private int cows;

    public Move(String guess) {
        this.guess = guess;
    }

    public boolean isCorrect() {
        return StringUtils.isNotEmpty(guess) && guessPattern.matcher(guess).matches();
    }

    public Move result(MoveResult moveResult) {
        this.bulls = moveResult.getBulls();
        this.cows = moveResult.getCows();
        return this;
    }

}
