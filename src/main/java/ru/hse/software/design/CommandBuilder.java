package ru.hse.software.design;

import ru.hse.software.design.commands.CatCommand;
import ru.hse.software.design.commands.Command;
import ru.hse.software.design.commands.EchoCommand;
import ru.hse.software.design.commands.EnvironmentCommand;
import ru.hse.software.design.commands.ExitCommand;
import ru.hse.software.design.commands.OuterCommand;
import ru.hse.software.design.commands.PwdCommand;
import ru.hse.software.design.commands.WCCommand;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;


public class CommandBuilder {
    public static Command build(CommandTokens commandToken, Path path, CLI cli,
                                PipedInputStream commandOutput, PipedInputStream errorOutput) {
        InputStream inputStream = new InputStream(new PipedOutputStream());
        OutputStream outputStream = new OutputStream(commandOutput);
        OutputStream errorStream = new OutputStream(errorOutput);
        if (commandToken.getCommand().equals("cat")) {
            return new CatCommand(commandToken.getCommandArgs(), inputStream, outputStream, errorStream);
        }
        if (commandToken.getCommand().equals("echo")) {
            return new EchoCommand(commandToken.getCommandArgs(), inputStream, outputStream, errorStream);
        }
        if (commandToken.getCommand().equals("exit")) {
            return new ExitCommand(cli, inputStream, outputStream, errorStream);
        }
        if (commandToken.getCommand().equals("pwd")) {
            return new PwdCommand(commandToken.getCommandArgs(), inputStream, outputStream, errorStream);
        }
        if (commandToken.getCommand().equals("wc")) {
            return new WCCommand(commandToken.getCommandArgs(), inputStream, outputStream, errorStream);
        }
        if (commandToken.getCommand().equals("environment")) {
            return new EnvironmentCommand(commandToken.getCommandArgs(), inputStream, outputStream, errorStream);
        }
        return new OuterCommand(commandToken.getCommand(), commandToken.getCommandArgs(), inputStream,
            outputStream, path);
    }
}
