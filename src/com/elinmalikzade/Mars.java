package com.elinmalikzade;

import java.util.LinkedList;

public class Mars {

    Grid grid;
    LinkedList<Robot> robots;

    public Mars(Grid grid, LinkedList<Robot> robots) {
        this.grid = grid;
        this.robots = robots;
    }

    // Move each robot on Mars with the corresponding commands in the commands list
    public LinkedList<String> moveRobots(LinkedList<String> commandsForRobots) {
        LinkedList<String> results = new LinkedList<>();
        robots.forEach(robot -> {
            if (robot.getCurrentCoords()[0] < 0 || robot.getCurrentCoords()[0] >= grid.getCells().length ||
                robot.getCurrentCoords()[1] < 0 || robot.getCurrentCoords()[1] >= grid.getCells()[0].length) {
                commandsForRobots.pop();
                results.add(moveRobot(robot, ""));
            } else {
                if (!commandsForRobots.isEmpty()) {
                    String commands = commandsForRobots.pop();
                    results.add(moveRobot(robot, commands));
                } else {
                    results.add(moveRobot(robot, ""));
                }
            }
        });
        return results;
    }

    // Move a robot with commands
    private String moveRobot(Robot robot, String commands) {
        for (char c : commands.toCharArray()) {
            if (!this.grid.getCells()[robot.getCurrentCoords()[0]][robot.getCurrentCoords()[1]].shouldIgnoreThisCommand(robot.getCurrentDirection(), c)) {
                robot.act(c);
                if (robot.getCurrentCoords()[0] < 0 || robot.getCurrentCoords()[0] > this.grid.getCells().length ||
                        robot.getCurrentCoords()[1] < 0 || robot.getCurrentCoords()[1] > this.grid.getCells()[0].length) {
                    // the robot is lost...
                    // make sure this command is not acted upon again on this cell by robots with this direction
                    robot.setLost();
                    robot.revertLastCommand();
                    this.grid.getCells()[robot.getCurrentCoords()[0]][robot.getCurrentCoords()[1]].addCommandToIgnoreNextTime(robot.getCurrentDirection(), c);

                    break;
                }
            }
        }

        int[] finalCoords = this.grid.convertInternalCoordsToMartian(robot.getCurrentCoords()[0], robot.getCurrentCoords()[1]);
        String result = finalCoords[0] + " " + finalCoords[1] + " " + robot.getCurrentDirection();
        if (robot.isLost())
            result += " LOST";

        return result;
    }


}
