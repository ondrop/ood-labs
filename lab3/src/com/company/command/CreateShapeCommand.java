package com.company.command;

import com.company.Application;
import com.company.shape.Circle;
import com.company.shape.ShapeCompound;

public class CreateShapeCommand implements Command {

    @Override
    public void execute() {
        ShapeCompound shapeCompound = new ShapeCompound();
        try {
            shapeCompound.add(new Circle());
            Application.getInstance().addShape(shapeCompound);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
