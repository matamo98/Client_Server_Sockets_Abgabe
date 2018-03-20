import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class Server_Multi {
	
	public static void main(String[] args) throws Exception {
		
		//------------------------client-socket-start------------------------------------------------
		ServerSocket server = new ServerSocket(6666);
		System.out.println("Server wurde gestartet");
		//-------------------------------------------------------------------------------------------
		
		while (true) {
		
			Socket client = server.accept();

			ClientServiceThread clientThread = new ClientServiceThread(client);
			clientThread.start();
		}
	}
}

class ClientServiceThread extends Thread {

	private Socket client;

	public ClientServiceThread(Socket clientSocket) {
		this.client = clientSocket;
	}

	public void run() {
		try {
	        
			//------------------------stream-------------------------------------------------------------
			DataInputStream dataInput = new DataInputStream(client.getInputStream());
			DataOutputStream dataOutput = new DataOutputStream(client.getOutputStream());
			//-------------------------------------------------------------------------------------------
			
			while (true) {
	
				String eingabe = dataInput.readUTF(); //Daten von Client
				
				if (eingabe.equals("abbruch")) {
					System.out.print("Client beendet");
					break;
				}
				
				else if (eingabe.toLowerCase().equals("zeit")) {
						String buffer = getTime();
						dataOutput.writeUTF(buffer);
					}
				
				else if(eingabe.equals("berechnung")) {
						int num = getNumber();
						dataOutput.writeUTF(Integer.toString(num));
					}
				}
			
		}
		
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private synchronized static String getTime() throws InterruptedException{
    	Thread.sleep(1500);
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
	
	private synchronized static int getNumber() throws InterruptedException{
		Thread.sleep(1500);
    	Random rand = new Random();
    	int num = rand.nextInt(199) + 1;
    	
    	return num;
	}
}