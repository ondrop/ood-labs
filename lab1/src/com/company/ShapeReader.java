package com.company;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class ShapeReader extends BufferedReader {

    public ShapeReader(Reader reader) {
        super(reader);
    }

    public Shape readShape() throws Exception {
        String line = this.readLine();
        if (line == null) {
            return null;
        }

        String[] subLine = line.split(":");
        String shapeType = subLine[0];
        String[] shapeArgsLine = subLine[1].split(";");
        ArrayList<String> shapeArgs = new ArrayList<String>();
        for (String shapeArg : shapeArgsLine) {
            shapeArgs.add(shapeArg.split("=")[1]);
        }

        if (shapeType.equals(ShapeType.CIRCLE.toString())) {
            String[] coordinates = shapeArgs.get(0).split(",");
            Point center = new Point(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
            double radius = Double.parseDouble(shapeArgs.get(1));
            return new Circle(center, radius);
        } else if (shapeType.equals(ShapeType.RECTANGLE.toString())) {
            String[] leftTopCoordinates = shapeArgs.get(0).split(",");
            String[] rightBottomCoordinates = shapeArgs.get(1).split(",");
            Point leftTop = new Point(Double.parseDouble(leftTopCoordinates[0]), Double.parseDouble(leftTopCoordinates[1]));
            Point rightBottom = new Point(Double.parseDouble(rightBottomCoordinates[0]), Double.parseDouble(rightBottomCoordinates[1]));

            return new Rectangle(leftTop, rightBottom);
        } else if (shapeType.equals(ShapeType.TRIANGLE.toString())) {
            String[] vertex1Coordinates = shapeArgs.get(0).split(",");
            String[] vertex2Coordinates = shapeArgs.get(1).split(",");
            String[] vertex3Coordinates = shapeArgs.get(2).split(",");
            Point vertex1 = new Point(Double.parseDouble(vertex1Coordinates[0]), Double.parseDouble(vertex1Coordinates[1]));
            Point vertex2 = new Point(Double.parseDouble(vertex2Coordinates[0]), Double.parseDouble(vertex2Coordinates[1]));
            Point vertex3 = new Point(Double.parseDouble(vertex3Coordinates[0]), Double.parseDouble(vertex3Coordinates[1]));
            return new Triangle(vertex1, vertex2, vertex3);
        }

        return null;
    }
}
