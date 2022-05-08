/*
 * Kyra Samuel
 * Project 2
 * CMSC405
 * RTriangle.java
 * Desc: Draws a Right Triangle
 */
package project2.ks;

import java.awt.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;


public class RTriangle extends GLJPanel implements GLEventListener{
    private float zPos;
    private float rotAng;

    public RTriangle() {
        super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize( new Dimension(60,60) );
        addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
    }
    
    
    public void display(GLAutoDrawable drawable) {    
        
        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
         
        gl2.glClearColor( 0, 0, 0, 1 );  // (In fact, this is the default.)
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT );
        
        gl2.glLoadIdentity();
        gl2.glTranslatef(0f, 0f, zPos);
        gl2.glRotatef(rotAng, 0f, 0.8f, 0f);

        gl2.glBegin(GL2.GL_TRIANGLES);
        if (rotAng % 2f == 0)
            gl2.glColor3d(1, 0, 0); //red
        else if (rotAng % 3f == 0)
            gl2.glColor3d(0, 0, 1); //blue
        else
            gl2.glColor3d(1, 0.5, 0.31); //coral
        
        gl2.glVertex2d( -0.25, -0.25 );
        gl2.glVertex2d( 0.25, 0.25 );
        gl2.glVertex2d( 0, 0.34 );
        gl2.glEnd();
        
        rotAng += 1f;
        
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

