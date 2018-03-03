import java.io.*;
import java.net.*;

public class Server{
	
	static String operation; 
	static int ersteZahl;
	static int zweiteZahl;
	static int result = 0;
	
	public static void main(String[] args){
		
		try {
			ServerSocket server = new ServerSocket(1111);
			
			System.out.println("Server wurde gestartet");
			
			// Server wartet bis ein Client sich verbindet
			Socket client = server.accept(); 
		
			//Streams für Ein-und Ausgabe
			BufferedReader in 	= new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter out 	= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			//Hier bekommen die Variablen durch den Client einen Wert 
			operation  = in.readLine();
			ersteZahl  = Integer.parseInt(in.readLine());
			zweiteZahl = Integer.parseInt(in.readLine());
			
			//Abfrage der Operationen
			switch(operation){
				
				case "+":
					result = ersteZahl + zweiteZahl;
				break;
					
				case "-":
					result = ersteZahl - zweiteZahl;
				break;
					
				case "*":
					result = ersteZahl * zweiteZahl;
				break;
					
				case "/":
					result = ersteZahl / zweiteZahl;
				break;
					
				default: break; //Falls nicht dergleichen passt, soll die Schleife beendet werden
			}
				
				out.write(Integer.toString(result));
				out.newLine();
				out.flush();
			
			in.close(); //Reader Default Klasse wird beendet
			out.close(); //Writer Default Klasse wird beendet
			client.close(); //Client wird beendet
			server.close(); //Server wird beendet
		} 
		
		catch (IOException e) {
			System.out.println("Server konnte nicht gestartet werden"); //Fehlermeldung
			e.printStackTrace();
		} 
	}
}


