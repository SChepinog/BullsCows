package ru.bullsncows.passive.passive.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovesDto {

    private String move;
    private int bulls;
    private int cows;
}
