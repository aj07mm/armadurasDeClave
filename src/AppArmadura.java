import java.awt.*;

import armadura.*;

public class AppArmadura {
  public static void main(String[] args) {
    armadura myApplet = new armadura(); // define applet of interest
    Frame myFrame = new Frame("Applet Holder"); // create frame with title
    // Call applet's init method (since Java App does not
    // call it as a browser automatically does)
    myApplet.init();	
    // add applet to the frame
    myFrame.add(myApplet, BorderLayout.CENTER);
    myFrame.setBounds(1000, 1000, 700, 600);
    myFrame.pack(); // set window to appropriate size (for its elements)
    myFrame.setVisible(true); // usual step to make frame visible
  } // end main
} // end class
