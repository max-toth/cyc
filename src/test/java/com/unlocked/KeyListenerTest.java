package com.unlocked;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by max_tolstykh on 31/08/14.
 */
public class KeyListenerTest {

    @Test
    public void testKeyListener(){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("KL test");
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode() + " pressed");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e.getKeyCode() + " released");
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
