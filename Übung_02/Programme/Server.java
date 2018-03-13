import java.io.*;
import java.net.*;

public class Server{
	
	static String username;
	static String password;
	static String operation; 
	static int ersteZahl;
	static int zweiteZahl;
	static int result = 0;
	
	public static void main(String[] args){
		
		try {
			//------------------------server-socket-start------------------------------------------------
			ServerSocket server = new ServerSocket(2222); //Port 2222 
			System.out.println("Server wurde gestartet");
			//-------------------------------------------------------------------------------------------
			
			//------------------------server-socket-start------------------------------------------------
			Socket client = server.accept(); //dient dazu, den Client zu merken, seine Nachrichten zu hören und zu antworten
			//-------------------------------------------------------------------------------------------
			
			//------------------------stream-------------------------------------------------------------
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			//-------------------------------------------------------------------------------------------
			
			//------------------------authentifizierung--------------------------------------------------
			username = in.readLine();
			password = in.readLine();
			//-------------------------------------------------------------------------------------------
			
			if(username.equals("user") && password.equals("123")){
				out.write("genehmigt"); //Zugangscode
				out.newLine();
				out.flush();
				
				operation  = in.readLine();
				ersteZahl  = Integer.parseInt(in.readLine());
				zweiteZahl = Integer.parseInt(in.readLine());
				
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
					
					default: break;
				}
				
				out.write(Integer.toString(result));
				out.newLine();
				out.flush();
				
				//in.readLine();
			}
			
			else{
				out.write("Verbindung verweigert!");
				out.newLine();
				out.flush();
			}
			
			in.close();
			out.close();
			server.close();
			client.close();
		}
		
		catch(IOException e) {
			System.out.println("FEHLER: Server kann nicht aufgebaut werden!"); //Fehlermeldung
		}
	}
}
