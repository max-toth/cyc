package com.unlocked.objects.car;

import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.Vertex;

import javax.media.opengl.GL2;

/**
 * Created by max_tolstykh on 25/08/14.
 */
public class Car implements Nonstatic, Drawable {
    Frame carFrame;
    Wheel wheelFrontLeft;
    Wheel wheelFrontRight;
    Wheel wheelRearLeft;
    Wheel wheelRearRight;

    public Car(float length, float width, float wheelRadius, Vertex pos) {
        this.carFrame = new Frame(0.5f, 0.25f, pos);
        this.wheelFrontLeft = new Wheel(carFrame.getA(), wheelRadius);
        this.wheelFrontRight = new Wheel(carFrame.getB(), wheelRadius);
        this.wheelRearLeft = new Wheel(carFrame.getC(), wheelRadius);
        this.wheelRearRight = new Wheel(carFrame.getD(), wheelRadius);
    }

    public void turnLeft(){
        this.wheelFrontLeft.turn(0);
        this.wheelFrontRight.turn(0);
    }

    public void turnRight(){
        this.wheelFrontLeft.turn(1);
        this.wheelFrontRight.turn(1);

    }

    @Override
    public void draw(GL2 gl2) {
        carFrame.draw(gl2);
        wheelFrontLeft.draw(gl2);
        wheelFrontRight.draw(gl2);
        wheelRearLeft.draw(gl2);
        wheelRearRight.draw(gl2);
    }

    @Override
    public void move(float x, float y) {
        carFrame.move(x, y);
        wheelFrontLeft.move(x, y);
        wheelFrontRight.move(x, y);
        wheelRearLeft.move(x, y);
        wheelRearRight.move(x, y);
    }

    @Override
    public void moveUp() {
        carFrame.moveUp();
        wheelFrontLeft.moveUp();
        wheelFrontRight.moveUp();
        wheelRearLeft.moveUp();
        wheelRearRight.moveUp();
    }

    @Override
    public void moveDown() {
        carFrame.moveDown();
        wheelFrontLeft.moveDown();
        wheelFrontRight.moveDown();
        wheelRearLeft.moveDown();
        wheelRearRight.moveDown();
    }

    @Override
    public void brakes() {
        carFrame.brakes();
        wheelFrontLeft.brakes();
        wheelFrontRight.brakes();
        wheelRearLeft.brakes();
        wheelRearRight.brakes();
    }

    @Override
    public void inertia() {
        carFrame.inertia();
        wheelFrontLeft.inertia();
        wheelFrontRight.inertia();
        wheelRearLeft.inertia();
        wheelRearRight.inertia();
    }

    @Override
    public float velocity() {
        return carFrame.velocity();
    }

    @Override
    public float direction() {
        return carFrame.a.getX();
    }
}
