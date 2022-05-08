/*
 * Kyra Samuel
 * Project 2
 * CMSC405
 * Hexagon.java
 * Desc: Draws a Hexagon
 */
package project2.ks;

import java.awt.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;


public class Hexagon extends GLJPanel implements GLEventListener{
    private float xPos;
    
    public Hexagon() {
        super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize( new Dimension(60,60) );
        addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
    }
    
    public void display(GLAutoDrawable drawable) {    
        
        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
        gl2.glClearColor( 0, 0, 0, 1 );  // (In fact, this is the default.)
        
        gl2.glLoadIdentity();
        gl2.glTranslatef(xPos, 0f, 0f);
        gl2.glClear( GL2.GL_COLOR_BUFFER_BIT );
        
        gl2.glBegin(GL2.GL_POLYGON);
        {
        gl2.glColor3d(0, 0.5, 0); // darkgreen
        gl2.glVertex3d( 0.0 ,0.25,0 );
        gl2.glColor3d(0, 0, 1); // blue
        gl2.glVertex3d( -0.25, 0.1, 0 );
        gl2.glColor3d(0.5, 0, 0); // darkred
        gl2.glVertex3d( -0.25, -0.1, 0 );
        gl2.glVertex3d( 0, -0.25, 0 );
        gl2.glColor3d(0, 0.5, 0); // darkgreen
        gl2.glVertex3d( 0, 0.25, 0 );
        gl2.glColor3d(0, 0, 1); // blue
        gl2.glVertex3d( 0.25, 0.1, 0 );
        gl2.glColor3d(0.5, 0, 0); // darkred
        gl2.glVertex3d( 0.25, -0.1, 0 );
        gl2.glVertex3d( 0, -0.25, 0 );
        }
        gl2.glEnd();
        xPos += 0.01f;
        
        if (xPos == 0.04f)
            xPos = 0f;
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