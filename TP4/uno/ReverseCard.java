package uno;

public class ReverseCard extends Card{
    private String color;
    private String action;

    public ReverseCard(String color) {
        this.color = color;
        this.action = "Reverse";
    }

    public void processCard(UNOMatch match, String aPlayer) {
        match.processReverseCard(aPlayer, this);
    }

    public boolean canIGoOnTop(Card pitCard) {
            return pitCard.colorCompatibility(this.color)||pitCard.valueCompatibility(this.action);
    }
    public boolean colorCompatibility(Object color) {
        return this.color.equals(color);
    }
    public boolean valueCompatibility(Object color) {
        return this.action.equals(color);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ReverseCard other = (ReverseCard) obj;
        return color.equals(other.color) && action.equals(other.action);
    }
}
