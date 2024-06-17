package uno;

public class SkipCard extends Card{
    private String color;
    private String accion;

    public SkipCard(String color) {
        this.color = color;
        this.accion = "Skip";
    }

    public void processCard(UNOMatch partida, String jugador) {
        partida.processSkipCard(jugador, this);
    }

    public boolean canIGoOnTop(Card pitCard) {
      return pitCard.colorCompatibility(this.color)||pitCard.valueCompatibility(this.accion);
    }
    public boolean colorCompatibility(Object color) {
        return this.color.equals(color);
    }
    public boolean valueCompatibility(Object color) {
        return this.accion.equals(color);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SkipCard other = (SkipCard) obj;
        return color.equals(other.color) && accion.equals(other.accion);
    }


}
