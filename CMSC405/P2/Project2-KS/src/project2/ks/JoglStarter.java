/*
 * Kyra Samuel
 * Project 2
 * CMSC405
 * JoglStarter.java
 * Desc: Using JoglStarter as template
 */
package project2.ks;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.gl2.GLUT;  // for drawing GLUT objects (such as the teapot)


public class JoglStarter extends JPanel implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, ActionListener {   
    private final GLJPanel display;
    private Timer animationTimer;

    private int frameNumber = 0;  // The current frame number for an animation.
    
    private final GLUT glut = new GLUT();  // TODO: For drawing GLUT objects, otherwise, not needed.

    public JoglStarter() {
        GLCapabilities caps = new GLCapabilities(null);
        display = new GLJPanel(caps);
        display.setPreferredSize( new Dimension(650,550) );  // TODO: set display size here
        display.addGLEventListener(this);
        setLayout(new BorderLayout());
        add(display,BorderLayout.CENTER);
        // TODO:  Other components could be added to the main panel.
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(3, 3));
        panel.setPreferredSize( new Dimension(500,500) ); 
        panel.setBackground(Color.BLACK);
        
        // create and add shapes
        Triangle triangle = new Triangle();
        Rectangle rectangle = new Rectangle();
        Rhombus rhombus = new Rhombus();
        Circle circle = new Circle();
        Hexagon hexagon = new Hexagon();
        RTriangle rTriangle = new RTriangle();
        
        panel.add(triangle);
        panel.add(rhombus);
        panel.add(rectangle);
        panel.add(circle);
        panel.add(hexagon);
        panel.add(rTriangle);

        display.add(panel);
        
        // TODO:  Uncomment the next two lines to enable keyboard event handling
        requestFocusInWindow();
        addKeyListener(this);

        // TODO:  Uncomment the next one or two lines to enable mouse event handling
        display.addMouseListener(this);
        display.addMouseMotionListener(this);
        
        //TODO:  Uncomment the following line to start the animation
        startAnimation();
    }

    /**
     * This method is called when the OpenGL display needs to be redrawn.
     * @param drawable
     */
    @Override
    public void display(GLAutoDrawable drawable) {    
        // called when the panel needs to be drawn
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0,0,0,0);
        gl.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT ); // TODO? Omit depth buffer for 2D.

        gl.glMatrixMode(GL2.GL_PROJECTION);  // TODO: Set up a better projection?
        gl.glLoadIdentity();
        gl.glOrtho(-1,1,-1,1,-2,2);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glLoadIdentity();             // Set up modelview transform.
    }

    /**
     * This is called when the GLJPanel is first created.It can be used to initialize
 the OpenGL drawing context.
     * @param drawable
     */
    @Override
    public void init(GLAutoDrawable drawable) {
            // called when the panel is created
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.3F, 0.3F, 0.3F, 1.0F);  // TODO: Set background color

        gl.glEnable(GL2.GL_DEPTH_TEST);  // TODO: Required for 3D drawing, not usually for 2D.
        
        // TODO: Uncomment the following 4 lines to do some typical initialization for 
        // lighting and materials.

        gl.glEnable(GL2.GL_LIGHTING);        // Enable lighting.
        gl.glEnable(GL2.GL_LIGHT0);          // Turn on a light.  By default, shines from direction of viewer.
        gl.glEnable(GL2.GL_NORMALIZE);       // OpenGL will make all normal vectors into unit normals
        gl.glEnable(GL2.GL_COLOR_MATERIAL);  // Material ambient and diffuse colors can be set by glColor*
    }

    /**
     * Called when the size of the GLJPanel changes.Note:  glViewport(x,y,width,height)
 has already been called before this method is called!
     * @param drawable
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // TODO: Add any code required to respond to the size of the display area.
        //             (Not usually needed.)
    }

    /**
     * This is called before the GLJPanel is destroyed.It can be used to release OpenGL resources.
     * @param drawable
     */
    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
    
    // --------------------------- animation support ---------------------------
    
    /* You can call startAnimation() to run an animation.  A frame will be drawn every
     * 30 milliseconds (can be changed in the call to glutTimerFunc.  The global frameNumber
     * variable will be incremented for each frame.  Call pauseAnimation() to stop animating.
     */
    
    private boolean animating;  // True if animation is running.  Do not set directly.
                                // This is set by startAnimation() and pauseAnimation().
    
    private void updateFrame() {
        frameNumber++;
        // TODO:  add any other updating required for the next frame.
    }
    
    public void startAnimation() {
        if ( ! animating ) {
            if (animationTimer == null) {
                animationTimer = new Timer(10, this);
            }
            animationTimer.start();
            animating = true;
        }
    }
    
    public void pauseAnimation() {
        if (animating) {
            animationTimer.stop();
            animating = false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        updateFrame();
        display.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}


