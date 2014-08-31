package com.unlocked.objects.car;

import com.unlocked.MathUtils;
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
    Vertex turnPoint;

    public Car(float length, float width, float wheelRadius, Vertex pos) {
        this.carFrame = new Frame(0.5f, 0.25f, pos);
        this.wheelFrontLeft = new Wheel(carFrame.getA(), wheelRadius);
        this.wheelFrontRight = new Wheel(carFrame.getB(), wheelRadius);
        this.wheelRearLeft = new Wheel(carFrame.getC(), wheelRadius);
        this.wheelRearRight = new Wheel(carFrame.getD(), wheelRadius);
        turnPoint = new Vertex(wheelRearLeft.getCenter().getX(), wheelRearLeft.getCenter().getY(), wheelRearLeft.getCenter().getZ());
    }

    public void turnLeft(){
        this.wheelFrontLeft.turn(0);
        this.wheelFrontRight.turn(0);
        if (wheelFrontLeft.getAlfa() > 0) leftSidePoint();
        if (wheelFrontRight.getAlfa() < 0) rightSidePoint();
    }

    public void turnRight(){
        this.wheelFrontLeft.turn(1);
        this.wheelFrontRight.turn(1);
        if (wheelFrontLeft.getAlfa() > 0) leftSidePoint();
        if (wheelFrontRight.getAlfa() < 0) rightSidePoint();
    }

    private void leftSidePoint() {
        float c = carFrame.getLength() / this.wheelFrontLeft.getAlfa();
        float b = (float) Math.sqrt(c * c - carFrame.getLength() * carFrame.getLength());
        turnPoint = new Vertex(wheelRearLeft.getCenter().getX() - b, wheelRearLeft.getCenter().getY(), 0);
    }

    private void rightSidePoint() {
        float c = carFrame.getLength() / this.wheelFrontRight.getAlfa();
        float b = (float) Math.sqrt(c * c - carFrame.getLength() * carFrame.getLength());
        turnPoint = new Vertex(wheelRearRight.getCenter().getX() + b, wheelRearRight.getCenter().getY(), 0);
    }

    @Override
    public void draw(GL2 gl2) {
        carFrame.draw(gl2);
        wheelFrontLeft.draw(gl2);
        wheelFrontRight.draw(gl2);
        wheelRearLeft.draw(gl2);
        wheelRearRight.draw(gl2);

        if (wheelFrontLeft.getAlfa() > 0) {
            gl2.glBegin(GL2.GL_TRIANGLES);
            wheelFrontLeft.getCenter().draw(gl2);
            wheelRearLeft.getCenter().draw(gl2);
            turnPoint.draw(gl2);
            gl2.glEnd();
        }
        if (wheelFrontLeft.getAlfa() < 0) {
            gl2.glBegin(GL2.GL_TRIANGLES);
            wheelFrontRight.getCenter().draw(gl2);
            wheelRearRight.getCenter().draw(gl2);
            turnPoint.draw(gl2);
            gl2.glEnd();
        }
    }

    @Override
    public void move(float x, float y) {
        carFrame.move(x, y);
        wheelFrontLeft.move(x, y);
        wheelFrontRight.move(x, y);
        wheelRearLeft.move(x, y);
        wheelRearRight.move(x, y);
        turnPoint.move(x, y);
    }

    @Override
    public void moveUp() {
        carFrame.moveUp();
        wheelFrontLeft.moveUp();
        wheelFrontRight.moveUp();
        wheelRearLeft.moveUp();
        wheelRearRight.moveUp();
        turnPoint.moveUp();
    }

    @Override
    public void moveDown() {
        carFrame.moveDown();
        wheelFrontLeft.moveDown();
        wheelFrontRight.moveDown();
        wheelRearLeft.moveDown();
        wheelRearRight.moveDown();
        turnPoint.moveDown();
    }

    @Override
    public void brakes() {
        carFrame.brakes();
        wheelFrontLeft.brakes();
        wheelFrontRight.brakes();
        wheelRearLeft.brakes();
        wheelRearRight.brakes();
        turnPoint.brakes();
    }

    @Override
    public void inertia() {
        carFrame.inertia();
        wheelFrontLeft.inertia();
        wheelFrontRight.inertia();
        wheelRearLeft.inertia();
        wheelRearRight.inertia();
        turnPoint.inertia();
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
