package com.unlocked.awt;

import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.World;
import com.unlocked.objects.car.Car;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    private boolean[] keys;

    public KeyboardListener() {
        this.keys = new boolean[200];
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
        update();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
        update();
    }

    private void update(){
        Drawable drawable = World.drawables.get(World.selected);
        if (drawable instanceof Nonstatic) {
            if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) ((Nonstatic) drawable).moveUp();
            if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) ((Nonstatic) drawable).moveDown();
            if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) ((Car) drawable).turnLeft();
            if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) ((Car) drawable).turnRight();
            if (keys[KeyEvent.VK_SPACE]) ((Nonstatic) drawable).brakes();
            ((Nonstatic) drawable).inertia();
        }
    }
}