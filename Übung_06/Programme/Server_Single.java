import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
	 
	
public class Server_Single {
    
	public static void main(String args[]) throws IOException, InterruptedException{
		
		//------------------------server-socket-start------------------------------------------------
		ServerSocket server = new ServerSocket(6666);
        System.out.println("Server wurde gestartet");
		Socket client = server.accept();
		//-------------------------------------------------------------------------------------------
		
		//------------------------stream-------------------------------------------------------------
        DataInputStream in = new DataInputStream(client.getInputStream());
        DataOutputStream out = new DataOutputStream(client.getOutputStream());
        //-------------------------------------------------------------------------------------------
        
        while (true){

        	String eingabe = in.readUTF(); //Bekommt Daten vom Client
 
            if(eingabe.equals("abbruch")){
            	out.writeUTF("System wurde angehalten"); 
            	break;
            }
            
            else if (eingabe.equals("zeit")) {
            	String buffer = getTime();
				out.writeUTF(buffer); //gibt an Client weiter
            }
            
            else if (eingabe.equals("berechnung")) {
            	int num = getNumber();
				out.writeUTF(Integer.toString(num));//gibt an Client weiter, String wird in Integer gewandelt
            }
        }
        
        server.close();
        in.close();
        out.close();
        client.close();
        
    }

    private static String getTime() throws InterruptedException{
    	Thread.sleep(1500);//Zufallszeit
		Calendar kalender = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//Datumformat
        return sdf.format(kalender.getTime());
    }
    
    private static  int getNumber() throws InterruptedException	{
		Thread.sleep(1500); //Zufallszeit
    	Random rand = new Random();
    	int randomZahl = rand.nextInt(199) + 1; //Zufallszahl	
    	return randomZahl;
	}
    
}