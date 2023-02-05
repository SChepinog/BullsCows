package ru.bullsncows.passive.passive.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class GameDto {

    UUID uuid;

    List<MovesDto> moves;
}
