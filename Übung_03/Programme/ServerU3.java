
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
 
public class ServerU3 {
 
    public static void main(String[] args) throws IOException {
       
    	
    	 ServerSocket server = new ServerSocket(2222); //Port 2222 
         System.out.println("Server wurde gestartet");
    	
         Socket client = server.accept();
         System.out.println("Verbindung wurde herrgestellt");
         
         
         BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
         BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
         
         
        String  eingabeClient; 
    	URL url ;
        InputStream is = null;
        BufferedReader br;
        String line;
 
        eingabeClient= in.readLine();
        System.out.println("Dies ist Server Seite: ");
        System.out.println(eingabeClient);
        try {
            url = new URL(eingabeClient);
        	
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));
 
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                
                out.write(line);
                out.newLine();
                out.flush();
                 
                in.readLine();
                
                
            }
        } catch (MalformedURLException mue) {
             mue.printStackTrace();
        } catch (IOException ioe) {
             ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }
 
     
}