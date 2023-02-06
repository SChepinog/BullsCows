package ru.bullsncows.passive.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.bullsncows.passive.dtos.GameDto;
import ru.bullsncows.passive.dtos.MovesDto;
import ru.bullsncows.passive.model.GameModel;
import ru.bullsncows.passive.model.Move;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-06T15:39:31+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GameDto mapToDto(GameModel savedGameModel) {
        if ( savedGameModel == null ) {
            return null;
        }

        GameDto gameDto = new GameDto();

        gameDto.setUuid( savedGameModel.getUuid() );
        gameDto.setMoves( moveListToMovesDtoList( savedGameModel.getMoves() ) );

        return gameDto;
    }

    @Override
    public MovesDto mapToDto(Move move) {
        if ( move == null ) {
            return null;
        }

        MovesDto movesDto = new MovesDto();

        movesDto.setMove( move.getGuess() );
        movesDto.setBulls( move.getBulls() );
        movesDto.setCows( move.getCows() );

        return movesDto;
    }

    protected List<MovesDto> moveListToMovesDtoList(List<Move> list) {
        if ( list == null ) {
            return null;
        }

        List<MovesDto> list1 = new ArrayList<MovesDto>( list.size() );
        for ( Move move : list ) {
            list1.add( mapToDto( move ) );
        }

        return list1;
    }
}
