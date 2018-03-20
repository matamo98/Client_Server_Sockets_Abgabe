import java.io.*;
import java.net.*;

public class Client{
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException{
		
		Socket SOCKET = new Socket("localhost", 4444);
		
		//------------------------stream-------------------------------------------------------------
		ObjectOutputStream out = new ObjectOutputStream(SOCKET.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(SOCKET.getInputStream());
		BufferedReader daten = new BufferedReader(new InputStreamReader(System.in));
		//-------------------------------------------------------------------------------------------
		
		byte[][] gamefield = new byte[6][7]; //Spielfeld Array -> 6 x 7 größe
		
		int spielZug;
		int endresult = 0;
		int zaehler = 0;
		
		//------------------------Ausgabe-des-Spielfeldes------------------------------------------
		while(endresult == 0){
			
			for(int i = 0; i < 6; i++){
				for(int j = 0; j < 7; j++){
					System.out.print(gamefield[i][j]);
					System.out.print(" ");
				}
				System.out.println(" ");
			}
		//------------------------Abfragen-des-Spielzugs---------------------------------------------
			System.out.print("Geben sie eine Zahl von 1 bis 7 ein!");
			spielZug = Integer.parseInt(daten.readLine()) - 1;
			
			for(int i = 5; i >= 0; i--){
					
				if(gamefield[i][spielZug] == 0){ //7;1;
					gamefield[i][spielZug] = 1;
					break; //damit die For schleife sich beendet
				}
			}
		//-------------------------------------------------------------------------------------------
		
			out.writeObject(gamefield); //sendet bzw speichert ganzes Array / Objekt in out
			
			gamefield = (byte[][]) in.readObject(); //bekommt Objekt von Server
			
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
			
			//------------------------Senkrecht-----------------------------------------------------------------------
			for(int i = 0; i < 3; i++){
				
				for(int j = 0; j < 6; j++){
					if(gamefield[i][j] == 1 && gamefield[i + 1][j] == 1 && gamefield[i + 2][j] == 1 && gamefield[i + 3][j] == 1){
						endresult = 1;
					}
					
					else if(gamefield[i][j] == 2 && gamefield[i + 1][j] == 2 && gamefield[i + 2][j] == 2 && gamefield[i + 3][j] == 2){
						endresult = 2;
					}
				}
			}
			//--------------------------------------------------------------------------------------------------------
			
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
			//--------------------------------------------------------------------------------------------------------
			
			
			
			//------------------------Diagonal-Rechts-----------------------------------------------------------------------
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
			//------------------------------------------------------------------------------------------------------------
			
			//------------------------Diagonal-Links-----------------------------------------------------------------------
			for(int i = 0; i < 3; i++){
				for(int j = 3; j < 7; j++){
					
					if(gamefield[i][j] == 1 && gamefield[i + 1][j - 1] == 1 && gamefield[i + 2][j - 2] == 1 && gamefield[i + 3][j - 3] == 1){
						endresult = 1;
					}
					
					else if(gamefield[i][j] == 2 && gamefield[i + 1][j - 1] == 2 && gamefield[i + 2][j - 2] == 2 && gamefield[i + 3][j - 3] == 2){
						endresult = 2;
					}
				}
			}
			//--------------------------------------------------------------------------------------------------------
			
			if(endresult == 1){
				System.out.println("Sie haben gewonnen!");
				out.writeObject(gamefield);
				break;
			}
			
			else if(endresult == 2){
				System.out.println("Sie haben verloren!");
				out.writeObject(gamefield);
				break;
			}
			
			else if(endresult == 3){
				System.out.println("Feld ist voll!");
				out.writeObject(gamefield);
				break;
			}
		}
		
		out.close();
		in.close();
		SOCKET.close();
	}
}