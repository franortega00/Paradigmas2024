package uno;

import java.util.ArrayList;
import java.util.List;

public class Player2Status extends GameStatus{

    public Player2Status(String player) {
        super();
        this.player = player;
    }

    public Player2Status(String aPlayer, GameStatus previousStatus, GameStatus nextStatus) {
        super();
        this.player = aPlayer;
        this.previousStatus = previousStatus;
        this.nextStatus = nextStatus;
        this.gameStates = List.of(this, this.previousStatus, this.nextStatus);
    }

    public UNOMatch passAs(UNOMatch match) {
        return match.passAsPlayer2();
    }
    public UNOMatch passAsPlayer2(UNOMatch match) {
        return match.player2PassAsPlayer2();
    }


    public UNOMatch drawAs(UNOMatch match) {
        return match.drawCardAsPlayer2();
    }
    public UNOMatch drawAsPlayer2(UNOMatch match ) {
        return match.player2DrawCardAsPlayer2();
    }


    public UNOMatch playsCardAs(UNOMatch match, Card playedCard) {
        return match.playCardAsPlayer2(  playedCard);
    }
    public UNOMatch playCardAsPlayer2(UNOMatch match, Card playedCard) {
        return match.player2PlayCardAsPlayer2( playedCard);
    }


    public void updateStatus(UNOMatch match) {
        List<String> playerNames = new ArrayList<>(match.playerCards.keySet());

        int currentPlayerIndex = playerNames.indexOf(match.currentPlayer);

        match.gameStatus = new Player1Status(playerNames.get(currentPlayerIndex),
                            match.gameStatus,
                            new Player1Status(playerNames.get((currentPlayerIndex + 1) % playerNames.size() )));
    }

}
