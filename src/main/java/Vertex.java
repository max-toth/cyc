import javax.media.opengl.GL2;

/**
 * User: mtolstykh
 * Date: 20.08.14
 * Time: 16:25
 */
public class Vertex {
    float x,y,z;

    Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(GL2 gl2) {
        gl2.glVertex3f(this.x, this.y, this.z);
    }

    public void update(Vertex vertex) {
        this.x = vertex.x;
        this.y = vertex.y;
        this.z = vertex.z;

    }
}