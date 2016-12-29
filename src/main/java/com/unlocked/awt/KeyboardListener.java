package com.unlocked.awt;

import com.jogamp.opengl.util.gl2.GLUT;
import com.unlocked.Camera;
import com.unlocked.Const;
import com.unlocked.Main;
import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.World;
import com.unlocked.objects.car.Car;
import com.unlocked.objects.car.CarHolder;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardListener extends KeyAdapter {

    private boolean[] keys;

    public KeyboardListener() {
        this.keys = new boolean[200];
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("press " + keyEvent.getKeyCode());
        keys[keyEvent.getKeyCode()] = true;
        update();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println("release " + keyEvent.getKeyCode());
        keys[keyEvent.getKeyCode()] = false;

        update();
    }

    private void update() {
//        Drawable drawable = World.drawables.get(World.selected);
//        if (drawable instanceof Nonstatic) {
        Car car = CarHolder.getCar();
        if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
            Const.deltaStraight = 0.0015f;
            Const.delta = 0;
            //.moveUp();//(car.direction()+0.01f, car.direction() + 0.01f);
        }//Camera.x++;//((Nonstatic) drawable).moveUp();
        if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) {
            Const.deltaStraight = -0.0015f;
            Const.delta = 0;
        }//car.brakes(); //Camera.x--;//((Nonstatic) drawable).moveDown();
        if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
            Const.deltaStraight = 0;
            Const.delta = 0.15f;
            car.turnLeft(true); //Camera.y++;//((Car) drawable).turnLeft();
        }
        if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
            Const.deltaStraight = 0;
            Const.delta = -0.15f;
            car.turnRight(true); //Camera.y--;//((Car) drawable).turnRight();
        }
        if (keys[KeyEvent.VK_SPACE]) {
            Const.deltaStraight = 0;
            Const.delta = 0;
        }//car.stopTurn(); //Camera.y--;//((Car) drawable).turnRight();
//            if (keys[KeyEvent.VK_SPACE]) ((Nonstatic) drawable).brakes();
//            ((Nonstatic) drawable).inertia();
//        }
    }
}