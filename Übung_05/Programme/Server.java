import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	
	public final static String FILE_TO_SEND = "C:/Users/Amort_Matthias/Desktop/Files/File_01.txt"; //Pfad

	public static void main (String [] args ) throws IOException {
		
		try {
	    	//------------------------server-socket-start------------------------------------------------
	    	ServerSocket server = new ServerSocket(5555);
	    	System.out.println("Server wurde gestartet");
	    	//-------------------------------------------------------------------------------------------
	    	
	    	//------------------------server-socket-start------------------------------------------------
	    	Socket client = server.accept();
	    	//-------------------------------------------------------------------------------------------
	    	
	    	try {
	
	    		File datei 		= new File (FILE_TO_SEND);
	    		byte [] array  	= new byte [(int)datei.length()];
	          
		        FileInputStream in 			= new FileInputStream(datei);
		        BufferedInputStream buff_in = new BufferedInputStream(in);
	          
		        buff_in.read(array,0,array.length);
		        
		        OutputStream out = client.getOutputStream();
		        System.out.println("Sending " + FILE_TO_SEND + "(" + array.length + " bytes)");
		        out.write(array,0,array.length);
		        out.flush();
		        
		        in.close();
		        buff_in.close();
	    		out.close();
	    		client.close();
	    		server.close();
	        }
	    	
	    	catch (NumberFormatException e) {
				System.out.println("Client hat nichts übergeben!"); //Fehlermeldung
			}
	    
	    		
	    }
		
		catch (IOException e) {
			System.out.println("Client hat nichts übergeben!"); //Fehlermeldung
		}
	      
	}
	    
}
