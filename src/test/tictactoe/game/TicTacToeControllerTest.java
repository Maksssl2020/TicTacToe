package tictactoe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicTacToeControllerTest {

    private TicTacToeController controller;

    @BeforeEach
    void setUp() {
        controller = new TicTacToeController();
    }

    @Test
    public void testWinningResult() {
        String resultMessage = "Congratulations! You won the game!";
        boolean gameResult = controller.checkGameResult(resultMessage);
        assertTrue(gameResult);
    }

    @Test
    public void testLoosingResult() {
        String resultMessage = "Sorry, computer won the game!";
        boolean gameResult = controller.checkGameResult(resultMessage);
        assertTrue(gameResult);
    }

    @Test
    public void testTieResult() {
        String resultMessage = "Nobody won, it's a tie!";
        boolean gameResult = controller.checkGameResult(resultMessage);
        assertTrue(gameResult);
    }

    @Test
    public void testNextRoundResult() {
        String resultMessage = "It's time for the next round!";
        boolean gameResult = controller.checkGameResult(resultMessage);
        assertFalse(gameResult);
    }
}
