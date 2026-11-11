import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        TcpStreamClient client = new TcpStreamClient();
        
        client.connect("127.0.0.1", 8080);
        client.startListening();
        
        Scanner console = new Scanner(System.in);
        System.out.println("Type 'stop' to halt server feed, or 'disconnect' to exit.");
        
        while (console.hasNextLine()) {
            String command = console.nextLine();
            
            if (command.equalsIgnoreCase("stop")) {
                client.stopServerFeed();
            } else if (command.equalsIgnoreCase("disconnect")) {
                client.disconnect();
                break;
            }
        }
        console.close();
    }
}