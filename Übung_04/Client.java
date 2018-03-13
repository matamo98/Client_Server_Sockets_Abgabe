import java.io.*;
import java.net.*;

public class Client{
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException{
		
		Socket SOCKET = new Socket("localhost", 4444);
		
		//------------------------stream-------------------------------------------------------------
		ObjectOutputStream out = new ObjectOutputStream(SOCKET.getOutputStream());
		ObjectInputStream IN = new ObjectInputStream(SOCKET.getInputStream());
		BufferedReader DATEN = new BufferedReader(new InputStreamReader(System.in));
		//-------------------------------------------------------------------------------------------
		
		byte[][] FELD = new byte[6][7]; //Spielfeld Array -> 6 x 7 größe
		
		int ZUG;
		int endresult = 0;
		int COUNT = 0;
		
		//------------------------Ausgabe-des-Spielfeldes------------------------------------------
		while(endresult == 0){
			
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 7; j++){
					System.out.print(FELD[i][j]);
					System.out.print(" ");
				}
				System.out.println(" ");
			}
		//------------------------Abfragen-des-Spielzugs---------------------------------------------
			System.out.print("Geben sie eine Zahl von 1 bis 7 ein!");
			ZUG = Integer.parseInt(DATEN.readLine()) - 1;
			
			for(int COUNTER = 5; COUNTER >= 0; COUNTER--){
					
				if(FELD[COUNTER][ZUG] == 0){ //7;1;
					FELD[COUNTER][ZUG] = 1;
					//System.out.println(FELD[COUNTER][ZUG]);
					break; //damit die For schleife sich beendet
				}
			}
		//-------------------------------------------------------------------------------------------
		
			out.writeObject(FELD); //sendet bzw speichert ganzes Array / Objekt in out
			
			FELD = (byte[][]) IN.readObject(); //bekommt Objekt von Server
			
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
			//--------------------------------------------------------------------------------------------------------
			
			//------------------------Senkrecht-----------------------------------------------------------------------
			for(int i = 0; i < 4; i++){
				
				for(int j = 0; j < 6; j++){
					if(FELD[i][j] == 1 && FELD[i + 1][j] == 1 && FELD[i + 2][j] == 1 && FELD[i + 3][j] == 1){
						endresult = 1;
					}
					
					else if(FELD[i][j] == 2 && FELD[i + 1][j] == 2 && FELD[i + 2][j] == 2 && FELD[i + 3][j] == 2){
						endresult = 2;
					}
				}
			}
			//--------------------------------------------------------------------------------------------------------
			
			//------------------------Diagonal-Rechts-----------------------------------------------------------------------
			for(int i = 0; i < 3; i++){
				for(int j = 0; j < 4; j++){
					
					if(FELD[i][j] == 1 && FELD[i + 1][j + 1] == 1 && FELD[i + 2][j + 2] == 1 && FELD[i + 3][j + 3] == 1){
						endresult = 1;
					}
					
					else if(FELD[i][j] == 2 && FELD[i + 1][j + 1] == 2 && FELD[i + 2][j + 2] == 2 && FELD[i + 3][j + 3] == 2){
						endresult = 2;
					}
				}
			}
			//------------------------------------------------------------------------------------------------------------
			
			//------------------------Diagonal-Links-----------------------------------------------------------------------
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
			//--------------------------------------------------------------------------------------------------------
			
			if(endresult == 1){
				System.out.println("Sie haben gewonnen!");
				out.writeObject(FELD);
				break;
			}
			
			else if(endresult == 2){
				System.out.println("Sie haben verloren!");
				out.writeObject(FELD);
				break;
			}
			
			else if(endresult == 3){
				System.out.println("Feld ist voll!");
				out.writeObject(FELD);
				break;
			}
		}
		
		out.close();
		IN.close();
		SOCKET.close();
	}
}