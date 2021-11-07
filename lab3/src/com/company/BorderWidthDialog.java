package com.company;

import javax.swing.*;
import java.awt.*;

public class BorderWidthDialog extends JDialog {

    final int[] WIDTHS = {1, 3, 6};

    private int returnValue;

    public BorderWidthDialog() {
        this((Frame)null);
    }

    public BorderWidthDialog(Dialog owner) {
        super(owner);
        this.initialize(owner);
    }

    public BorderWidthDialog(Frame owner) {
        super(owner);
        this.initialize(owner);
    }

    public static int showDialog(Window owner, String title) {
        BorderWidthDialog dialog;
        if (!(owner instanceof Frame) && owner != null) {
            if (!(owner instanceof Dialog)) {
                throw new IllegalArgumentException("the owner (" + owner.getClass().getName() + ") must be a java.awt.Frame or a java.awt.Dialog");
            }

            dialog = new BorderWidthDialog((Dialog)owner);
        } else {
            dialog = new BorderWidthDialog((Frame)owner);
        }

        if (title == null) {
            dialog.setTitle("Choose border width");
        } else {
            dialog.setTitle(title);
        }

        dialog.pack();
        dialog.setVisible(true);
        return dialog.getValue();
    }

    private void initialize(Component owner) {
        this.setModal(true);
        this.setResizable(false);
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        ButtonGroup btnGroup = new ButtonGroup();
        for (int width : WIDTHS) {
            JButton btn = new JButton(String.valueOf(width));
            btn.addActionListener(event -> {
                JButton sourceBtn = (JButton) event.getSource();
                this.returnValue = Integer.parseInt(sourceBtn.getText());
                dispose();
            });
            btnGroup.add(btn);
            add(btn);
        }
        this.pack();
        this.setLocationRelativeTo(owner);
    }


    public int getValue() {
        return this.returnValue;
    }
}
