/*
 * Kyra Samuel
 * Project 2
 * CMSC405
 * Triangle.java
 * Desc: Draws a Triangle
 */

package project2.ks;

import java.awt.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;


public class Triangle extends GLJPanel implements GLEventListener{
    private float rotAng;
    
    public Triangle() {
        super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize( new Dimension(60,60) );
        addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
    }
    
    
    public void display(GLAutoDrawable drawable) {    
        
        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        
        gl2.glLoadIdentity();
        gl2.glRotatef(rotAng, 0f, 0.8f, 0f);
        gl2.glClearColor( 0, 0, 0, 1 );  // (In fact, this is the default.)
        
        gl2.glBegin(GL2.GL_TRIANGLES);
        gl2.glColor3d( 1, 0, 1 ); // purple
        gl2.glVertex2d( -0.25, -0.25 );
        gl2.glColor3d( 0, 1, 1 ); // cyan
        gl2.glVertex2d( 0.25, -0.25 );
        gl2.glColor3d( 0, 0, 1 ); // blue
        gl2.glVertex2d( 0, 0.4 );
        gl2.glEnd(); 
        gl2.glFlush();
        rotAng += 0.2f;
    } // end display()

    public void init(GLAutoDrawable drawable) {
           // called when the panel is created
    }

    public void dispose(GLAutoDrawable drawable) {
            // called when the panel is being disposed
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
            // called when user resizes the window
    }
    
}
