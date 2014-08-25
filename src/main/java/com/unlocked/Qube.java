package com.unlocked;

import javax.media.opengl.GL2;

/**
 * User: mtolstykh
 * Date: 20.08.14
 * Time: 16:25
 */
public class Qube implements Nonstatic {
    Vertex A,B,C,D,E,F,G,H;
    float width = 0.1f;

    Qube(Vertex a) {
        A = a;
        B = new Vertex(a.x + width, a.y, a.z);
        C = new Vertex(a.x + width, a.y + width, a.z);
        D = new Vertex(a.x, a.y + width, a.z);

        E = new Vertex(A.x, A.y, A.z + width);
        F = new Vertex(B.x, B.y, B.z + width);
        G = new Vertex(C.x, C.y, C.z + width);
        H = new Vertex(D.x, D.y, D.z + width);
    }

    public void move(float x, float y) {
        this.A.move(x, y);
        this.B.move(x, y);
        this.C.move(x, y);
        this.D.move(x, y);
        this.E.move(x, y);
        this.F.move(x, y);
        this.G.move(x, y);
        this.H.move(x, y);
    }

    @Override
    public void moveUp() {
        this.A.moveUp();
        this.B.moveUp();
        this.C.moveUp();
        this.D.moveUp();
        this.E.moveUp();
        this.F.moveUp();
        this.G.moveUp();
        this.H.moveUp();
    }

    @Override
    public void moveDown() {
        this.A.moveDown();
        this.B.moveDown();
        this.C.moveDown();
        this.D.moveDown();
        this.E.moveDown();
        this.F.moveDown();
        this.G.moveDown();
        this.H.moveDown();
    }

    @Override
    public void brakes() {
        this.A.brakes();
        this.B.brakes();
        this.C.brakes();
        this.D.brakes();
        this.E.brakes();
        this.F.brakes();
        this.G.brakes();
        this.H.brakes();
    }

    @Override
    public void inertia() {
        this.A.inertia();
        this.B.inertia();
        this.C.inertia();
        this.D.inertia();
        this.E.inertia();
        this.F.inertia();
        this.G.inertia();
        this.H.inertia();
    }

    @Override
    public float velocity() {
        return this.A.velocity();
    }

    public void draw(GL2 gl2) {

        gl2.glBegin(GL2.GL_POLYGON);

        A.draw(gl2);
        B.draw(gl2);
        C.draw(gl2);
        D.draw(gl2);

        gl2.glEnd();
        gl2.glBegin(GL2.GL_POLYGON);

        E.draw(gl2);
        F.draw(gl2);
        G.draw(gl2);
        H.draw(gl2);

        gl2.glEnd();
        gl2.glBegin(GL2.GL_POLYGON);

        A.draw(gl2);
        B.draw(gl2);
        F.draw(gl2);
        E.draw(gl2);

        gl2.glEnd();
        gl2.glBegin(GL2.GL_POLYGON);

        C.draw(gl2);
        D.draw(gl2);
        H.draw(gl2);
        G.draw(gl2);

        gl2.glEnd();
        gl2.glBegin(GL2.GL_POLYGON);

        A.draw(gl2);
        E.draw(gl2);
        H.draw(gl2);
        D.draw(gl2);

        gl2.glEnd();
        gl2.glBegin(GL2.GL_POLYGON);

        B.draw(gl2);
        F.draw(gl2);
        G.draw(gl2);
        C.draw(gl2);

        gl2.glEnd();
    }
}
