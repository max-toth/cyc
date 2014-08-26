package com.unlocked.awt;

import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.World;

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
//        nonstatic.inertia();
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
            Nonstatic nonstatic = (Nonstatic) drawable;
            if (up) {
                nonstatic.moveUp();
            }
            if (down) nonstatic.moveDown();
            if (brakes) nonstatic.brakes();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                qube.inertia();
//                car.inertia();
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
//                    qube.moveUp();
//                    car.moveUp();
////                    y += qube.V;
//                }
//                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                    qube.brakes();
//                    car.brakes();
////                    y += qube.V;
//                }
//                if (e.getKeyCode() == KeyEvent.VK_DOWN ||
//                        e.getKeyCode() == KeyEvent.VK_S) {
//                    qube.moveDown();
//                    car.moveDown();
////                    y += qube.V;
//                }
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    qube.move(-0.01f, 0);
////                    x -= 0.01f;
//                    car.turnLeft();
//                }
//                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    qube.move(0.01f, 0);
////                    x += 0.01f;
//                    car.turnRight();
//                }
//            }
//        });