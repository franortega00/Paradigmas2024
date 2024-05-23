package axiom;

public class Axiom {
    protected Speedometre speed = new Speedometre.StoppedSpeed();
    protected Navigation direction = new Navigation.North();
    protected Sonda sonda = new Sonda.SondaNotDeployed();

    public int getSpeed() { return speed.value(); }
    public String getDirection() { return direction.heading(); }
    public boolean isSondaDeployed() { return sonda.sondaState(); }

    public Axiom process(String command) {
        command.chars()
                .mapToObj(c -> Command.applies((char) c))
                .forEach(cmd -> cmd.execute(this));

        return this;
    }

    public boolean equals(Object obj) {
        return obj instanceof Axiom &&
                java.util.Objects.equals(speed, ((Axiom) obj).speed) &&
                java.util.Objects.equals(direction, ((Axiom) obj).direction) &&
                java.util.Objects.equals(sonda, ((Axiom) obj).sonda);
        }
}
//TODO CAMBIAR PARAMETROS A PRIVATE (x seguridad)