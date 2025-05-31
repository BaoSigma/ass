/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.ui.all;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author baoha
 */
public class RoundedPanel extends JPanel{
    private int cornerRadius;
    private Color startColor;
    private Color endColor;

    public RoundedPanel(int radius, Color startColor, Color endColor) {
        this.cornerRadius = radius;
        this.startColor = startColor;
        this.endColor = endColor;
        setOpaque(false); // Cho phép vẽ nền tùy chỉnh
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Gradient từ trên xuống dưới
        GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        g2.dispose();

        // Vẽ các component con (nút, label, v.v.)
        super.paintComponent(g);
    }
}
