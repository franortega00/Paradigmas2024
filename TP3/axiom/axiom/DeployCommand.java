package axiom;

public class DeployCommand extends Command {
    public DeployCommand(char commandChar) {
        super(commandChar);
    }
    public void execute(Axiom axiom){
        axiom.speed.speedSecurityTest();
        axiom.sonda = axiom.sonda.DeploySonda();
    }
}
