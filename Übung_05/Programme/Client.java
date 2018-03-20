import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client{
	
	public final static String FILE_TO_RECEIVED = "C:/Users/Amort_Matthias/Desktop/Files/File_01.txt";

	public final static int FILE_SIZE = 6022386;

	public static void main (String [] args ) throws IOException {
    
		int byteCounter;
	    int iaz = 0;
    
	    try {
	    	//------------------------server-socket-start------------------------------------------------
	    	Socket client = new Socket("localHost", 5555);
	    	System.out.println("Client wurde gestartet!");
	    	//-------------------------------------------------------------------------------------------
	    	
	    	byte [] mybytearray  = new byte [FILE_SIZE];
	    	InputStream is = client.getInputStream();
	    	
	    	FileOutputStream in = new FileOutputStream(FILE_TO_RECEIVED);
	    	BufferedOutputStream buff_in = new BufferedOutputStream(in);
	    	
	    	byteCounter = is.read(mybytearray,0,mybytearray.length);
	    	iaz = byteCounter;
	
	    	do {
	    		byteCounter = is.read(mybytearray, iaz, (mybytearray.length-iaz));
	         
	    		if(byteCounter >= 0) {
	    			iaz += byteCounter;
	    		}
	    	} 
	      
	    	while(byteCounter > -1);
	
		    buff_in.write(mybytearray, 0 , iaz);
		    buff_in.flush();
		    System.out.println("File " + FILE_TO_RECEIVED + " downloaded (" + iaz + " bytes read)");
		      
		    in.close();
		    buff_in.close();
		    client.close();
	    }
	    
	    catch (IOException e) {
			System.out.println("Server konnte nicht gestartet werden"); //Fehlermeldung
			e.printStackTrace();
		}
	}
}
