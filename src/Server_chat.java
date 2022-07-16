import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server_chat {
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers = new ArrayList<String>();
    public static void main(String[] args) {
        try{
            final int PORT = 4146;
            ServerSocket SERVER = new ServerSocket(PORT);
            System.out.println("Waiting for Clients..");
            
            while(true){
                Socket SOCK = SERVER.accept();
                ConnectionArray.add(SOCK);
                
                System.out.println("Client connected from:" +SOCK.getLocalAddress().getHostName());
                AddUserName(SOCK);
                Server_Chat_Return CHAT = new Server_Chat_Return(SOCK);
                Thread X = new Thread(CHAT);
                X.start();
        }
    }
    catch(Exception X){
        System.out.println(X);
    }
}
    public static void AddUserName(Socket X) throws IOException{
        Scanner INPUT =  new Scanner(X.getInputStream());
        String UserName = INPUT.nextLine();
        CurrentUsers.add(UserName);
        
        for(int i = 1; i<= Server_chat.ConnectionArray.size(); i++){
            Socket TEMP_SOCK = (Socket) Server_chat.ConnectionArray.get(i-1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!" + CurrentUsers);
            OUT.flush();
    }
}
}