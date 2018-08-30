package com.elinmalikzade;

import java.util.Stack;

public class Robot {

    private int x;
    private int y;
    private char direction;
    private boolean lost;
    private Stack<Character> previosCommands;

    public Robot(int initialX, int initialY, char initialDirection) {
        this.x = initialX;
        this.y = initialY;
        this.direction = initialDirection;
        this.lost = false;
        this.previosCommands = new Stack<>();
    }

    public int[] getCurrentCoords() {
        int[] currentCoords = {x, y};
        return currentCoords;
    }

    public void setCurrentCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public char getCurrentDirection() {
        return direction;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost() {
        this.lost = true;
    }

    public void act(char command) {
        switch (command) {
            case 'R':
                turnRight();
                previosCommands.push(command);
                break;
            case 'L':
                turnLeft();
                previosCommands.push(command);
                break;
            case 'F':
                moveForward();
                previosCommands.push(command);
                break;
             default:
                 // if command is not recognized, ignore for now
                 break;
        }
    }

    public void revertLastCommand() {
        if (previosCommands.size() > 0) {
            switch (previosCommands.get(previosCommands.size() - 1)) {
                case 'R':
                    turnLeft();
                    previosCommands.pop();
                    break;
                case 'L':
                    turnRight();
                    previosCommands.pop();
                    break;
                case 'F':
                    moveBackward();
                    previosCommands.pop();
                    break;
                default:
                    // if command is not recognized, ignore for now
                    break;
            }
        }
    }

    private void turnRight() {
        switch (this.direction) {
            case 'N':
                this.direction = 'E';
                break;
            case 'S':
                this.direction = 'W';
                break;
            case 'E':
                this.direction = 'S';
                break;
            case 'W':
                this.direction = 'N';
                break;
        }
    }

    private void turnLeft() {
        switch (direction) {
            case 'N':
                this.direction = 'W';
                break;
            case 'S':
                this.direction = 'E';
                break;
            case 'E':
                this.direction = 'N';
                break;
            case 'W':
                this.direction = 'S';
                break;
        }
    }

    private void moveForward() {
        switch (direction) {
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
        switch (direction) {
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
