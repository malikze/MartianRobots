package com.elinmalikzade;

import java.util.LinkedList;

public class Mars {

    Grid grid;
    LinkedList<Robot> robots;

    public Mars(Grid grid, LinkedList<Robot> robots) {
        this.grid = grid;
        this.robots = robots;
    }

    // Move each robot on Mars with the corresponding instructions in the commands list
    public LinkedList<String> moveRobots(LinkedList<String> instructionsForRobots) {
        LinkedList<String> results = new LinkedList<>();
        robots.forEach(robot -> {
            if (robot.getCurrentCoords()[0] < 0 || robot.getCurrentCoords()[0] >= grid.getCells().length ||
                robot.getCurrentCoords()[1] < 0 || robot.getCurrentCoords()[1] >= grid.getCells()[0].length) {
                instructionsForRobots.pop();
                results.add(moveRobot(robot, ""));
            } else {
                if (!instructionsForRobots.isEmpty()) {
                    String commands = instructionsForRobots.pop();
                    results.add(moveRobot(robot, commands));
                } else {
                    results.add(moveRobot(robot, ""));
                }
            }
        });
        return results;
    }

    // Move a robot with instructions
    private String moveRobot(Robot robot, String instructions) {
        for (char c : instructions.toCharArray()) {
            if (!this.grid.getCells()[robot.getCurrentCoords()[0]][robot.getCurrentCoords()[1]].shouldIgnoreThisCommand(robot.getCurrentOrientation(), c)) {
                robot.act(c);
                if (robot.getCurrentCoords()[0] < 0 || robot.getCurrentCoords()[0] > this.grid.getCells().length ||
                        robot.getCurrentCoords()[1] < 0 || robot.getCurrentCoords()[1] > this.grid.getCells()[0].length) {
                    // the robot is lost...
                    // make sure this instructon is ignored on this cell by robots with this orientation
                    robot.setLost();
                    robot.revertLastInstruction();
                    this.grid.getCells()[robot.getCurrentCoords()[0]][robot.getCurrentCoords()[1]].addInstructionToIgnoreNextTime(robot.getCurrentOrientation(), c);

                    break;
                }
            }
        }

        int[] finalCoords = this.grid.convertInternalCoordsToMartian(robot.getCurrentCoords()[0], robot.getCurrentCoords()[1]);
        String result = finalCoords[0] + " " + finalCoords[1] + " " + robot.getCurrentOrientation();
        if (robot.isLost())
            result += " LOST";

        return result;
    }


}
