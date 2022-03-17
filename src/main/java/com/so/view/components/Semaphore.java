package com.so.view.components;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Semaphore extends JPanel {

    private boolean isActive = true;

    public Semaphore(int width, int height) {
        setLayout(null);
        setSize(width, height);
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    public boolean isActive(){
        return isActive;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Shape circleShape = new Ellipse2D.Double(0, 0, getWidth() - 10, getHeight() - 10);
        g2d.setColor(isActive? Color.GREEN : Color.RED);
        g2d.fill(circleShape);
        g2d.draw(circleShape);
        g2d.setColor(Color.BLACK);
        g2d.draw(circleShape);
    }
    
}
