import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.BufferedWriter;



public class client {
	public static void main(String[] args)throws IOException{
		Socket client = new Socket("localhost",9999);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		
		
		
	}
}
