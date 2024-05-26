package axiom;

public class RetrieveCommand extends Command {
    public RetrieveCommand(char commandChar) {
        super(commandChar);
    }
    public void execute(Axiom axiom) {
        axiom.sonda = axiom.sonda.RetrieveSonda();
    }
}
