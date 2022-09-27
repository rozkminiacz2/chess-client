package tictactoe.game;

import global.game.Coord;
import global.game.Faction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static tictactoe.game.Piece.*;

public class TicTacToeBoardTest {

    private final Faction x = new XPiece();
    private final Faction o = new OPiece();


    @Test
    void shouldEvaluateNextMove() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(O, X, E)
                .withRow1(O, X, E)
                .withRow2(E, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(x)).isEqualTo(new Coord(2, 1));
        assertThat(analyzer.getNextMoveCoord(o)).isEqualTo(new Coord(2, 0));
    }

    @Test
    void shouldEvaluateNextMove2() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, E, O)
                .withRow1(X, X, O)
                .withRow2(E, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(x)).isEqualTo(new Coord(2, 2));
        assertThat(analyzer.getNextMoveCoord(o)).isEqualTo(new Coord(2, 2));
    }

    @Test
    void shouldEvaluateNextMove3() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, E, O)
                .withRow1(X, O, E)
                .withRow2(X, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(x)).isEqualTo(new Coord(0, 0));
        assertThat(analyzer.getNextMoveCoord(o)).isEqualTo(new Coord(0, 0));
    }

    @Test
    void shouldEvaluateNextMove4() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(O, E, E)
                .withRow1(E, O, X)
                .withRow2(E, E, X)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(x)).isEqualTo(new Coord(0, 2));
        assertThat(analyzer.getNextMoveCoord(o)).isEqualTo(new Coord(0, 2));
    }

    @Test
    void shouldEvaluateNextMove5() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, X, O)
                .withRow1(E, X, O)
                .withRow2(E, E, E)
                .build();
        System.out.println(board.toString());
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(x)).usingRecursiveComparison().isEqualTo(new Coord(2, 1));
    }

    @Test
    void shouldEvaluateNextMove6() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(X, O, X)
                .withRow1(O, E, X)
                .withRow2(E, O, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        System.out.println(board.toString());
        assertThat(analyzer.getNextMoveCoord(o)).usingRecursiveComparison().isEqualTo(new Coord(1, 1));
    }

    @Test
    void shouldEvaluateNextMove7() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(X, E, E)
                .withRow1(O, E, X)
                .withRow2(E, O, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        System.out.println(board.toString());
        assertThat(analyzer.getNextMoveCoord(o)).usingRecursiveComparison().isEqualTo(new Coord(1, 1));
    }

    @Test
    void shouldEvaluateNextMove8() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, E, E)
                .withRow1(O, X, X)
                .withRow2(E, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        System.out.println(board.toString());
        assertThat(analyzer.getNextMoveCoord(o))
                .usingRecursiveComparison()
                .isIn(new Coord(0, 2), new Coord(2, 2), new Coord(2, 2), new Coord(0, 0));
    }

    @Test
    void shouldThrowRuntimeExceptionDueToBoardStateValidationFailures() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(O, X, E)
                .withRow1(X, O, X)
                .withRow2(E, E, X)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThatThrownBy(() -> {
            analyzer.getNextMoveCoord(x);
        })
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Board validation failure. Invalid pieces count.");
    }

}
