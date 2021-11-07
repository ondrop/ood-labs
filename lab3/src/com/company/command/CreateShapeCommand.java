package com.company.command;

import com.company.Application;
import com.company.shape.Shape;
import com.company.shape.ShapeCompound;

public class CreateShapeCommand implements Command {

    private String shapeName;

    public CreateShapeCommand(String shapeName) {
        this.shapeName = shapeName;
    }

    @Override
    public void execute() {
        ShapeCompound shapeCompound = new ShapeCompound();
        try {
            Shape shape = (Shape) Class.forName(shapeName).getConstructor().newInstance();
            shapeCompound.add(shape);

            Application frame = Application.getInstance();
            frame.addShape(shapeCompound);
            
            frame.repaint();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
