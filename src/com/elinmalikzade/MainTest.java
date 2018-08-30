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

        LinkedList<String> commandsForRobots = new LinkedList<>();
        commandsForRobots.add("RFRFRFRF");
        commandsForRobots.add("FRRFLLFFRRFLL");
        commandsForRobots.add("LLFFFLFLFL");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(commandsForRobots);

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

        LinkedList<String> commandsForRobots = new LinkedList<>();

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(commandsForRobots);

        Assert.assertTrue(results.pop().equals("1 1 E") && results.pop().equals("3 2 N") && results.pop().equals("0 3 W"));
    }

    @org.junit.Test
    public void commandsWithoutRobots() {
        Grid grid = new Grid(5, 3);

        LinkedList<Robot> robots = new LinkedList<>();

        LinkedList<String> commandsForRobots = new LinkedList<>();
        commandsForRobots.add("RFRFRFRF");
        commandsForRobots.add("FRRFLLFFRRFLL");
        commandsForRobots.add("LLFFFLFLFL");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(commandsForRobots);

        Assert.assertTrue(results.isEmpty());
    }

    @org.junit.Test
    public void robotOutOfGridTest() {
        Grid grid = new Grid(5, 3);

        LinkedList<int[]> robotsInitialCoords = new LinkedList<>();
        robotsInitialCoords.add(grid.convertMartianCoordsToInternal(10, 20));

        LinkedList<Robot> robots = new LinkedList<>();
        robots.add(new Robot(robotsInitialCoords.peek()[0], robotsInitialCoords.peek()[1], 'E'));
        robotsInitialCoords.pop();

        LinkedList<String> commandsForRobots = new LinkedList<>();
        commandsForRobots.add("RFRFRFRF");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(commandsForRobots);

        Assert.assertTrue(results.pop().equals("10 20 E"));
    }

    @org.junit.Test
    public void cornerCellBlockBothSides() {
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

        LinkedList<String> commandsForRobots = new LinkedList<>();
        commandsForRobots.add("RF");
        commandsForRobots.add("RFRF");
        commandsForRobots.add("RFRFRF");

        Mars mars = new Mars(grid, robots);

        LinkedList<String> results = mars.moveRobots(commandsForRobots);

        Assert.assertTrue(results.pop().equals("0 3 W LOST") && results.pop().equals("0 3 N LOST") && results.pop().equals("1 3 E"));
    }

}