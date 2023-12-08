package banking;

import java.util.List;

public class MasterControl {
    private final CommandProcessor commandProcessor;
    private final CommandValidator commandValidator;
    private final CommandStorage commandStorage;

    public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
                         CommandStorage commandStorage) {
        this.commandProcessor = commandProcessor;
        this.commandValidator = commandValidator;
        this.commandStorage = commandStorage;
    }

    public List<String> start(List<String> commands) {
        for (String command : commands) {
            if (commandValidator.validate(command)) {
                commandProcessor.execute(command);
                commandStorage.storeValidCommand(command);
            } else {
                commandStorage.storeInvalidCommand(command);
            }
        }
        return commandStorage.getOutput();
    }

}