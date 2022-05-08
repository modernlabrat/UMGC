/*
 * Kyra Samuel
 * Project 2
 * CMSC405
 * Rectangle.java
 * Desc: Draws a Rectangle
 */
package project2.ks;

import java.awt.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;

/**
 *
 * @author kyras
 */
public class Rectangle extends GLJPanel implements GLEventListener  {
    private float xPos;
    private float yPos;

    /**
     *
     */
    public Rectangle() {
        super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize( new Dimension(60,60) );
        addGLEventListener(this);
    }
    
    public void display(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
        gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
        gl2.glClearColor(0, 0, 0, 0);
        
        gl2.glLoadIdentity();
        gl2.glTranslatef(xPos, yPos, 0f);
        gl2.glBegin(GL2.GL_POLYGON);
        {
        gl2.glColor3d(255, 140, 0); // orange red
        gl2.glVertex3d(-0.2, -0.2, 0.0);
        gl2.glColor3d(255, 215, 0); // gold
        gl2.glVertex3d( 0.2, -0.2, 0.0);
        gl2.glColor3d(255, 0, 0); //  red
        gl2.glVertex3d( 0.2, 0.2, 0.0);
        gl2.glColor3d(255, 215, 0); // gold
        gl2.glVertex3d(-0.2, 0.2, 0.0);
        }
        gl2.glEnd();
        gl2.glFlush();
        
        xPos += 0.5f;
        yPos -= 0.5f;
        
        if (xPos == 16f)
            xPos = 0f;
        
        if (yPos == -16f)
            yPos = 0f;
    }
    
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
