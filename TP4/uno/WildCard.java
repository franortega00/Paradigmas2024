package uno;

public class WildCard extends Card {
    private String accion;
    private String color;

    public WildCard() {
        this.accion = "Wild";
    }

    public void processCard(UNOMatch partida, String jugador) {
        partida.processWildCard(jugador, this);
    }

    public WildCard color(String color) {
        this.color = color;
        return this;
    }

    public boolean canIGoOnTop(Card pitCard) {
        return true;
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
        WildCard other = (WildCard) obj;
        return accion.equals(other.accion);
    }

}
