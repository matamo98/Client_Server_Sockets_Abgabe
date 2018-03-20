import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
 

public class Client{
    public static void main(String[] args) throws IOException{
    	
    	//------------------------server-socket-start------------------------------------------------
    	Socket socket = new Socket("localHost", 6666);
    	
    	//------------------------stream-------------------------------------------------------------
    	DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        //-------------------------------------------------------------------------------------------

        String operation = null;
        boolean abfrage = false;
        
        while (abfrage==false){
        	
        	int auswahl = JOptionPane.showOptionDialog(null, "Waehle zwischen Zeit oder Berechnung!",  "ServerClient_07", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[] {"Zeit", "Berechnung","Abbruch"}, "default");
        	
        	switch(auswahl) {
        		case 0:	operation = "zeit";break;
        		case 1:	operation = "berechnung";break;
        		case 2:	operation = "abbruch";break;
        		//default:break;
        	}
        	
        	if (operation.equals("abbruch")) { // Abfrage um die While Schleife zu beenden
        		abfrage=true;
        		break;
            }
        	
        	out.writeUTF(operation); //operation wird an Server übergeben
	        String answer = in.readUTF(); //Client bekommt antwort
	        System.out.println(answer); //Gibt Ergebnis aus
        	
        }
        
        socket.close();
        in.close();
        out.close();
    }
}