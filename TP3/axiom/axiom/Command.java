package axiom;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    private char commandChar;
    public static final String warningUnknownCommand = "WARNING: Unknown command";
    public abstract void execute(Axiom axiom);
    public Command(char commandChar) {
        this.commandChar = commandChar;
    }

    static List<Command> commands = Arrays.asList(new IncreaseSpeedCommand('i'),
            new DecreaseSpeedCommand('s'), new TurnRightCommand('r'),
            new TurnLeftCommand('l'), new DeployCommand('d'), new RetrieveCommand('f'));

    public static Command applies(char command) {
        Command commandToExecute = commands.stream()
                .filter(c -> c.commandChar == command)
                .findAny()
                .orElseThrow(() -> new RuntimeException(warningUnknownCommand));
        return commandToExecute;
    }
}
