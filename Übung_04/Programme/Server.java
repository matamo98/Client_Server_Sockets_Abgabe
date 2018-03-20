import java.io.*;
import java.net.*;

public class Server{
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
		//------------------------server-socket-start------------------------------------------------
		ServerSocket server = new ServerSocket(4444);//Port 444 
		System.out.println("Server wurde gestartet");
		//-------------------------------------------------------------------------------------------
		
		//------------------------server-socket-start------------------------------------------------
		Socket client = server.accept();
		//-------------------------------------------------------------------------------------------
		
		//------------------------stream-------------------------------------------------------------
		ObjectInputStream in = new ObjectInputStream(client.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
		BufferedReader daten = new BufferedReader(new InputStreamReader(System.in));
		//-------------------------------------------------------------------------------------------
		
		byte[][] gamefield = new byte[6][7]; //2-Dimensionales ARRAY
		
		int spielZug;
		int endresult = 0; //1) Client, 2) Server, 3)Unentschieden
		int zaehler = 0;
		
		//------------------------Erstellen-des-Spielfeldes------------------------------------------
		while(endresult == 0){
			gamefield = (byte[][]) in.readObject(); //erhält das Objekt von Client
			
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 7; j++){
					System.out.print(gamefield[i][j]);
					System.out.print(" ");
				}
				System.out.println("");
			}
		
		//-------------------------------------------------------------------------------------------
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 7; j++){
					if(gamefield[i][j] != 0){
						zaehler++;
					}
				}
			}
			
			if(zaehler == 28){ //Unentschieden
				endresult = 3;
			}
		
			//-------------------------------------------------------------------------------------------------------
			
			for(int Y = 0; Y < 3; Y++){
				for(int X = 0; X < 6; X++){
					if(gamefield[Y][X] == 1 && gamefield[Y + 1][X] == 1 && gamefield[Y + 2][X] == 1 && gamefield[Y + 3][X] == 1){
						endresult = 1;
					}
					
					else if(gamefield[Y][X] == 2 && gamefield[Y + 1][X] == 2 && gamefield[Y + 2][X] == 2 && gamefield[Y + 3][X] == 2){
						endresult = 2;
					}
				}
			}
			
			//------------------------Waagrecht-----------------------------------------------------------------------
			
			for(int i = 0; i < 6; i++){
				
				for(int j = 0; j < 4; j++){
				
					if(gamefield[i][j] == 1 && gamefield[i][j + 1] == 1 && gamefield[i][j + 2] == 1 && gamefield[i][j + 3] == 1){
						endresult = 1;
					}
					
					else if(gamefield[i][j] == 2 && gamefield[i][j + 1] == 2 && gamefield[i][j + 2] == 2 && gamefield[i][j + 3] == 2){
						endresult = 2;
					}
				}
			}
			
			//------------------------Diagonal-----------------------------------------------------------------------
			
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 4; j++){
					if(gamefield[i][j] == 1 && gamefield[i + 1][j + 1] == 1 && gamefield[i + 2][j + 2] == 1 && gamefield[i + 3][j + 3] == 1){
						endresult = 1;
					}
					
					else if(gamefield[i][j] == 2 && gamefield[i + 1][j + 1] == 2 && gamefield[i + 2][j + 2] == 2 && gamefield[i + 3][j + 3] == 2){
						endresult = 2;
					}
				}
			}
			//--------------------------------------------------------------------------------------------------------
			
			for(int Y = 0; Y < 3; Y++){
				for(int X = 3; X < 7; X++){
					if(gamefield[Y][X] == 1 && gamefield[Y + 1][X - 1] == 1 && gamefield[Y + 2][X - 2] == 1 && gamefield[Y + 3][X - 3] == 1){
						endresult = 1;
					}
					
					else if(gamefield[Y][X] == 2 && gamefield[Y + 1][X - 1] == 2 && gamefield[Y + 2][X - 2] == 2 && gamefield[Y + 3][X - 3] == 2){
						endresult = 2;
					}
				}
			}
			
			if(endresult == 1){
				System.out.println("Sie haben verloren!");
				out.writeObject(gamefield);
				break;
			}
			
			else if(endresult == 2){
				System.out.println("Sie haben gewonnen!");
				out.writeObject(gamefield);
				break;
			}
			
			else if(endresult == 3){
				System.out.println("Feld ist voll!");
				out.writeObject(gamefield);
				break;
			}
			
			System.out.print("Geben sie eine Zahl von 1 bis 7 ein!");
			spielZug = Integer.parseInt(daten.readLine()) - 1;
			
			for(int i = 5; i >= 0; i--){
				
				if(gamefield[i][spielZug] == 0){
					gamefield[i][spielZug] = 2;
					break;
				}
			}
			System.out.println("BAILA-END");
			out.writeObject(gamefield);
		}
		
		in.close();
		out.close();
		client.close();
		server.close();
	}
}
