package axiom;

public class TurnLeftCommand extends Command {
    public TurnLeftCommand(char commandChar) { super(commandChar); }
    public void execute(Axiom axiom) {
        axiom.sonda.sondaSecurityTest();
        axiom.direction = axiom.direction.turnLeft();
    }
}
