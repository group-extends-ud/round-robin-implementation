package com.so.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Semaphore extends JPanel {

    public Semaphore(int width, int height) {

        setLayout(null);

        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape circleShape = new Ellipse2D.Double(0, 0, getWidth() - 10, getHeight() - 10);

        g2d.setColor(Color.RED);

        g2d.draw(circleShape);
    }
    
}
