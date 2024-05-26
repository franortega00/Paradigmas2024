package axiom;

public class DecreaseSpeedCommand extends Command {
    public DecreaseSpeedCommand (char commandChar) { super(commandChar); }
    public void execute(Axiom axiom) {
        axiom.sonda.decreaseSpeed(axiom);
    }
}
