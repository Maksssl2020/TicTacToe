package tictactoe.game;

public class TicTacToeController {
    private TicTacToeEngine gameEngine;
    private TicTacToeUserInterface userInterface;

    public TicTacToeController() {
        gameEngine = new TicTacToeEngine();
        userInterface = new TicTacToeUserInterface();
    }

    public void startGame() {
        String gameResultMessage;
        String fieldNumber;
        String gameCharacter;

        gameEngine.initWinningSituationsList();
        System.out.println("It's a playing field: ");
        gameEngine.displayGameField();
        System.out.println();

        userInterface.askUserForGameCharacter();
        gameCharacter = userInterface.getUserGameCharacter();
        gameEngine.characterChoice(gameCharacter);

        do {
            userInterface.askUserForFieldNumber();
            fieldNumber = userInterface.getUserChosenFieldNumber();
            gameResultMessage = gameEngine.startGame(fieldNumber);
            System.out.println(gameResultMessage);
        } while (!checkGameResult(gameResultMessage));
    }

    protected boolean checkGameResult(String resultMessage) {
        boolean result;

        if (resultMessage.contains("You won")) {
            result = true;
        } else if (resultMessage.contains("Nobody won")) {
            result = true;
        } else if (resultMessage.contains("computer won")) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}
