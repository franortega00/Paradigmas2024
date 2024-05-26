package axiom;

public class IncreaseSpeedCommand extends Command {
    public IncreaseSpeedCommand(char commandChar) { super(commandChar); }
    public void execute(Axiom axiom) {
        axiom.speed.increaseSpeed();
    }
}
