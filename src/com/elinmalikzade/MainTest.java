package com.elinmalikzade;

import org.junit.Assert;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void defaultTest() {
        Grid grid = new Grid(5, 3);

        LinkedList<int[]> robotsInitialCoords = new LinkedList<>();
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(1, 1));
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(3, 2));
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(0, 3));

        LinkedList<Robot> robots = new LinkedList<>();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'E'));
        robotsInitialCoords.pop();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'N'));
        robotsInitialCoords.pop();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'W'));
        robotsInitialCoords.pop();

        LinkedList<String> instructionsForRobots = new LinkedList<>();
        instructionsForRobots.add("RFRFRFRF");
        instructionsForRobots.add("FRRFLLFFRRFLL");
        instructionsForRobots.add("LLFFFLFLFL");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(instructionsForRobots);

        Assert.assertTrue(results.pop().equals("1 1 E") && results.pop().equals("3 3 N LOST") && results.pop().equals("2 3 S"));
    }

    @org.junit.Test
    public void robotsWithoutCommands() {
        Grid grid = new Grid(5, 3);

        LinkedList<int[]> robotsInitialCoords = new LinkedList<>();
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(1, 1));
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(3, 2));
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(0, 3));

        LinkedList<Robot> robots = new LinkedList<>();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'E'));
        robotsInitialCoords.pop();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'N'));
        robotsInitialCoords.pop();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'W'));
        robotsInitialCoords.pop();

        LinkedList<String> instructionsForRobots = new LinkedList<>();

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(instructionsForRobots);

        Assert.assertTrue(results.pop().equals("1 1 E") && results.pop().equals("3 2 N") && results.pop().equals("0 3 W"));
    }

    @org.junit.Test
    public void commandsWithoutRobots() {
        Grid grid = new Grid(5, 3);

        LinkedList<Robot> robots = new LinkedList<>();

        LinkedList<String> instructionsForRobots = new LinkedList<>();
        instructionsForRobots.add("RFRFRFRF");
        instructionsForRobots.add("FRRFLLFFRRFLL");
        instructionsForRobots.add("LLFFFLFLFL");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(instructionsForRobots);

        Assert.assertTrue(results.isEmpty());
    }

    @org.junit.Test
    public void robotOutOfGrid() {
        Grid grid = new Grid(5, 3);

        LinkedList<int[]> robotsInitialCoords = new LinkedList<>();
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(10, 20));

        LinkedList<Robot> robots = new LinkedList<>();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'E'));
        robotsInitialCoords.pop();

        LinkedList<String> instructionsForRobots = new LinkedList<>();
        instructionsForRobots.add("RFRFRFRF");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(instructionsForRobots);

        Assert.assertTrue(results.pop().equals("10 20 E"));
    }

    @org.junit.Test
    public void cornerCellBlockedBothSides() {
        Grid grid = new Grid(5, 3);

        LinkedList<int[]> robotsInitialCoords = new LinkedList<>();
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(0, 3));
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(0, 3));
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(0, 3));


        LinkedList<Robot> robots = new LinkedList<>();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'S'));
        robotsInitialCoords.pop();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'S'));
        robotsInitialCoords.pop();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'S'));
        robotsInitialCoords.pop();

        LinkedList<String> instructionsForRobots = new LinkedList<>();
        instructionsForRobots.add("RF");
        instructionsForRobots.add("RFRF");
        instructionsForRobots.add("RFRFRF");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(instructionsForRobots);

        Assert.assertTrue(results.pop().equals("0 3 W LOST") && results.pop().equals("0 3 N LOST") && results.pop().equals("1 3 E"));
    }

    @org.junit.Test
    public void unknownCommand() {
        Grid grid = new Grid(5, 3);

        LinkedList<int[]> robotsInitialCoords = new LinkedList<>();
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(0, 3));


        LinkedList<Robot> robots = new LinkedList<>();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'S'));
        robotsInitialCoords.pop();

        LinkedList<String> instructionsForRobots = new LinkedList<>();
        instructionsForRobots.add("FFBLFF");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(instructionsForRobots);

        Assert.assertTrue(results.pop().equals("2 1 E"));
    }

}