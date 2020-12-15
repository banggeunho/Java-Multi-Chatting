package Chat.Sources.Client.Src;

import java.util.*;
import java.io.*;
public class Abob_main {

	public Abob_main() {
		
		/**
		 * Launch the application. sequence : main.java -> GUI.java -> locationToIndex -> GUI.java -> DB.java -> GUI.java
		 *                                 				   main.java -> floydwarshall.java
		 */
		floydwarshall floyd = new floydwarshall();
		GUI frame = new GUI();			
		frame.setVisible(true);
	}
}


