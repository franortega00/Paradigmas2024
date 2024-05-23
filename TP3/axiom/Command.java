package axiom;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    protected static final String warningUnknownCommand = "WARNING: Unknown command";
    protected char commandChar;
    public abstract void execute(Axiom axiom);
    public Command(char commandChar) {
        this.commandChar = commandChar;
    }

    static Command increaseSpeedCommand = new IncreaseSpeedCommand('i');
    static Command decreaseSpeedCommand = new DecreaseSpeedCommand('s');
    static Command turnRightCommand = new TurnRightCommand('r');
    static Command turnLeftCommand = new TurnLeftCommand('l');
    static Command deployCommand = new DeployCommand('d');
    static Command retrieveCommand = new RetrieveCommand('f');

    static List<Command> commands = Arrays.asList(increaseSpeedCommand, decreaseSpeedCommand, turnRightCommand, turnLeftCommand, deployCommand, retrieveCommand);

    public static Command applies(char command) {
        Command commandToExecute = commands.stream()
                .filter(c -> c.commandChar == command)
                .findAny()
                .orElseThrow(() -> new RuntimeException(warningUnknownCommand));
        return commandToExecute;
    }
}

class TurnLeftCommand extends Command {
    public TurnLeftCommand(char commandChar) { super(commandChar); }
    public void execute(Axiom axiom) {
        axiom.sonda.sondaSecurityTest();
        axiom.direction = axiom.direction.turnLeft();
    }
}

class TurnRightCommand extends Command {
    public TurnRightCommand(char commandChar) { super(commandChar); }
    public void execute(Axiom axiom){
        axiom.sonda.sondaSecurityTest();
        axiom.direction = axiom.direction.turnRight();
    }
}

class IncreaseSpeedCommand extends Command {
    public IncreaseSpeedCommand(char commandChar) { super(commandChar); }
    public void execute(Axiom axiom) {
        axiom.speed = axiom.speed.IncreaseSpeed();
    }
}

class DecreaseSpeedCommand extends Command {
    public DecreaseSpeedCommand (char commandChar) { super(commandChar); }
    public void execute(Axiom axiom) {
        axiom.sonda.decreaseSpeed(axiom);
    }
}

class DeployCommand extends Command {
    public DeployCommand(char commandChar) {
        super(commandChar);
    }
    public void execute(Axiom axiom){
        axiom.speed.speedSecurityTest();
        axiom.sonda = axiom.sonda.DeploySonda();
    }
}

class RetrieveCommand extends Command {
    public RetrieveCommand(char commandChar) {
        super(commandChar);
    }
    public void execute(Axiom axiom) {
        axiom.sonda = axiom.sonda.RetrieveSonda();
    }
}
