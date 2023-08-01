package tictactoe.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicTacToeUserInterfaceTest {

    private TicTacToeUserInterface userInterface;

    @BeforeEach
    void setUp() {
        userInterface = new TicTacToeUserInterface();
    }

    @Test
    public void testUserChoosingX() {
        String userChoice = "x";

        String resultMessage = userInterface.checkEnteredGameCharacter(userChoice);
        assertEquals("You have chosen X", resultMessage);
    }

    @Test
    public void testUserChoosingO() {
        String userChoice = "O";

        String resultMessage = userInterface.checkEnteredGameCharacter(userChoice);
        assertEquals("You have chosen O", resultMessage);
    }

    @Test
    public void testWrongUserChoice() {
        String userChoice = "d";

        String resultMessage = userInterface.checkEnteredGameCharacter(userChoice);
        assertEquals("Try again! You didn't enter X or O!", resultMessage);
    }

    @Test
    public void testUserGameFieldChoice() {
        String userChoice = "3";

        userInterface.checkEnteredGameCharacter("X");
        String resultMessage = userInterface.checkEnteredGameField(userChoice);
        assertEquals("You have chosen field number 3!", resultMessage);
    }

    @Test
    public void testUserChoosingTheSameFieldTwoTimes() {
        String userChoice = "3";

        userInterface.checkEnteredGameCharacter("X");
        userInterface.checkEnteredGameField(userChoice);
        String resultMessage = userInterface.checkEnteredGameField(userChoice);
        assertEquals("Field number 3 is occupied! Please choose another field!", resultMessage);
    }

    @Test
    public void testUserEnteringNumberGreaterThan9() {
        String userChoice = "11";

        userInterface.checkEnteredGameCharacter("X");
        String resultMessage = userInterface.checkEnteredGameField(userChoice);
        assertEquals("You didn't choose number between 1 and 9!", resultMessage);
    }

    @Test
    public void testUserEnteringNumberLessThan1() {
        String userChoice = "-5";

        userInterface.checkEnteredGameCharacter("X");
        String resultMessage = userInterface.checkEnteredGameField(userChoice);
        assertEquals("You didn't choose number between 1 and 9!", resultMessage);
    }

    @Test
    public void testEnteringCorrectGameCharacter() {
        String userInput = "X";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        String resultMessage = userInterface.askUserForGameCharacter();
        assertEquals("You have chosen X", resultMessage);

        System.setIn(System.in);
    }

    @Test
    public void testEnteringCorrectFieldNumber() {
        String userInput = "4";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inputStream);

        String resultMessage = userInterface.askUserForFieldNumber();
        assertEquals("You have chosen field number 4!", resultMessage);

        System.setIn(System.in);
    }
}
