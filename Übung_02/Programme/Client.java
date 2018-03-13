import java.io.*;
import java.net.*;

public class Client{
	
	public static void main(String[] args) throws UnknownHostException{
		
		String anfrage="denide"; //Default Wert
		
		try {
			//------------------------client-socket-start------------------------------------------------
			Socket client = new Socket("localhost", 2222);
			System.out.println("Client wurde gestartet");
			
			//-------------------------------------------------------------------------------------------
			
			//------------------------stream-------------------------------------------------------------
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			BufferedReader daten = new BufferedReader(new InputStreamReader(System.in)); //System.in, Eingabe am Bildschirm auslesen
			//-------------------------------------------------------------------------------------------
			
			try {
				System.out.print("Benutzer: ");
				out.write(daten.readLine()); //Eingabe durch den Bildschirm / Konsole
				out.newLine(); //
				out.flush(); 
				
				System.out.print("Passwort: ");
				out.write(daten.readLine());
				out.newLine();
				out.flush();
				
				anfrage = in.readLine();
				
				if(anfrage.equals("genehmigt")){
				
					System.out.print("Waehle eine Operation aus (+, -, *, /): ");
					out.write(daten.readLine());
					out.newLine();
					out.flush();
					
					System.out.print("\nerste Zahl: ");
					out.write(daten.readLine());
					out.newLine();
					out.flush();
					
					System.out.print("zweite Zahl: ");
					out.write(daten.readLine());
					out.newLine();
					out.flush();
					
					System.out.println("Ergebnis: " + in.readLine());
				}
				
				else {
					System.out.println("SERVER VERWEIGERT: falsche Zugangsdaten");
				}
			}
			
			catch(IOException e) {
				System.out.println("FEHLER: Sie müssen etwas eingeben!"); //Fehlermeldung
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
