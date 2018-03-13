import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientU3 {
	 public static void main(String[] args) throws UnknownHostException, IOException{
         
	        //------------------------client-socket-start------------------------------------------------
	        Socket client = new Socket("localhost", 2222);
	        System.out.println("Client wurde gestartet");
	         
	        //-------------------------------------------------------------------------------------------
	         
	        //------------------------stream-------------------------------------------------------------
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	        BufferedReader daten = new BufferedReader(new InputStreamReader(System.in)); //System.in, Eingabe am Bildschirm auslesen
	        //-------------------------------------------------------------------------------------------
	         
	        System.out.print("URL: ");
	        out.write(daten.readLine()); //Eingabe durch den Bildschirm / Konsole
	        out.newLine(); //
	        out.flush(); 
	        
	        
	        System.out.println("Ergebnis: " + in.readLine());
	         
	        out.close(); 
	        in.close(); 
	        client.close();
	    }
	}