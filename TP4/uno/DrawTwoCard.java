package uno;

public class DrawTwoCard extends Card{
    private String color;
    private String action;
    public DrawTwoCard(String color) {
        this.color = color;
        this.action = "Draw 2";
    }

    public void processCard(UNOMatch match, String aPlayer) {
        match.processDrawTwoCard( aPlayer, this );
    }
    public boolean canIGoOnTop(Card pitCard) {
        return pitCard.colorCompatibility(this.color)||pitCard.valueCompatibility(this.action);
    }
    public boolean colorCompatibility(Object color) {
        return this.color.equals(color);
    }
    public boolean valueCompatibility(Object valor) {
        return this.action.equals(valor);
    }


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DrawTwoCard other = (DrawTwoCard) obj;
        return color.equals(other.color) && action.equals(other.action);
    }
}
