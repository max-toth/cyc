package com.unlocked.objects.car;


import com.unlocked.objects.Drawable;
import com.unlocked.objects.Nonstatic;
import com.unlocked.objects.Vertex;

import javax.media.opengl.GL2;

/**
 * User: mtolstykh
 * Date: 22.08.14
 * Time: 12:10
 */
public class Frame implements Nonstatic, Drawable {

    Vertex a,b,c,d,e,f;

    private float length;
    private float width;
    private float csaRadius; // радиус площади поперечного сечения
                     // cross sectional area radius

    public Frame(float length, float width, Vertex a) {
        this.a = a;
        this.b = new Vertex(a.getX() + width, a.getY(), a.getZ());
        this.c = new Vertex(a.getX(), a.getY() + length, a.getZ());
        this.d = new Vertex(a.getX() + width, a.getY() + length, a.getZ());
        this.e = new Vertex(a.getX() + width/2, a.getY(), a.getZ());
        this.f = new Vertex(a.getX() + width/2, a.getY() + length, a.getZ());
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw(GL2 gl2){

        gl2.glBegin(GL2.GL_LINES);

        a.draw(gl2);
        d.draw(gl2);

        b.draw(gl2);
        c.draw(gl2);
//        f.draw(gl2);
//        e.draw(gl2);

        gl2.glEnd();
    }

    public Vertex getA() {
        return a;
    }

    public void setA(Vertex a) {
        this.a = a;
    }

    public Vertex getB() {
        return b;
    }

    public void setB(Vertex b) {
        this.b = b;
    }

    public Vertex getC() {
        return c;
    }

    public void setC(Vertex c) {
        this.c = c;
    }

    public Vertex getD() {
        return d;
    }

    public void setD(Vertex d) {
        this.d = d;
    }

    public Vertex getE() {
        return e;
    }

    public void setE(Vertex e) {
        this.e = e;
    }

    public Vertex getF() {
        return f;
    }

    public void setF(Vertex f) {
        this.f = f;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getCsaRadius() {
        return csaRadius;
    }

    public void setCsaRadius(float csaRadius) {
        this.csaRadius = csaRadius;
    }

    @Override
    public void move(float x, float y) {
        this.a.move(x, y);
        this.b.move(x, y);
        this.c.move(x, y);
        this.d.move(x, y);
        this.e.move(x, y);
        this.f.move(x, y);
    }

    @Override
    public void moveUp() {
        this.a.moveUp();
        this.b.moveUp();
        this.c.moveUp();
        this.d.moveUp();
        this.e.moveUp();
        this.f.moveUp();
    }

    @Override
    public void moveDown() {
        this.a.moveDown();
        this.b.moveDown();
        this.c.moveDown();
        this.d.moveDown();
        this.e.moveDown();
        this.f.moveDown();
    }

    @Override
    public void brakes() {
        this.a.brakes();
        this.b.brakes();
        this.c.brakes();
        this.d.brakes();
        this.e.brakes();
        this.f.brakes();
    }

    @Override
    public void inertia() {
        this.a.inertia();
        this.b.inertia();
        this.c.inertia();
        this.d.inertia();
        this.e.inertia();
        this.f.inertia();
    }

    @Override
    public float velocity() {
        return this.a.velocity();
    }

    @Override
    public float direction() {
        return a.getX();
    }
}
