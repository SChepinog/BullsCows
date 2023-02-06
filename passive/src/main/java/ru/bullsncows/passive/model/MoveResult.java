package ru.bullsncows.passive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveResult {

    Long id;
    int bulls;
    int cows;
    Move move;

}
