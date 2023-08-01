package tictactoe.game;

import java.util.Scanner;

class TicTacToeUserInterface {
    private final TicTacToeEngine gameEngine= new TicTacToeEngine();
    private String userChosenFieldNumber;
    private String userGameCharacter;
    private Scanner getDataFromUser = new Scanner(System.in);

    protected String askUserForGameCharacter() {
        String resultMessage;

        System.out.println("Choose your game letter, you can only choose O or X: ");
        do {
            userGameCharacter = getDataFromUser.nextLine();
            resultMessage = checkEnteredGameCharacter(userGameCharacter);
            System.out.println(resultMessage);
        } while (!resultMessage.contains("You have chosen"));

        gameEngine.characterChoice(userGameCharacter);

        return resultMessage;
    }

    protected String checkEnteredGameCharacter(String enteredGameCharacter) {
        String resultMessage;
        String gameCharacterRegex = "[xXoO]+";

        if (enteredGameCharacter.matches(gameCharacterRegex)) {
            gameEngine.characterChoice(enteredGameCharacter);
            resultMessage = String.format("You have chosen %s", enteredGameCharacter.toUpperCase());
        } else {
            resultMessage = "Try again! You didn't enter X or O!";
        }

        return resultMessage;
    }

    protected String askUserForFieldNumber() {
        String resultMessage;

        System.out.println("Choose a field number between 1 and 9: ");
        do {
            userChosenFieldNumber = getDataFromUser.nextLine();
            resultMessage = checkEnteredGameField(userChosenFieldNumber);
            System.out.println(resultMessage);
        } while (!resultMessage.contains("You have chosen field"));

        return resultMessage;
    }

    protected String checkEnteredGameField(String enteredGameField) {
        String resultMessage;
        String gameFieldsRegex = "[1-9]";

        if (!enteredGameField.matches(gameFieldsRegex)) {
            resultMessage = "You didn't choose number between 1 and 9!";
        } else if (!gameEngine.fieldSelection(enteredGameField, gameEngine.getUserCharacter())) {
            resultMessage = String.format("Field number %s is occupied! Please choose another field!", enteredGameField);
        } else {
            resultMessage = String.format("You have chosen field number %s!", enteredGameField);
        }

        return resultMessage;
    }

    protected String getUserGameCharacter() {
        return userGameCharacter;
    }

    protected String getUserChosenFieldNumber() {
        return userChosenFieldNumber;
    }
}
