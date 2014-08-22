import javax.media.opengl.GL2;

/**
 * User: mtolstykh
 * Date: 20.08.14
 * Time: 16:25
 */
public class Qube {
    Vertex A,B,C,D,E,F,G,H;
    float width = 0.01f;
    float V = 0.0f;
    float a = 0.0f;
    float brakesPower = 0.0005f;
    boolean intertia;

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
        if (V > 0.0f && intertia) V -= a;
        if (V < 0.0f && intertia) V += a;

        this.A.x += x;
        this.B.x += x;
        this.C.x += x;
        this.D.x += x;
        this.E.x += x;
        this.F.x += x;
        this.G.x += x;
        this.H.x += x;

        this.A.y += y;
        this.B.y += y;
        this.C.y += y;
        this.D.y += y;
        this.E.y += y;
        this.F.y += y;
        this.G.y += y;
        this.H.y += y;
    }

    public void moveDown() {
        intertia = false;
        if (V > -0.003f) {
            a += 0.0001f - PhysicConstants.At;
            V -= a;
        }

        move(0, V);
    }

    public void moveUp() {
        intertia = false;
        if (a < 1.0f) {
            a += 0.0001f - PhysicConstants.At;
            V += a;
        }

        move(0, V);
    }

    public void brakes() {
        if (V > 0) {
            if (V > 0.001) V -= brakesPower;
            else V = 0;
        }
        if (V < 0) {
            if (V < -0.001f) V += brakesPower;
            else V = 0;
        }
        intertia = false;
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
