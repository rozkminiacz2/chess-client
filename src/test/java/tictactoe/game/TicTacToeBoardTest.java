package tictactoe.game;

import org.junit.jupiter.api.Test;

import static global.game.CoordManager.coord;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static tictactoe.faction.Piece.*;
import static tictactoe.faction.FactionManager.XPiece;
import static tictactoe.faction.FactionManager.OPiece;

public class TicTacToeBoardTest {

    @Test
    void shouldEvaluateNextMove1() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(O, X, E)
                .withRow1(O, X, E)
                .withRow2(E, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(XPiece())).isEqualTo(coord(2, 1));
        assertThat(analyzer.getNextMoveCoord(OPiece())).isEqualTo(coord(2, 0));
    }

    @Test
    void shouldEvaluateNextMove2() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, E, O)
                .withRow1(X, X, O)
                .withRow2(E, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(XPiece())).isEqualTo(coord(2, 2));
        assertThat(analyzer.getNextMoveCoord(OPiece())).isEqualTo(coord(2, 2));
    }

    @Test
    void shouldEvaluateNextMove3() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, E, O)
                .withRow1(X, O, E)
                .withRow2(X, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(XPiece())).isEqualTo(coord(0, 0));
        assertThat(analyzer.getNextMoveCoord(OPiece())).isEqualTo(coord(0, 0));
    }

    @Test
    void shouldEvaluateNextMove4() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(O, E, E)
                .withRow1(E, O, X)
                .withRow2(E, E, X)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(XPiece())).isEqualTo(coord(0, 2));
        assertThat(analyzer.getNextMoveCoord(OPiece())).isEqualTo(coord(0, 2));
    }

    @Test
    void shouldEvaluateNextMove5() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, X, O)
                .withRow1(E, X, O)
                .withRow2(E, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(XPiece())).usingRecursiveComparison().isEqualTo(coord(2, 1));
    }

    @Test
    void shouldEvaluateNextMove6() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(X, O, X)
                .withRow1(O, E, X)
                .withRow2(E, O, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(OPiece())).usingRecursiveComparison().isEqualTo(coord(1, 1));
    }

    @Test
    void shouldEvaluateNextMove7() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(X, E, E)
                .withRow1(O, E, X)
                .withRow2(E, O, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(OPiece())).usingRecursiveComparison().isEqualTo(coord(1, 1));
    }

    @Test
    void shouldEvaluateNextMove8() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(E, E, E)
                .withRow1(O, X, X)
                .withRow2(E, E, E)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThat(analyzer.getNextMoveCoord(OPiece()))
                .usingRecursiveComparison()
                .isIn(coord(0, 2), coord(2, 0), coord(2, 2), coord(0, 0));
    }

    @Test
    void shouldThrowRuntimeExceptionDueToBoardStateValidationFailures1() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(O, X, E)
                .withRow1(X, O, X)
                .withRow2(E, E, X)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThatThrownBy(() -> analyzer.getNextMoveCoord(XPiece()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Board validation failure. Invalid pieces count.");
    }

    @Test
    void shouldThrowRuntimeExceptionDueToBoardStateValidationFailures2() {
        TicTacToeBoard board = new TestBoardCreator()
                .withRow0(O, O, E)
                .withRow1(X, O, O)
                .withRow2(E, E, X)
                .build();
        TicTacToeBoardAnalyzer analyzer = new TicTacToeBoardAnalyzer(board);
        assertThatThrownBy(() -> analyzer.getNextMoveCoord(XPiece()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Board validation failure. Invalid pieces count.");
    }

}
