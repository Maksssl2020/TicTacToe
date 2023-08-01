package tictactoe.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class TicTacToeEngine {
    private final String[][] playingField =
                    {{" ", "|", " ", "|", " "},
                    {"-", "+", "-", "+", "-"},
                    {" ", "|", " ", "|", " "},
                    {"-", "+", "-", "+", "-"},
                    {" ", "|", " ", "|", " "}};
    private int amountOfEmptyFields = 9;
    private String userGameCharacter;
    private String computerGameCharacter;
    private List<List> winningSituations;
    private final List<Integer> computerMovements = new ArrayList<>();
    private final List<Integer> userMovements = new ArrayList<>();


    protected String startGame(String userFieldNumberChoice) {
        if (amountOfEmptyFields != 0) {
            userMovement(userFieldNumberChoice);
        }
        if (amountOfEmptyFields != 0) {
            computerMovement(getRandomPositionNumber());
        }

        displayGameField();

        return isWinningSituation();
    }

    protected String isWinningSituation() {
        String resultMessage = null;

        if (amountOfEmptyFields != 0) {
            resultMessage = "It's time for the next round!";
        } else if (amountOfEmptyFields == 0) {
            resultMessage = "Nobody won, it's a tie!";
        }

        for (List winningSituation : winningSituations) {
            if (userMovements.containsAll(winningSituation)) {
                resultMessage = "Congratulations! You won the game!";
                break;
            } else if (computerMovements.containsAll(winningSituation)) {
                resultMessage = "Sorry, computer won the game!";
                break;
            }
        }

        return resultMessage;
    }

    protected boolean characterChoice(String userChoice) {
        boolean choiceResult = false;

        if ("x".equalsIgnoreCase(userChoice)) {
            userGameCharacter = userChoice.toUpperCase();
            computerGameCharacter = "O";
            choiceResult = true;
        } else if ("o".equalsIgnoreCase(userChoice)) {
            userGameCharacter = userChoice.toUpperCase();
            computerGameCharacter = "X";
            choiceResult = true;
        }

        return choiceResult;
    }

    protected String getUserCharacter() {
        return userGameCharacter;
    }

    protected int getRandomPositionNumber() {
        return new Random().nextInt(10);
    }

    protected String getComputerGameCharacter() {
        return computerGameCharacter;
    }

    protected boolean computerMovement(int randomFieldNumber) {
        boolean result;
        String computerChoice = String.valueOf(randomFieldNumber);
        result = fieldSelection(computerChoice, getComputerGameCharacter());

        if (result) {
            computerMovements.add(randomFieldNumber);
            amountOfEmptyFields--;
        } else {
            computerMovement(getRandomPositionNumber());
        }

        return result;
    }

    protected boolean userMovement(String userFieldNumberChoice) {
        boolean result;
        result = fieldSelection(userFieldNumberChoice, getUserCharacter());

        if (result) {
            userMovements.add(Integer.parseInt(userFieldNumberChoice));
            amountOfEmptyFields--;
        }

        return result;
    }

    protected boolean fieldSelection(String userChoice, String userCharacter) {
        int userChoiceAsDigit = Integer.parseInt(userChoice);
        boolean result;

        switch (userChoiceAsDigit) {
            case 1 -> result = putCharacterInTheField(userCharacter, 0, 0);
            case 2 -> result = putCharacterInTheField(userCharacter, 0, 2);
            case 3 -> result = putCharacterInTheField(userCharacter, 0, 4);
            case 4 -> result = putCharacterInTheField(userCharacter, 2, 0);
            case 5 -> result = putCharacterInTheField(userCharacter, 2, 2);
            case 6 -> result = putCharacterInTheField(userCharacter, 2, 4);
            case 7 -> result = putCharacterInTheField(userCharacter, 4, 0);
            case 8 -> result = putCharacterInTheField(userCharacter, 4, 2);
            case 9 -> result = putCharacterInTheField(userCharacter, 4, 4);
            default -> result = false;
        }

        return result;
    }

    protected boolean putCharacterInTheField(String character ,int rowPosition, int columnPosition) {
        boolean puttingResult = false;

        if (playingField[rowPosition][columnPosition].isBlank()) {
            playingField[rowPosition][columnPosition] = character;
            puttingResult = true;
        }

        return puttingResult;
    }

    protected String getCharacterFromField(int rowPosition, int columnPosition) {
        return playingField[rowPosition][columnPosition];
    }

    protected void initWinningSituationsList() {
        winningSituations = new ArrayList<>();
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> bottomRow = Arrays.asList(7, 8, 9);
        List<Integer> leftColumn = Arrays.asList(1, 4, 7);
        List<Integer> middleColumn = Arrays.asList(2, 5, 8);
        List<Integer> rightColumn = Arrays.asList(3, 6, 9);
        List<Integer> slantFromLeft = Arrays.asList(1, 5, 9);
        List<Integer> slantFromRight = Arrays.asList(7, 5, 3);

        winningSituations.add(topRow);
        winningSituations.add(middleRow);
        winningSituations.add(bottomRow);
        winningSituations.add(leftColumn);
        winningSituations.add(middleColumn);
        winningSituations.add(rightColumn);
        winningSituations.add(slantFromLeft);
        winningSituations.add(slantFromRight);
    }

    protected List<List> getWinningSituationsList() {
        return winningSituations;
    }

    protected void takeOffEmptyField() {
        amountOfEmptyFields--;
    }

    protected int getAmountOfEmptyFields() {
        return amountOfEmptyFields;
    }

    protected void displayGameField() {
        for (String[] strings : playingField) {
            for (int j = 0; j < playingField.length; j++) {
                System.out.print(strings[j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
