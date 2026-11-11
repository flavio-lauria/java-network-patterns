import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer {
    public static void main(String[] args) {
        int port = 8080;
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and waiting for clients on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                threadPool.execute(() -> handleClient(clientSocket));
            }
        } catch (Exception ex) {
            System.err.println("Server error: " + ex.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println("Welcome to the Bingo Server! Generating numbers...");
            
            for (int i = 1; i <= 5; i++) {
                Thread.sleep(1000);
                out.println((int) (Math.random() * 90) + 1);
            }
            out.println("+"); 
            
        } catch (Exception ex) {
            System.err.println("Error communicating with client: " + ex.getMessage());
        }
    }
}