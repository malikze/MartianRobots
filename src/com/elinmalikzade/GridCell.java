package com.elinmalikzade;

import java.util.ArrayList;
import java.util.Map;

public class GridCell {

    private ArrayList<InstructionToIgnore> instructionsToIgnore;

    public GridCell() {
        instructionsToIgnore = new ArrayList<>();
    }


    //Check if a robot on this cell with the given orientation should ignored the given instruction
    public boolean shouldIgnoreThisInstruction(char orientation, char instruction) {
        if (instructionsToIgnore.stream().anyMatch(instructionToIgnore ->
                        instructionToIgnore.getKey() == orientation && instructionToIgnore.getValue() == instruction))
            return true;
        return false;
    }

    //Add the instruction so robots on this cell with the given orientation ignore it later
    public void addInstructionToIgnoreNextTime(char orientation, char instruction) {
        InstructionToIgnore instructionToIgnore = new InstructionToIgnore(orientation, instruction);
        this.instructionsToIgnore.add(instructionToIgnore);
    }

    private final class InstructionToIgnore implements Map.Entry<Character, Character> {
        private final Character key;
        private Character value;

        public InstructionToIgnore(Character key, Character value) {
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
