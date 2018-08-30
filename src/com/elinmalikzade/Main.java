package com.elinmalikzade;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        Grid grid = null;
        LinkedList<Robot> robots = new LinkedList<>();
        LinkedList<String> commandsForRobots = new LinkedList<>();
        String fileName = "input.txt";
        int idx = 0;


        //Read input from input.txt file
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //Read each line of input.txt file and create the grid, robots and commands
            for(String textLine; (textLine = br.readLine()) != null;) {
                if (idx == 0) {
                    String[] chars = textLine.split(" ");
                    if (chars == null || chars.length != 2)
                        throw new IllegalArgumentException();

                    grid = new Grid(Integer.parseInt(textLine.split(" ")[0]),
                                    Integer.parseInt(textLine.split(" ")[1]));
                    idx++;
                } else if(idx == 1) {
                    String[] chars = textLine.split(" ");
                    if (chars == null || chars.length != 3)
                        throw new IllegalArgumentException();

                    int[] initialCoords = grid.convertMartianCoordsToInternal(Integer.parseInt(chars[0]), Integer.parseInt(chars[1]));
                    Robot robot = new Robot(initialCoords[0], initialCoords[1], chars[2].charAt(0));
                    robots.add(robot);
                    idx++;
                } else if(idx == 2) {
                    commandsForRobots.add(textLine);
                    idx++;
                } else {
                    if (!textLine.equals(""))
                        throw new IllegalArgumentException();
                    idx = 1;
                }
            }

            Mars mars = new Mars(grid, robots);
            LinkedList<String> results = mars.moveRobots(commandsForRobots);

            //Print results
            results.forEach(str -> System.out.println(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("File format is not correct");
        }
    }

}
