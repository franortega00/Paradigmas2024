package uno;

import java.util.Objects;

public class NumberCard extends Card{
    private String color;
    private int value;
    public NumberCard(int valor, String color) {
        this.value = valor;
        this.color = color;
    }

    public void processCard(UNOMatch match, String aPlayer) {
        match.processNumberCard( aPlayer, this );
    }
    public boolean canIGoOnTop(Card pitCard) {
        return pitCard.colorCompatibility(this.color)||pitCard.valueCompatibility(this.value);
    }
    public boolean colorCompatibility(Object color) {
        return this.color.equals(color);
    }
    public boolean valueCompatibility(Object aValue) {
        return Objects.equals(this.value, aValue);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NumberCard other = (NumberCard) obj;
        return value == other.value && color.equals(other.color);
    }


}
