package com.company;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader(args[0]);
            ShapeReader shapeReader = new ShapeReader(reader);
            PrintWriter writer = new PrintWriter(args[1]);
            Shape shape;
            ArrayList<ShapeCompound> shapes = new ArrayList<>();
            while ((shape = shapeReader.readShape()) != null) {
                shapes.add(new ShapeCompound(shape));
                String outStr = shape.getGid() + ": P=" + shape.getPerimeter() + "; S=" + shape.getArea();
                writer.append(outStr).append("\n");
            }
            writer.flush();

            MyFrame frame = new MyFrame("Shapes", 1920, 1080, shapes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
