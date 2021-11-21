package com.company;

import com.company.listener.FrameKeyListener;
import com.company.memento.Caretaker;
import com.company.memento.Originator;
import com.company.shape.ShapeCompound;
import com.company.state.*;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Application extends JFrame {

    private static Application uniqueInstance;

    private HistoryState historyNotOverwrittenState;
    private HistoryState historyOverwrittenState;

    private HistoryState historyState;

    private boolean shiftPressed = false;
    private Point prevPt;
    private ShapeCompound choosedComponent;
    private ShapePanel shapePanel;
    private ToolPanel toolPanel;
    private Caretaker caretaker;

    protected ArrayList<ShapeCompound> shapes = new ArrayList<ShapeCompound>();

    private Application(String frameTitle, int width, int height) {
        super();
        run(frameTitle, width, height);
    }

    private void run(String frameTitle, int width, int height) {
        historyNotOverwrittenState = new HistoryNotOverwrittenState();
        historyOverwrittenState = new HistoryOverwrittenState();

        historyState = historyNotOverwrittenState;

        Originator originator = new Originator();
        originator.setState(new ArrayList<>());
        caretaker = new Caretaker(originator);

        this.setLayout(new BorderLayout());

        toolPanel = new ToolPanel();
        add(toolPanel, BorderLayout.NORTH);

        shapePanel = new ShapePanel();
        shapePanel.setBackground(Color.WHITE);
        add(shapePanel, BorderLayout.CENTER);

        this.setSize(width, height);
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.addKeyListener(new FrameKeyListener());
    }

    public static Application getInstance() {
        if (uniqueInstance == null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            double width = screenSize.getWidth();
            double height = screenSize.getHeight();

            uniqueInstance = new Application("Frame", (int)width, (int)height);
        }

        return uniqueInstance;
    }

    public void addShape(ShapeCompound shapeCompound) {
        this.shapes.add(shapeCompound);
    }

    @Override
    public void paint(Graphics graphics) {

        this.shapePanel.setShapes(getHistoryState().getShapes());

        if (getSelectedCompounds().isEmpty()) {
            caretaker.save();
        }

        super.paint(graphics);
    }

    public static ArrayList<ShapeCompound> parseStateShapes(ArrayList<ArrayList<JSONObject>> state) {
        ArrayList<ShapeCompound> parsedShapes = new ArrayList<>();
        for (ArrayList<JSONObject> shapes : state) {
            ShapeCompound compound = new ShapeCompound();
            for (JSONObject shapeData : shapes) {
                try {
                    com.company.shape.Shape shape = (com.company.shape.Shape) Class.forName((String) shapeData.get(com.company.shape.Shape.TYPE_FIELD)).getConstructor().newInstance();
                    shape.setData(shapeData);
                    compound.add(shape);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            parsedShapes.add(compound);
        }

        return parsedShapes;
    }

    public Point getPrevPt() {
        return prevPt;
    }

    public void setPrevPt(Point newPrevPt) {
        prevPt = newPrevPt;
    }

    public boolean getShiftPressed() {
        return shiftPressed;
    }

    public void changeShiftPressed(boolean newShiftPressed) {
        shiftPressed = newShiftPressed;
    }

    public ArrayList<ShapeCompound> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<ShapeCompound> newShapes) {
        this.shapes = newShapes;
    }

    public ShapeCompound getChoosedComponent() {
        return choosedComponent;
    }

    public void setChoosedComponent(ShapeCompound newChoosedComponent) {
        choosedComponent = newChoosedComponent;
    }

    public void removeShape(ShapeCompound shapeCompound) {
        shapes.remove(shapeCompound);
    }

    public ArrayList<ShapeCompound> getSelectedCompounds() {
        ArrayList<ShapeCompound> selectedCompounds = new ArrayList<>();
        for (ShapeCompound shapeCompound : shapes) {
            if (shapeCompound.isSelected()) {
                selectedCompounds.add(shapeCompound);
            }
        }

        return selectedCompounds;
    }

    public ShapePanel getShapePanel() {
        return shapePanel;
    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public ArrayList<ArrayList<JSONObject>> getAppState() {
        ArrayList<ArrayList<JSONObject>> appState = new ArrayList<>();
        for (ShapeCompound shapeCompound : shapes) {
            appState.add(shapeCompound.getChildStates());
        }

        return appState;
    }

    public HistoryState getHistoryState() {
        return historyState;
    }

    public void setHistoryState(HistoryState historyState) {
        this.historyState = historyState;
    }

    public HistoryState getHistoryNotOverwrittenState() {
        return historyNotOverwrittenState;
    }

    public HistoryState getHistoryOverwrittenState() {
        return historyOverwrittenState;
    }
}
