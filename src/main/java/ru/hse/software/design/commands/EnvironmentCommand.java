package ru.hse.software.design.commands;

import ru.hse.software.design.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores environment variable value with first provided argument being its name and second being its value.
 **/
public class EnvironmentCommand extends Command {
    private final List<String> commandArgs = new ArrayList<>();

    /**
     * Created environment command with given arguments.
     *
     * @param commandArgs command arguments
     **/
    public EnvironmentCommand(List<String> commandArgs) {
        this.commandArgs.addAll(commandArgs);
        this.command = "environment";
    }

    /**
     * Executes 'environment' command with the given arguments.
     *
     * @param input input as string
     * @return 0 in case of successful outcome of the command, 1 otherwise
     **/
    @Override
    public int execute(String input) {
        if (commandArgs.size() != 2) {
            errorStream.println("Command environment needs 2 arguments");
            return 1;
        }
        Environment.set(commandArgs.get(0), commandArgs.get(1));
        return 0;
    }
}