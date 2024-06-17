package uno;

import java.util.*;

public abstract class GameStatus {
    String player;
    GameStatus nextStatus;
    GameStatus previousStatus;
    List<GameStatus> gameStates = new ArrayList<>();


    public GameStatus requestedStatus(String aPlayer) {
        return gameStates.stream()
                .filter(each -> each.applies(aPlayer))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("It's not your turn"));
    }

    public boolean applies(String aPlayer) {
        return this.player.equals(aPlayer);
    }
    public abstract void updateStatus(UNOMatch match);


    public abstract UNOMatch passAs(UNOMatch match);
    public UNOMatch passAsPlayer1(UNOMatch match) { throw new RuntimeException("It's not your turn"); }
    public UNOMatch passAsPlayer2(UNOMatch match) { throw new RuntimeException("It's not your turn"); }


    public abstract UNOMatch drawAs(UNOMatch match);
    public UNOMatch drawAsPlayer1(UNOMatch match) { throw new RuntimeException("It's not your turn"); }
    public UNOMatch drawAsPlayer2(UNOMatch match) { throw new RuntimeException("It's not your turn"); }


    public abstract UNOMatch playsCardAs(UNOMatch match, Card playedCard);
    public UNOMatch playCardAsPlayer1(UNOMatch match, Card playedCard)  { throw new RuntimeException("It's not your turn"); }
    public UNOMatch playCardAsPlayer2(UNOMatch match, Card playedCard)  { throw new RuntimeException("It's not your turn"); }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(player, ((GameStatus) obj).player);
    }

}
