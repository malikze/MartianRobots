package com.elinmalikzade;

import java.util.ArrayList;
import java.util.Map;

public class GridCell {

    private ArrayList<CommandToIgnore> commandsToIgnore;

    public GridCell() {
        commandsToIgnore = new ArrayList<>();
    }


    //Check if a robot on this cell with the given direction should ignored the given command
    public boolean shouldIgnoreThisCommand(char direction, char command) {
        if (commandsToIgnore.stream().anyMatch(commandToIgnore ->
                        commandToIgnore.getKey() == direction && commandToIgnore.getValue() == command))
            return true;
        return false;
    }

    //Add the command so robots on this cell with the given direction ignore it later
    public void addCommandToIgnoreNextTime(char direction, char command) {
        CommandToIgnore commandToIgnore = new CommandToIgnore(direction, command);
        this.commandsToIgnore.add(commandToIgnore);
    }

    private final class CommandToIgnore implements Map.Entry<Character, Character> {
        private final Character key;
        private Character value;

        public CommandToIgnore(Character key, Character value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Character getKey() {
            return this.key;
        }

        @Override
        public Character getValue() {
            return this.value;
        }

        @Override
        public Character setValue(Character value) {
            this.value = value;
            return this.value;
        }
    }
}
