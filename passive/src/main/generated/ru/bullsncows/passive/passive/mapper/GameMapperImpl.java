package ru.bullsncows.passive.passive.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.bullsncows.passive.passive.dtos.GameDto;
import ru.bullsncows.passive.passive.dtos.MovesDto;
import ru.bullsncows.passive.passive.model.GameModel;
import ru.bullsncows.passive.passive.model.Move;
import ru.bullsncows.passive.passive.model.MoveResult;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-05T19:31:48+0300",
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
        movesDto.setBulls( moveResultBulls( move ) );
        movesDto.setCows( moveResultCows( move ) );

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

    private int moveResultBulls(Move move) {
        if ( move == null ) {
            return 0;
        }
        MoveResult result = move.getResult();
        if ( result == null ) {
            return 0;
        }
        int bulls = result.getBulls();
        return bulls;
    }

    private int moveResultCows(Move move) {
        if ( move == null ) {
            return 0;
        }
        MoveResult result = move.getResult();
        if ( result == null ) {
            return 0;
        }
        int cows = result.getCows();
        return cows;
    }
}
