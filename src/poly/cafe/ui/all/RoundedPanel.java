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

    
}
