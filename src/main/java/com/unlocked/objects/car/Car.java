package com.unlocked.objects.car;

import com.jogamp.opengl.util.gl2.GLUT;
import com.unlocked.Const;
import com.unlocked.MathUtils;
import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.Vertex;

import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import static java.lang.Math.PI;

/**
 * Created by max_tolstykh on 25/08/14.
 */
public class Car implements Nonstatic, Drawable {
    private Frame carFrame;
    private Wheel wheelFrontLeft;
    private Wheel wheelFrontRight;
    private Wheel wheelRearLeft;
    private Wheel wheelRearRight;
    private Vertex turnPoint;
    private Vertex center;
    private float rangle = 0;

    Boolean isLeftTriangle = false;
    Boolean isRightTriangle = false;
    Boolean fixRangle = false;

    private float tpX, tpY;

    float length, width, wheelRadius;

    public Car(float length, float width, float wheelRadius, Vertex pos) {
        this.length = length;
        this.width = width;
        this.wheelRadius = wheelRadius;

        this.carFrame = new Frame(0.5f, 0.25f, pos);
        center = new Vertex(pos.getX() + width / 2, pos.getY() + length / 2, pos.getZ());
        this.wheelFrontLeft = new Wheel(carFrame.getA(), wheelRadius);
        this.tpX = wheelFrontLeft.getCenter().getX();
        this.tpY = wheelFrontLeft.getCenter().getY();
        this.wheelFrontRight = new Wheel(carFrame.getB(), wheelRadius);
        this.wheelRearLeft = new Wheel(carFrame.getC(), wheelRadius);
        this.wheelRearRight = new Wheel(carFrame.getD(), wheelRadius);
        turnPoint = new Vertex(wheelRearLeft.getCenter().getX(), wheelRearLeft.getCenter().getY(), wheelRearLeft.getCenter().getZ());
    }

    public void stopTurn() {

        float a = (float) (rangle * PI / 180.0f);
        float cosA = (float) Math.cos(a), sinA = (float) Math.sin(a);

        Vertex vertex = carFrame.getA();
        Vertex newA = new Vertex(
                (vertex.getX() - turnPoint.getX()) * cosA - (vertex.getY() - turnPoint.getY() ) * sinA,
                (vertex.getX() - turnPoint.getX()) * sinA + (vertex.getY() - turnPoint.getY() ) * cosA,
                vertex.getZ());

        System.out.println("A " + vertex.toString());
        System.out.println("A after rotation " + newA.toString());

//        Car carUpdated = new Car(length, width, wheelRadius, newA);
//        CarHolder.setCar(carUpdated);

        isLeftTriangle = false;
        isRightTriangle = false;

        fixRangle = true;

        rangle = 0;
    }

    public void turnLeft(boolean onOff) {

        this.wheelFrontLeft.turn(0, onOff);
        this.wheelFrontRight.turn(0, onOff);
//        if (wheelFrontLeft.isMaximum()) return;
        if (wheelFrontLeft.getAlfa() >= 0) leftSidePoint();
        if (wheelFrontRight.getAlfa() < 0) rightSidePoint();

        System.out.println("turn left []");
    }

    public void turnRight(boolean onOff) {
        this.wheelFrontLeft.turn(1, onOff);
        this.wheelFrontRight.turn(1, onOff);
//        if (wheelFrontLeft.isMaximum()) return;
        if (wheelFrontLeft.getAlfa() >= 0) leftSidePoint();
        if (wheelFrontRight.getAlfa() < 0) rightSidePoint();

        System.out.println("turn right []");
    }

    private void leftSidePoint() {
        float c = carFrame.getLength() / this.wheelFrontLeft.getAlfa();
        float b = (float) Math.sqrt(c * c - carFrame.getLength() * carFrame.getLength());
        isLeftTriangle = true;
        isRightTriangle = false;
        turnPoint.setX(wheelRearLeft.getCenter().getX() - b);
        turnPoint.setY(wheelRearLeft.getCenter().getY());
        turnPoint.setZ(wheelRearLeft.getCenter().getZ());
    }

    private void rightSidePoint() {
        float c = carFrame.getLength() / this.wheelFrontRight.getAlfa();
        float b = (float) Math.sqrt(c * c - carFrame.getLength() * carFrame.getLength());
        isRightTriangle = true;
        isLeftTriangle = false;
        turnPoint.setX(wheelRearRight.getCenter().getX() + b);
        turnPoint.setY(wheelRearRight.getCenter().getY());
        turnPoint.setZ(wheelRearRight.getCenter().getZ());
    }

    @Override
    public void draw(GL2 gl2) {
        if (isLeftTriangle) {
            rotation(gl2, rangle);
            leftTriangle(gl2);
        } else if (isRightTriangle) {
            rotation(gl2, -rangle);
            rightTriangle(gl2);
        } else {
            renderCar(gl2);
        }

        if (!fixRangle)
            rangle += Const.delta;
    }

    private void rotation(GL2 gl2, float rangle) {
        fixRangle = false;
        gl2.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl2.glPushMatrix();
        gl2.glTranslatef(turnPoint.getX(), turnPoint.getY(), 0);
        gl2.glRotatef(rangle, 0, 0, 1);
        gl2.glTranslatef(-turnPoint.getX(), -turnPoint.getY(), 0);

        float[] mv = new float[16];
        gl2.glGetFloatv(GLMatrixFunc.GL_MODELVIEW_MATRIX, mv, 0);

        renderCar(gl2);
        gl2.glPopMatrix();
    }

    private void rightTriangle(GL2 gl2) {
        if (wheelFrontLeft.getAlfa() < 0) {
            gl2.glBegin(GL2.GL_LINES);
            wheelFrontRight.getCenter().draw(gl2);
            wheelRearRight.getCenter().draw(gl2);

            wheelRearRight.getCenter().draw(gl2);
            turnPoint.draw(gl2);

            turnPoint.draw(gl2);
            wheelFrontRight.getCenter().draw(gl2);

            wheelFrontLeft.getCenter().draw(gl2);
            wheelRearLeft.getCenter().draw(gl2);

            wheelRearLeft.getCenter().draw(gl2);
            turnPoint.draw(gl2);

            turnPoint.draw(gl2);
            wheelFrontLeft.getCenter().draw(gl2);
            gl2.glEnd();
        }
    }

    private void leftTriangle(GL2 gl2) {
        if (wheelFrontLeft.getAlfa() > 0) {
            gl2.glBegin(GL2.GL_LINES);
            wheelFrontLeft.getCenter().draw(gl2);
            wheelRearLeft.getCenter().draw(gl2);

            wheelRearLeft.getCenter().draw(gl2);
            turnPoint.draw(gl2);

            turnPoint.draw(gl2);
            wheelFrontLeft.getCenter().draw(gl2);

            wheelFrontRight.getCenter().draw(gl2);
            wheelRearRight.getCenter().draw(gl2);

            wheelRearRight.getCenter().draw(gl2);
            turnPoint.draw(gl2);

            turnPoint.draw(gl2);
            wheelFrontRight.getCenter().draw(gl2);
            gl2.glEnd();
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void renderCar(GL2 gl2) {
        carFrame.draw(gl2);
        wheelFrontRight.draw(gl2);
        wheelRearLeft.draw(gl2);
        wheelRearRight.draw(gl2);
        wheelFrontLeft.draw(gl2);
    }

    @Override
    public void move(float x, float y) {
        carFrame.move(x, y);
        wheelFrontLeft.move(x, y);
        wheelFrontRight.move(x, y);
        wheelRearLeft.move(x, y);
        wheelRearRight.move(x, y);
//        turnPoint.move(x, y);
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
//        turnPoint.moveDown();
    }

    @Override
    public void brakes() {
        carFrame.brakes();
        wheelFrontLeft.brakes();
        wheelFrontRight.brakes();
        wheelRearLeft.brakes();
        wheelRearRight.brakes();
//        turnPoint.brakes();
    }

    @Override
    public void inertia() {
        carFrame.inertia();
        wheelFrontLeft.inertia();
        wheelFrontRight.inertia();
        wheelRearLeft.inertia();
        wheelRearRight.inertia();
//        turnPoint.inertia();
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
