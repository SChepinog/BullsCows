package ru.bullsncows.passive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.bullsncows.passive.dtos.GameDto;
import ru.bullsncows.passive.dtos.MovesDto;
import ru.bullsncows.passive.model.GameModel;
import ru.bullsncows.passive.model.Move;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameDto mapToDto(GameModel savedGameModel);

    @Mappings(value = {
            @Mapping(source = "move.guess", target = "move"),
            @Mapping(source = "move.result.bulls", target = "bulls"),
            @Mapping(source = "move.result.cows", target = "cows")
//            @Mapping(target = "move", expression= "java(move.getGuess())"),
//            @Mapping(target = "bulls", expression = "java(move.getResult().getBulls())"),
//            @Mapping(target = "cows", expression = "java(move.getResult().getCows())")
    })
    MovesDto mapToDto(Move move);
}
