package com.herecoding.marsrover.rover;

import com.herecoding.marsrover.BaseTest;
import com.herecoding.marsrover.Plateau;
import com.herecoding.marsrover.command.Command;
import com.herecoding.marsrover.command.MoveForwardCommand;
import com.herecoding.marsrover.command.SpinLeftCommand;
import com.herecoding.marsrover.command.SpinRightCommand;
import com.herecoding.marsrover.direction.EastDirection;
import com.herecoding.marsrover.direction.NorthDirection;
import com.herecoding.marsrover.direction.WestDirection;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class RoverTest extends BaseTest {

    @Before
    public void setUp() throws Exception {
        direction = new NorthDirection();
        rover = new Rover(plateau, xCoordinate, yCoordinate, direction);
    }

    @Test
    public void whenRequestedRoverCanSpinRight() throws Exception {
        rover.spinRight();
        TestCase.assertEquals(EastDirection.class, rover.getDirection().getClass());
    }

    @Test
    public void whenRequestedRoverCanSpinLeft() throws Exception {
        rover.spinLeft();
        TestCase.assertEquals(WestDirection.class, rover.getDirection().getClass());
    }

    @Test
    public void whenRequestedRoverCanMoveForward() throws Exception {
        rover.moveForward();
        TestCase.assertEquals(++yCoordinate, rover.getCoordinateY());
    }

    @Test
    public void whenRequestedRoverCanMoveBack() throws Exception {
        rover.moveBack();
        TestCase.assertEquals(--yCoordinate, rover.getCoordinateY());
    }

    @Test
    public void whenRequestedRoverCanPrintCurrentPosition() throws Exception {
        TestCase.assertEquals(xCoordinate + " "
                + yCoordinate + " "
                + direction.getClass().getSimpleName().charAt(0), rover.broadcastLocation());
    }

    @Test
    public void whenRequestedRoverCanExecuteCommandAsAList() throws Exception {
        ArrayList<Command> commandArrayList = new ArrayList<>();
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new SpinLeftCommand());
        commandArrayList.add(new MoveForwardCommand());
        commandArrayList.add(new SpinRightCommand());
        rover.executeCommandList(commandArrayList);

        TestCase.assertEquals(1, rover.getCoordinateX());
        TestCase.assertEquals(4, rover.getCoordinateY());
        TestCase.assertEquals(NorthDirection.class, rover.getDirection().getClass());
    }

    @Test
    public void whenHardCodedTestInputIsRunCorrectOutputIsReturned() {
        // Test Input:
        // 5 5
        Plateau plateau = new Plateau(5, 5);

        // 1 2 N
        Rover roverOne = new Rover(plateau, 1, 2, new NorthDirection());

        // LMLMLMLMM
        ArrayList<Command> roverOneCommands = new ArrayList<>();
        roverOneCommands.add(new SpinLeftCommand());
        roverOneCommands.add(new MoveForwardCommand());
        roverOneCommands.add(new SpinLeftCommand());
        roverOneCommands.add(new MoveForwardCommand());
        roverOneCommands.add(new SpinLeftCommand());
        roverOneCommands.add(new MoveForwardCommand());
        roverOneCommands.add(new SpinLeftCommand());
        roverOneCommands.add(new MoveForwardCommand());
        roverOneCommands.add(new MoveForwardCommand());
        roverOne.executeCommandList(roverOneCommands);

        // 3 3 E
        Rover roverTwo = new Rover(plateau, 3, 3, new EastDirection());

        // MMRMMRMRRM
        ArrayList<Command> roverTwoCommands = new ArrayList<>();
        roverTwoCommands.add(new MoveForwardCommand());
        roverTwoCommands.add(new MoveForwardCommand());
        roverTwoCommands.add(new SpinRightCommand());
        roverTwoCommands.add(new MoveForwardCommand());
        roverTwoCommands.add(new MoveForwardCommand());
        roverTwoCommands.add(new SpinRightCommand());
        roverTwoCommands.add(new MoveForwardCommand());
        roverTwoCommands.add(new SpinRightCommand());
        roverTwoCommands.add(new SpinRightCommand());
        roverTwoCommands.add(new MoveForwardCommand());
        roverTwo.executeCommandList(roverTwoCommands);

        // Expected Output:
        // 1 3 N
        assertEquals(1, roverOne.getCoordinateX());
        assertEquals(3, roverOne.getCoordinateY());
        assertEquals(NorthDirection.class, roverOne.getDirection().getClass());

        // 5 1 E
        assertEquals(5, roverTwo.getCoordinateX());
        assertEquals(1, roverTwo.getCoordinateY());
        assertEquals(EastDirection.class, roverTwo.getDirection().getClass());
    }
}
