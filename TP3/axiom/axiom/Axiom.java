package axiom;

public class Axiom {
    public OficialSpeed speed = new OficialSpeed();
    public Navigation direction = new Navigation.North();
    public Sonda sonda = new Sonda.SondaNotDeployed();

    public int getSpeed() { return speed.value(); }
    public String getDirection() { return direction.heading(); }
    public boolean isSondaDeployed() { return sonda.sondaState(); }

    public Axiom process(String command) {
        command.chars()
                .mapToObj(c -> Command.applies((char) c))
                .forEach(cmd -> cmd.execute(this));
        return this;
    }
}