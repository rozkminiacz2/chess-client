package tictactoe.game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static tictactoe.game.Piece.*;

public class TicTacToeBoardTest {

    @Test
    void test() {
        TicTacToeBoard board =
                new TestBoardCreator()
                .withRow1(O, X, E)
                .withRow2(X, E, O)
                .withRow3(E, X, E)
                .build();
        System.out.println(board.toString());
        assertThat(board.isWinningMovePossible(new XPiece())).isTrue();
        assertThat(board.isWinningMovePossible(new OPiece())).isFalse();
        assertThat(board.isDefensiveMoveNeeded(new XPiece())).isFalse();
        assertThat(board.isDefensiveMoveNeeded(new OPiece())).isTrue();
    }


}
