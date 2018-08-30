package com.elinmalikzade;

import java.util.Stack;

public class Robot {

    private int x;
    private int y;
    private char orientation;
    private boolean lost;
    private Stack<Character> previosInstructions;

    public Robot(int initialX, int initialY, char initialOrientation) {
        this.x = initialX;
        this.y = initialY;
        this.orientation = initialOrientation;
        this.lost = false;
        this.previosInstructions = new Stack<>();
    }

    public int[] getCurrentCoords() {
        int[] currentCoords = {x, y};
        return currentCoords;
    }

    public char getCurrentOrientation() {
        return orientation;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost() {
        this.lost = true;
    }

    public void act(char instruction) {
        switch (instruction) {
            case 'R':
                turnRight();
                previosInstructions.push(instruction);
                break;
            case 'L':
                turnLeft();
                previosInstructions.push(instruction);
                break;
            case 'F':
                moveForward();
                previosInstructions.push(instruction);
                break;
             default:
                 // if the instruction is not recognized, ignore for now
                 break;
        }
    }

    public void revertLastInstruction() {
        if (previosInstructions.size() > 0) {
            switch (previosInstructions.get(previosInstructions.size() - 1)) {
                case 'R':
                    turnLeft();
                    previosInstructions.pop();
                    break;
                case 'L':
                    turnRight();
                    previosInstructions.pop();
                    break;
                case 'F':
                    moveBackward();
                    previosInstructions.pop();
                    break;
                default:
                    // if the instruction is not recognized, ignore for now
                    break;
            }
        }
    }

    private void turnRight() {
        switch (this.orientation) {
            case 'N':
                this.orientation = 'E';
                break;
            case 'S':
                this.orientation = 'W';
                break;
            case 'E':
                this.orientation = 'S';
                break;
            case 'W':
                this.orientation = 'N';
                break;
        }
    }

    private void turnLeft() {
        switch (orientation) {
            case 'N':
                this.orientation = 'W';
                break;
            case 'S':
                this.orientation = 'E';
                break;
            case 'E':
                this.orientation = 'N';
                break;
            case 'W':
                this.orientation = 'S';
                break;
        }
    }

    private void moveForward() {
        switch (orientation) {
            case 'N':
                this.x = x - 1;
                break;
            case 'S':
                this.x = x + 1;
                break;
            case 'E':
                this.y = y + 1;
                break;
            case 'W':
                this.y = y - 1;
                break;
        }
    }

    private void moveBackward() {
        switch (orientation) {
            case 'N':
                this.x = x + 1;
                break;
            case 'S':
                this.x = x - 1;
                break;
            case 'E':
                this.y = y - 1;
                break;
            case 'W':
                this.y = y + 1;
                break;
        }
    }
}
