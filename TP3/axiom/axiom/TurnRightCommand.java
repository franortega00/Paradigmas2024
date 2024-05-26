package axiom;

public class TurnRightCommand extends Command {
    public TurnRightCommand(char commandChar) { super(commandChar); }
    public void execute(Axiom axiom){
        axiom.sonda.sondaSecurityTest();
        axiom.direction = axiom.direction.turnRight();
    }
}
