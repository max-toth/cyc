package com.unlocked.awt;

import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.World;
import com.unlocked.objects.car.Car;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by max_tolstykh on 26/08/14.
 */
public class KeyboardListener implements KeyListener {



    private boolean[] keys = new boolean[200];
    public boolean up, down, left, right, brakes, exit;

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
        update();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
        update();
        Drawable drawable = World.drawables.get(World.selected);
        ((Nonstatic) drawable).inertia();
    }

    public void update(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        brakes = keys[KeyEvent.VK_SPACE];
        exit = keys[KeyEvent.VK_ESCAPE];
        Drawable drawable = World.drawables.get(World.selected);
        if (drawable instanceof Nonstatic) {
            if (up) ((Nonstatic) drawable).moveUp();
            if (down) ((Nonstatic) drawable).moveDown();
            if (brakes) ((Nonstatic) drawable).brakes();
            if (left) ((Car) drawable).turnLeft();
            if (right) ((Car) drawable).turnRight();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}