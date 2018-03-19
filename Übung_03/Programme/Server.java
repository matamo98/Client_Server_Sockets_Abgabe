
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
 
public class Server {
 
	public static void main(String[] args){
		try {
			//------------------------server-socket-start------------------------------------------------
	    	ServerSocket server = new ServerSocket(3333);
	        System.out.println("Server wurde gestartet");
	        //-------------------------------------------------------------------------------------------
	        
	        //------------------------server-socket-start------------------------------------------------
	        Socket client = server.accept();
	        System.out.println("Verbindung wurde hergestellt");
	        //-------------------------------------------------------------------------------------------
	        
	        //------------------------stream-------------------------------------------------------------
	        BufferedReader in 	= new BufferedReader(new InputStreamReader(client.getInputStream()));
	        BufferedWriter out 	= new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	        //------------------------------------------------------------------------------------------- 
	        try {
		        String eingabeClient = in.readLine(); //URL einlesen
		        URL url = new URL(eingabeClient); //URL in URL Format speichern
		        
		    //------------------------stream-------------------------------------------------------------
		        InputStream is = url.openStream(); //URL wird geöffnet 
		        BufferedReader br = new BufferedReader(new InputStreamReader(is));
		    //------------------------------------------------------------------------------------------- 
		 
		        String line = br.readLine();
		        //System.out.println(line);
		                
		        out.write(line);
		        out.newLine();
		        out.flush();
		                
		        server.close();
		        client.close();
		        out.close();
		        in.close();
		        br.close();
	        }
	        
	        catch(IOException e) {
				System.out.println("FEHLER: Client hat nichts übergeben oder 'https://...' wurde vergessen!"); //Fehlermeldung
			}
	    }
	    
		catch(IOException e) {
			System.out.println("FEHLER: Client kann sich nicht mit dem Server verbinden!"); //Fehlermeldung
		}
   }
}
 
     
