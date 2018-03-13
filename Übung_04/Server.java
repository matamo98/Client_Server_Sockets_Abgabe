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
		
		byte[][] FELD = new byte[6][7]; //2-Dimensionales ARRAY
		
		int ZUG;
		int endresult = 0; //1) Client, 2) Server, 3)Unentschieden
		int COUNT = 0;
		
		//------------------------Erstellen-des-Spielfeldes------------------------------------------
		while(endresult == 0){
			FELD = (byte[][]) in.readObject(); //erhält das Objekt von Client
			
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 7; j++){
					System.out.print(FELD[i][j]);
					System.out.print(" ");
				}
				System.out.println("");
			}
		
		//-------------------------------------------------------------------------------------------
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 7; j++){
					if(FELD[i][j] != 0){
						COUNT++;
					}
				}
			}
			
			if(COUNT == 42){
				endresult = 3;
			}
			
			//------------------------Waagrecht-----------------------------------------------------------------------
			
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 4; j++){
					if(FELD[i][j] == 1 && FELD[i][j + 1] == 1 && FELD[i][j + 2] == 1 && FELD[i][j + 3] == 1){
						endresult = 1;
					}
					
					else if(FELD[i][j] == 2 && FELD[i][j + 1] == 2 && FELD[i][j + 2] == 2 && FELD[i][j + 3] == 2){
						endresult = 2;
					}
				}
			}
			//-------------------------------------------------------------------------------------------------------
			
			for(int Y = 0; Y < 4; Y++){
				for(int X = 0; X < 6; X++){
					if(FELD[Y][X] == 1 && FELD[Y + 1][X] == 1 && FELD[Y + 2][X] == 1 && FELD[Y + 3][X] == 1){
						endresult = 1;
					}
					
					else if(FELD[Y][X] == 2 && FELD[Y + 1][X] == 2 && FELD[Y + 2][X] == 2 && FELD[Y + 3][X] == 2){
						endresult = 2;
					}
				}
			}
			
			//------------------------Diagonal-----------------------------------------------------------------------
			
			for(int Y = 0; Y < 3; Y++){
				for(int X = 0; X < 4; X++){
					if(FELD[Y][X] == 1 && FELD[Y + 1][X + 1] == 1 && FELD[Y + 2][X + 2] == 1 && FELD[Y + 3][X + 3] == 1){
						endresult = 1;
					}
					
					else if(FELD[Y][X] == 2 && FELD[Y + 1][X + 1] == 2 && FELD[Y + 2][X + 2] == 2 && FELD[Y + 3][X + 3] == 2){
						endresult = 2;
					}
				}
			}
			//--------------------------------------------------------------------------------------------------------
			
			for(int Y = 0; Y < 3; Y++){
				for(int X = 3; X < 7; X++){
					if(FELD[Y][X] == 1 && FELD[Y + 1][X - 1] == 1 && FELD[Y + 2][X - 2] == 1 && FELD[Y + 3][X - 3] == 1){
						endresult = 1;
					}
					
					else if(FELD[Y][X] == 2 && FELD[Y + 1][X - 1] == 2 && FELD[Y + 2][X - 2] == 2 && FELD[Y + 3][X - 3] == 2){
						endresult = 2;
					}
				}
			}
			
			if(endresult == 1){
				System.out.println("Sie haben verloren!");
				out.writeObject(FELD);
				break;
			}
			
			else if(endresult == 2){
				System.out.println("Sie haben gewonnen!");
				out.writeObject(FELD);
				break;
			}
			
			else if(endresult == 3){
				System.out.println("Feld ist voll!");
				out.writeObject(FELD);
				break;
			}
			
			System.out.print("Geben sie eine Zahl von 1 bis 7 ein!");
			ZUG = Integer.parseInt(daten.readLine()) - 1;
			
			for(int COUNTER = 5; COUNTER >= 0; COUNTER--){
				
				if(FELD[COUNTER][ZUG] == 0){
					FELD[COUNTER][ZUG] = 2;
					break;
				}
			}
			out.writeObject(FELD);
		}
		
		in.close();
		out.close();
		client.close();
		server.close();
	}
}
