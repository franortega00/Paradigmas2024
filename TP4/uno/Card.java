package uno;

public abstract class Card {
    boolean calledUNO = false;

    public abstract void processCard(UNOMatch match, String aPlayer);

    public Card uno( ) {
        calledUNO = true;
        return this;
    }

    public abstract boolean canIGoOnTop(Card pitCard);
    public abstract boolean colorCompatibility(Object color);
    public abstract boolean valueCompatibility(Object value);


}
