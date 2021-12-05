package com.company;

import com.company.builder.ShapeBuilder;
import com.company.builder.ShapeDirector;
import com.company.listener.FrameKeyListener;
import com.company.memento.Caretaker;
import com.company.memento.Originator;
import com.company.shape.ShapeCompound;
import com.company.state.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.company.shape.Shape.CHILDREN_FIELD;

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
        originator.setState(new JSONArray());
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

    public static synchronized Application getInstance() {
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

    public static ArrayList<ShapeCompound> parseStateShapes(JSONArray state) {
        ArrayList<ShapeCompound> parsedShapes = new ArrayList<>();
        for (int i = 0; i < state.length(); i++) {
            ShapeCompound compound = parseCompoundChildren(state.getJSONObject(i));
            if (compound != null) {
                parsedShapes.add(compound);
            }
        }

        return parsedShapes;
    }

    public static ShapeCompound parseCompoundChildren(JSONObject compound) {
        if (!compound.has(CHILDREN_FIELD)) {
            try {
                ShapeCompound shapeCompound = new ShapeCompound();
                com.company.shape.Shape shape = (com.company.shape.Shape) Class.forName((String) compound.get(com.company.shape.Shape.TYPE_FIELD)).getConstructor().newInstance();
                ShapeBuilder builder = shape.getBuilder();
                ShapeDirector.createShape(builder, compound);
                shapeCompound.add(builder.getResult());
                return shapeCompound;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        JSONArray children = compound.getJSONArray(CHILDREN_FIELD);
        if (children != null && !children.isEmpty()) {
            ShapeCompound shapeCompound = new ShapeCompound();
            for (int i = 0; i < children.length(); i++) {
                JSONObject child = children.getJSONObject(i);
                if (child.has(CHILDREN_FIELD)) {
                    ShapeCompound compoundChildren = parseCompoundChildren(child);
                    if (compoundChildren != null) {
                        shapeCompound.add(compoundChildren);
                    }
                } else {
                    try {
                        com.company.shape.Shape shape = (com.company.shape.Shape) Class.forName((String) child.get(com.company.shape.Shape.TYPE_FIELD)).getConstructor().newInstance();
                        ShapeBuilder builder = shape.getBuilder();
                        ShapeDirector.createShape(builder, child);
                        shapeCompound.add(builder.getResult());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            return shapeCompound;
        }

        return null;
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

    public JSONArray getAppState() {
        JSONArray appState = new JSONArray();
        for (ShapeCompound shapeCompound : shapes) {
            appState.put(shapeCompound.getState());
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
