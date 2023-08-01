package tictactoe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeEngineTest {

    private TicTacToeEngine game;

    @BeforeEach
    void setUp() {
        game = new TicTacToeEngine();
    }

    @Test
    public void testChoiceXToPlay() {
        String userChoice = "x";
        boolean result = game.characterChoice(userChoice);
        assertTrue(result);
    }

    @Test
    public void testChoiceOToPlay() {
        String userChoice = "O";
        boolean result = game.characterChoice(userChoice);
        assertTrue(result);
    }

    @Test
    public void testChoicePToPlay() {
        String userChoice = "P";
        boolean result = game.characterChoice(userChoice);
        assertFalse(result);
    }

    @Test
    public void testPuttingOneGameCharacterInTheField() {
        String userGameCharacter = "X";
        int rowPosition = 2;
        int columnPosition = 2;
        boolean result;

        result = game.putCharacterInTheField(userGameCharacter ,rowPosition, columnPosition);
        String fieldCharacter = game.getCharacterFromField(rowPosition, columnPosition);
        assertEquals(userGameCharacter, fieldCharacter);
        assertTrue(result);
    }

    @Test
    public void testPuttingTwoGameCharactersInTheSameField() {
        String userGameCharacter = "X";
        int rowPosition = 2;
        int columnPosition = 2;
        boolean result;

        game.putCharacterInTheField(userGameCharacter ,rowPosition, columnPosition);
        result = game.putCharacterInTheField(userGameCharacter ,rowPosition, columnPosition);
        String fieldCharacter = game.getCharacterFromField(rowPosition, columnPosition);

        assertEquals(userGameCharacter, fieldCharacter);
        assertFalse(result);
    }

    @Test
    public void testInitWinningSituationsList() {
        game.initWinningSituationsList();
        assertNotNull(game.getWinningSituationsList());
    }

    @Test
    public void testChoosingFieldToPutCharacter() {
        String userChoice = "1";
        String userCharacter = "X";
        boolean result;

        result = game.fieldSelection(userChoice, userCharacter);
        assertTrue(result);
    }

    @Test
    public void testChoosingTheSameFieldTwoTimesToPutCharacter() {
        String userChoice = "1";
        String userCharacter = "X";
        boolean result;

        game.fieldSelection(userChoice, userCharacter);
        result = game.fieldSelection(userChoice, userCharacter);
        assertFalse(result);
    }

    @RepeatedTest(5)
    public void testRandomFieldNumbersGenerator() {
        int[] confirmedDrawnNumbers = new int[10];
        int confirmedNumbersSum = 0;

        for (int i = 0; i < 100; i++) {
            int drawnNumber = game.getRandomPositionNumber();
            confirmedDrawnNumbers[drawnNumber] = 1;
        }

        for (int confirmedNumber : confirmedDrawnNumbers) {
            confirmedNumbersSum += confirmedNumber;
        }

        assertEquals(10, confirmedNumbersSum);
    }

    @Test
    public void testSelectedComputerGameCharacter() {
        String userGameCharacter = "X";
        game.characterChoice(userGameCharacter);
        String computerGameCharacter = game.getComputerGameCharacter();
        assertEquals("O", computerGameCharacter);
    }

    @Test
    public void testComputerMovement() {
        int randomFieldNumber = game.getRandomPositionNumber();
        game.characterChoice("X");
        boolean result;

        result = game.computerMovement(randomFieldNumber);
        assertTrue(result);
    }

    @Test
    public void testTwoComputerMovementsInTheSameField() {
        int randomFieldNumber = game.getRandomPositionNumber();
        game.characterChoice("O");
        boolean result;

        game.computerMovement(randomFieldNumber);
        result = game.computerMovement(randomFieldNumber);
        assertFalse(result);
    }

    @Test
    public void takeOffOneEmptyField() {
        game.takeOffEmptyField();
        int emptyFields = game.getAmountOfEmptyFields();
        assertEquals(8, emptyFields);
    }

    @Test
    public void testOneUserMove() {
        String userGameCharacter = "O";
        String userFieldNumberChoice = "4";
        game.characterChoice(userGameCharacter);
        boolean result;

        result = game.userMovement(userFieldNumberChoice);
        assertTrue(result);
    }

    @Test
    public void testTwoUserMovementsInTheSameField() {
        String userGameCharacter = "O";
        String userFieldNumberChoice = "4";
        game.characterChoice(userGameCharacter);
        boolean result;

        game.userMovement(userFieldNumberChoice);
        result = game.userMovement(userFieldNumberChoice);
        assertFalse(result);
    }

    @Test
    public void testIsWinningSituationAfterOneRound() {
        game.initWinningSituationsList();
        game.characterChoice("X");

        game.userMovement("1");
        game.computerMovement(2);

        String resultMessage = game.isWinningSituation();
        assertEquals("It's time for the next round!", resultMessage);
    }

    @Test
    public void testIsWinningSituationAfterTwoRounds() {
        game.initWinningSituationsList();
        game.characterChoice("X");

        game.userMovement("1");
        game.computerMovement(2);
        game.userMovement("4");
        game.computerMovement(3);

        String resultMessage = game.isWinningSituation();
        assertEquals("It's time for the next round!", resultMessage);
    }

    @Test
    public void testUserWinningSituation() {
        game.initWinningSituationsList();
        game.characterChoice("X");

        game.userMovement("1");
        game.computerMovement(2);
        game.userMovement("5");
        game.computerMovement(3);
        game.userMovement("9");
        game.computerMovement(5);

        String resultMessage = game.isWinningSituation();
        assertEquals("Congratulations! You won the game!", resultMessage);
    }

    @Test
    public void testComputerWinningSituation() {
        game.initWinningSituationsList();
        game.characterChoice("X");

        game.userMovement("4");
        game.computerMovement(1);
        game.userMovement("3");
        game.computerMovement(5);
        game.userMovement("2");
        game.computerMovement(9);

        String resultMessage = game.isWinningSituation();
        assertEquals("Sorry, computer won the game!", resultMessage);
    }

    @Test
    public void testTieWinningSituation() {
        game.initWinningSituationsList();
        game.characterChoice("X");

        game.userMovement("4");
        game.computerMovement(1);
        game.userMovement("3");
        game.computerMovement(5);
        game.userMovement("2");
        game.computerMovement(8);
        game.userMovement("7");
        game.computerMovement(6);
        game.userMovement("9");

        String resultMessage = game.isWinningSituation();
        assertEquals("Nobody won, it's a tie!", resultMessage);
    }

    @Test
    public void testStartingGame() {
        String userChoice = "X";
        game.characterChoice(userChoice);
        game.initWinningSituationsList();
        String message = game.startGame("3");
        assertEquals("It's time for the next round!", message);
    }
}
