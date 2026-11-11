import java.net.Socket;
import java.io.PrintWriter;

public class TcpStreamClient {
	private Thread downloadThread = new Thread();
	private Socket socket;
	
	public void connect(String address,int port){
		try{
			socket = new Socket(address,port);
			System.out.println("Connected to server at " + address + ":" + port);
			}
		catch(Exception ex){
			System.err.println("Error connecting to server: " + ex.getMessage());
		}
	}
	public void startListening(){
		if (socket == null || socket.isConnected()) {
			StreamReaderTask readerTask = new StreamReaderTask(socket);
			downloadThread= new Thread(readerTask);
			downloadThread.start();	
		} else {
			System.err.println("Socket is not connected. Cannot start listening.");
		}

	}
	public void stopServerFeed(){
		try{
			if (socket == null || socket.isClosed()) {
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				out.println("stop");
			} 
		}catch (Exception ex) {
				System.err.println("Error sending stop command: " + ex.getMessage());
			}

			
	
	}
	public void disconnect(){
		try{
			if (socket == null || !socket.isClosed()) {
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println("disconnect");
			socket.close();				
			System.out.println("Disconnected successfully.");
			}

		}
		catch(Exception ex){
			System.err.println("Error disconnecting: " + ex.getMessage());
		}
	}
}
