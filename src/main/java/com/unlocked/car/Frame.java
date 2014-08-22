package com.unlocked.car;


import com.unlocked.Vertex;

import javax.media.opengl.GL2;

/**
 * User: mtolstykh
 * Date: 22.08.14
 * Time: 12:10
 */
public class Frame {

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

    public void draw(GL2 gl2){

        gl2.glBegin(GL2.GL_LINES);
        a.draw(gl2);
        b.draw(gl2);
//        gl2.glEnd();

//        gl2.glBegin(GL2.GL_LINE);
        c.draw(gl2);
        d.draw(gl2);
//        gl2.glEnd();

//        gl2.glBegin(GL2.GL_LINE);
        f.draw(gl2);
        e.draw(gl2);
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
}
