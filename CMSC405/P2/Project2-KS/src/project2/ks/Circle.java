/*
 * Kyra Samuel
 * Project 2
 * CMSC405
 * Circle.java
 * Desc: Draws a Circle
 */
package project2.ks;

import java.awt.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.*;


public class Circle extends GLJPanel implements GLEventListener{
    private float scale = 0.5f;
    
    public Circle() {
        super( new GLCapabilities(null) ); // Makes a panel with default OpenGL "capabilities".
        setPreferredSize( new Dimension(60,60) );
        addGLEventListener(this); // A listener is essential! The listener is where the OpenGL programming lives.
    }
    
    public void display(GLAutoDrawable drawable) {    
        
        GL2 gl2 = drawable.getGL().getGL2(); // The object that contains all the OpenGL methods.
        
        gl2.glScalef(1f, scale, 1f );
        gl2.glBegin(GL2.GL_POLYGON);
        for(int i=0; i< 64; i++) {
         double theta = 2* i * Math.PI/64;
         double y = 0.3* Math.sin(theta);
         double x = 0.2* Math.cos(theta);
         gl2.glColor3d(1, 0, 1);
         gl2.glVertex2d(x,y);
         gl2.glColor3d(1, 0, 0);
        }
        
        gl2.glEnd();
        scale += 0.1f;
        
        if (scale == 1.2f)
            scale = -0.5f;
        
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
