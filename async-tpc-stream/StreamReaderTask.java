import java.net.Socket;
import java.io.PrintWriter;
import java.util.Scanner;
public class StreamReaderTask implements Runnable{
	
	private final Socket socket;
	public StreamReaderTask(Socket socket){
		this.socket=socket;
	}
	@Override
    public void run() {
        try (Scanner in = new Scanner(socket.getInputStream());
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            
            out.println("start");
            
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("+")) {
                    System.out.println("End of stream marker received.");
                    break;
                }
                System.out.println("Received from server: " + line);
            }
            System.out.println("Stream reading finished.");

        } catch (Exception ex) {
            System.err.println("Error in stream reader: " + ex.getMessage());
        }
    }
}