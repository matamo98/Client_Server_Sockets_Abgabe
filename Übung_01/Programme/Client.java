import java.io.*;
import java.net.*;

public class Client{
	public static void main(String[] args) throws UnknownHostException{
		
		try {
			/* IP ADRESSE (localHost) des Client wird bei der Verbindung mit-übertragen, 
			 * damit der Server weiß, welcher Client an welchem Port hängt.
			 * 
			 * An diesem Port 1111 soll eine Verbindung zwischen Client und Server aufgebaut werden.
			 * 
			 */
			
			//------------------------client-socket-start------------------------------------------------
			Socket client = new Socket("localHost", 1111); //[Adresse_Client][PortNummer_Server]
			System.out.println("Client wurde gestartet");
			//-------------------------------------------------------------------------------------------
			
			//------------------------stream-------------------------------------------------------------
			BufferedReader in 	= new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter out 	= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			BufferedReader dt 	= new BufferedReader(new InputStreamReader(System.in)); //System.in, Eingabe am Bildschirm auslesen
			//-------------------------------------------------------------------------------------------
			
			try {
				System.out.print("Waehle eine Operation aus (+, -, *, /): ");
				out.write(dt.readLine());										// Eingabe Bildschirm wird in 'out' gespeichert
				out.newLine();													// neue Zeile (für jede Zeile einen Datensatz)
				out.flush();													// Zwischenspeicher wird geleert
			
				System.out.print("\nerste Zahl: ");
				out.write(dt.readLine());
				out.newLine();
				out.flush();
			
				System.out.print("zweite Zahl: ");
				out.write(dt.readLine());
				out.newLine();
				out.flush();
				
				System.out.println("Ergebnis: " + in.readLine());
			}
			
			catch(IOException e) {
				System.out.println("FEHLER: Sie müssen etwas eingeben!"); //Fehlermeldung
			}
			
			out.close(); 
			in.close(); 
			client.close();
		} 
		
		catch (IOException e) {
			System.out.println("Verbindung an diesem Port nicht möglich!"); //Fehlermeldung
			e.printStackTrace();
		}
	}
}
