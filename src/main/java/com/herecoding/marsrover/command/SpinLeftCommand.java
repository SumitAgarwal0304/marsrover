package com.herecoding.marsrover.command;

import com.herecoding.marsrover.rover.Rover;

/**
 * Created by Sanjeev.Yadav on 1/14/2018..
 */
public class SpinLeftCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.spinLeft();
    }
}
