package uno;

public class GameEndedStatus extends GameStatus{
    public GameEndedStatus( String player ) {
        super();
        this.player = player;
    }
    public void updateStatus(UNOMatch match) { }
    public GameStatus requestedStatus(String aPlayer) { throw new RuntimeException("Game has ended"); }
    public UNOMatch passAs(UNOMatch match) { throw new RuntimeException("Game has ended"); }
    public UNOMatch drawAs(UNOMatch match) { throw new RuntimeException("Game has ended"); }
    public UNOMatch playsCardAs(UNOMatch match, Card playerdCard) { throw new RuntimeException("Game has ended"); }



}
