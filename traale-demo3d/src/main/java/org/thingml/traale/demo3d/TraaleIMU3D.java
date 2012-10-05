/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.traale.demo3d;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.geom.Rectangle2D;
import static javax.media.opengl.GL2.*; // GL2 constants
import org.thingml.traale.desktop.BLEExplorerDialog;
import org.thingml.traale.driver.Traale;
import org.thingml.traale.driver.TraaleListener;
 

@SuppressWarnings("serial")
public class TraaleIMU3D extends GLCanvas implements GLEventListener, TraaleListener {
   // Define constants for the top-level container
   private static String TITLE = "Traale IMU 3D Demo";  // window's title
   private static final int CANVAS_WIDTH = 320;  // width of the drawable
   private static final int CANVAS_HEIGHT = 240; // height of the drawable
   private static final int FPS = 30; // animator's target frames per second
   
   
   protected static BLEExplorerDialog dialog = new BLEExplorerDialog();
 
   /** The entry main() method to setup the top-level container and animator */
   public static void main(String[] args) {
      // Run the GUI codes in the event-dispatching thread for thread safety
      
       
       
       SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {

             dialog.setModal(true);
             dialog.setVisible(true);
       
       if (!dialog.isConnected()) {
           System.err.println("Not connected. Exiting.");
           dialog.disconnect();
           System.exit(0);
       }
            
       final Traale traale = new Traale(dialog.getBgapi(), dialog.getConnection());
       
       System.out.println("Connected.");
             
             
            // Create the OpenGL rendering canvas
            GLCanvas canvas = new TraaleIMU3D();
            traale.addTraaleListener((TraaleIMU3D)canvas);
            traale.subscribeIMU();
            
            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
            // Create a animator that drives canvas' display() at the specified FPS.
            final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);
 
            // Create the top-level container
            final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
            frame.getContentPane().add(canvas);
            frame.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  // Use a dedicate thread to run the stop() to ensure that the
                  // animator stops before program exits.
                  new Thread() {
                     @Override
                     public void run() {
                        if (traale != null) traale.disconnect();
                        if (dialog != null) dialog.disconnect();
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                     }
                  }.start();
               }
            });
            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
            animator.start(); // start the animation loop
         }
      });
   }
 
   // Setup OpenGL Graphics Renderer
 
   private GLU glu;  // for the GL Utility

 
   /** Constructor to setup the GUI for this Component */
   public TraaleIMU3D() {
      this.addGLEventListener(this);
   }
 
   // ------ Implement methods declared in GLEventListener ------
 
   /**
    * Called back immediately after the OpenGL context is initialized. Can be used
    * to perform one-time initialization. Run only once.
    */
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glClearDepth(1.0f);      // set clear depth value to farthest
      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting
   }
 
   /**
    * Call-back handler for window re-size event. Also called when the drawable is
    * first set to visible.
    */
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
 
      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;
 
      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);
 
      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
 
      // Enable the model-view transform
      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }
   
   private TextRenderer renderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 32));
   private float textScaleFactor = 0.01f;
 
   /**
    * Called back by the animator to perform rendering.
    */
   @Override
   public void display(GLAutoDrawable drawable) {
      
       GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
 
    
      // ----- Render the Color Cube -----
      gl.glLoadIdentity();                // reset the current model-view matrix
      gl.glTranslatef(0.0f, 0.0f, -5.0f); 
      
      float scale = (float)Math.sqrt(qx * qx + qy * qy + qz * qz);
      
      gl.glRotatef((float)Math.acos(qw) * 2.0f * 180.0f / (float)Math.PI, qx/scale, qy/scale, qz/scale); // rotate about the x, y and z-axes
      
      gl.glBegin(GL_QUADS); // of the color cube
 
      // Top-face
      gl.glColor3f(0.0f, 0.75f, 0.0f); // green
      gl.glVertex3f(0.60f, 0.20f, -1.0f);
      gl.glVertex3f(-0.60f, 0.20f, -1.0f);
      gl.glVertex3f(-0.60f, 0.20f, 1.0f);
      gl.glVertex3f(0.60f, 0.20f, 1.0f);
 
      // Bottom-face
      gl.glColor3f(1.0f, 0.5f, 0.0f); // orange
      gl.glVertex3f(0.60f, -0.20f, 1.0f);
      gl.glVertex3f(-0.60f, -0.20f, 1.0f);
      gl.glVertex3f(-0.60f, -0.20f, -1.0f);
      gl.glVertex3f(0.60f, -0.20f, -1.0f);
      
      
          
      
 
      // Front-face
      gl.glColor3f(1.0f, 0.0f, 0.0f); // red
      gl.glVertex3f(0.60f, 0.20f, 1.0f);
      gl.glVertex3f(-0.60f, 0.20f, 1.0f);
      gl.glVertex3f(-0.60f, -0.20f, 1.0f);
      gl.glVertex3f(0.60f, -0.20f, 1.0f);
 
      // Back-face
      gl.glColor3f(1.0f, 1.0f, 0.0f); // yellow
      gl.glVertex3f(0.60f, -0.20f, -1.0f);
      gl.glVertex3f(-0.60f, -0.20f, -1.0f);
      gl.glVertex3f(-0.60f, 0.20f, -1.0f);
      gl.glVertex3f(0.60f, 0.20f, -1.0f);
 
      // Left-face
      gl.glColor3f(0.0f, 0.0f, 1.0f); // blue
      gl.glVertex3f(-0.60f, 0.20f, 1.0f);
      gl.glVertex3f(-0.60f, 0.20f, -1.0f);
      gl.glVertex3f(-0.60f, -0.20f, -1.0f);
      gl.glVertex3f(-0.60f, -0.20f, 1.0f);
 
      // Right-face
      gl.glColor3f(1.0f, 0.0f, 1.0f); // magenta
      gl.glVertex3f(0.60f, 0.20f, -1.0f);
      gl.glVertex3f(0.60f, 0.20f, 1.0f);
      gl.glVertex3f(0.60f, -0.20f, 1.0f);
      gl.glVertex3f(0.60f, -0.20f, -1.0f);
 
      gl.glEnd(); // of the color cube
      
      /*
      GL2 gl = drawable.getGL().getGL2();
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

    gl.glMatrixMode(GL2.GL_MODELVIEW);
    gl.glLoadIdentity();
    glu.gluLookAt(0, 0, 10,
                  0, 0, 0,
                  0, 1, 0);

    // Base rotation of cube
    float scale = (float)Math.sqrt(qx * qx + qy * qy + qz * qz);  
    gl.glRotatef((float)Math.acos(qw) * 2.0f * 180.0f / (float)Math.PI, qx/scale, qy/scale, qz/scale);

    // Six faces of cube
    // Top face
    gl.glPushMatrix();
    gl.glRotatef(-90, 1, 0, 0);
    drawFace(gl, 1.0f, 0.2f, 0.2f, 0.8f, "Top");
    gl.glPopMatrix();
    // Front face
    drawFace(gl, 1.0f, 0.8f, 0.2f, 0.2f, "Front");
    // Right face
    gl.glPushMatrix();
    gl.glRotatef(90, 0, 1, 0);
    drawFace(gl, 1.0f, 0.2f, 0.8f, 0.2f, "Right");
    // Back face    
    gl.glRotatef(90, 0, 1, 0);
    drawFace(gl, 1.0f, 0.8f, 0.8f, 0.2f, "Back");
    // Left face    
    gl.glRotatef(90, 0, 1, 0);
    drawFace(gl, 1.0f, 0.2f, 0.8f, 0.8f, "Left");
    gl.glPopMatrix();
    // Bottom face
    gl.glPushMatrix();
    gl.glRotatef(90, 1, 0, 0);
    drawFace(gl, 1.0f, 0.8f, 0.2f, 0.8f, "Bottom");
    gl.glPopMatrix();
    */
      
      
 
   }
 
   private void drawFace(GL2 gl,
                        float faceSize,
                        float r, float g, float b,
                        String text) {
    float halfFaceSize = faceSize / 2;
    // Face is centered around the local coordinate system's z axis,
    // at a z depth of faceSize / 2
    gl.glColor3f(r, g, b);
    gl.glBegin(GL2.GL_QUADS);
    gl.glVertex3f(-halfFaceSize, -halfFaceSize, halfFaceSize);
    gl.glVertex3f( halfFaceSize, -halfFaceSize, halfFaceSize);
    gl.glVertex3f( halfFaceSize,  halfFaceSize, halfFaceSize);
    gl.glVertex3f(-halfFaceSize,  halfFaceSize, halfFaceSize);
    gl.glEnd();

    // Now draw the overlaid text. In this setting, we don't want the
    // text on the backward-facing faces to be visible, so we enable
    // back-face culling; and since we're drawing the text over other
    // geometry, to avoid z-fighting we disable the depth test. We
    // could plausibly also use glPolygonOffset but this is simpler.
    // Note that because the TextRenderer pushes the enable state
    // internally we don't have to reset the depth test or cull face
    // bits after we're done.
    renderer.begin3DRendering();
    gl.glDisable(GL2.GL_DEPTH_TEST);
    gl.glEnable(GL2.GL_CULL_FACE);
    // Note that the defaults for glCullFace and glFrontFace are
    // GL_BACK and GL_CCW, which match the TextRenderer's definition
    // of front-facing text.
    Rectangle2D bounds = renderer.getBounds(text);
    float w = (float) bounds.getWidth();
    float h = (float) bounds.getHeight();
    renderer.draw3D(text,
                    w / -2.0f * textScaleFactor,
                    h / -2.0f * textScaleFactor,
                    halfFaceSize,
                    textScaleFactor);
    renderer.end3DRendering();
  }
   
   /**
    * Called back before the OpenGL context is destroyed. Release resource such as buffers.
    */
   @Override
   public void dispose(GLAutoDrawable drawable) { }

    @Override
    public void skinTemperature(double temp) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void skinTemperatureInterval(int value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void humidity(int t1, int h1, int t2, int h2) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void humidityInterval(int value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    float qw = 0.0f;
    float qx = 0.0f;
    float qy = 0.0f;
    float qz = 0.0f;
    
    @Override
    public void imu(int w, int x, int y, int z, int ax, int ay, int az, int gx, int gy, int gz) {
        //throw new UnsupportedOperationException("Not supported yet.");
        qw = w/((float)(1<<14));
        qx = y/((float)(1<<14));
        qy = z/((float)(1<<14));
        qz = x/((float)(1<<14));
        System.out.println("Quaternion = " + qw + "\t" + qx + "\t" + qy + "\t" + qz );
    }

    @Override
    public void imuMode(int value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imuInterrupt(int value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void magnetometer(int x, int y, int z) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void magnetometerInterval(int value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void battery(int battery) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void manufacturer(String value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void model_number(String value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void serial_number(String value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hw_revision(String value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void fw_revision(String value) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}