import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	 public static void main(String[] args) throws UnknownHostException{
		 try {
	        //------------------------Client-socket-start------------------------------------------------
	        Socket client = new Socket("localhost", 3333);
	        System.out.println("Client wurde gestartet");
	        //-------------------------------------------------------------------------------------------
	         
	        //------------------------Stream-------------------------------------------------------------
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	        BufferedReader daten = new BufferedReader(new InputStreamReader(System.in)); //System.in, Eingabe am Bildschirm auslesen
	        //-------------------------------------------------------------------------------------------
	        
	        try {
		    //------------------------Eingabe------------------------------------------------------------
		        System.out.print("URL: ");
		        out.write(daten.readLine()); //Eingabe durch den Bildschirm / Konsole
		        out.newLine();
		        out.flush();
		    //-------------------------------------------------------------------------------------------
		        
		        FileWriter fw = new FileWriter("Save_URL.txt");
		        BufferedWriter bw = new BufferedWriter(fw);
		        String einspeichern = in.readLine();
		        
		         
		        System.out.println("Ergebnis: "+einspeichern);
		        bw.write(einspeichern); 
		   
		    //------------------------Ausgabe------------------------------------------------------------
		        System.out.println("Ergebnis: " + in.readLine());
		    //-------------------------------------------------------------------------------------------
	        }
	        
	        catch(IOException e) {
				System.out.println("FEHLER: Sie müssen etwas eingeben oder 'https://...' wurde vergessen!"); //Fehlermeldung
			}
	        
	        out.close(); 
	        in.close(); 
	        client.close();
	    }
	 
		catch(IOException e) {
			System.out.println("FEHLER: Client kann sich nicht mit dem Server verbinden!"); //Fehlermeldung
		}
	}
}