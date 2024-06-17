package uno;

import java.util.ArrayList;
import java.util.List;

public class Player1Status extends GameStatus {

    public Player1Status(String player) {
        super();
        this.player = player;
    }

    public Player1Status(String aPlayer, GameStatus previousStatus, GameStatus nextStatus) {
        super();
        this.player = aPlayer;
        this.previousStatus = previousStatus;
        this.nextStatus = nextStatus;
        this.gameStates = List.of(this, this.previousStatus, this.nextStatus);
    }


    public UNOMatch passAs(UNOMatch match) {
        return match.passAsPlayer1();
    }
    public UNOMatch passAsPlayer1(UNOMatch match) {
        return match.player1PassAsPlayer1();
    }


    public UNOMatch drawAs(UNOMatch match) {
        return match.drawCardAsPlayer1();
    }
    public UNOMatch drawAsPlayer1(UNOMatch match ) {
        return match.player1DrawCardAsPlayer1();
    }


    public UNOMatch playsCardAs(UNOMatch match, Card playedCard) {
        return match.playCardAsPlayer1(  playedCard);
    }
    public UNOMatch playCardAsPlayer1(UNOMatch match, Card playedCard) {
        return match.player1PlayCardAsPlayer1( playedCard);
    }


    public void updateStatus(UNOMatch match) {
        List<String> playerNames = new ArrayList<>(match.playerCards.keySet());

        int currentPlayerIndex = playerNames.indexOf(match.currentPlayer);

        match.gameStatus = new Player2Status(playerNames.get(currentPlayerIndex),
                            match.gameStatus,
                            new Player1Status(playerNames.get((currentPlayerIndex + 1) % playerNames.size() )));
    }

}
