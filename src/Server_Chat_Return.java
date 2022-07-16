import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Server_Chat_Return implements Runnable{
    Socket SOCK;
    private Scanner INPUT;
    private PrintWriter OUT;
    String MESSAGE="";

    public  Server_Chat_Return(Socket X){
        this.SOCK= X;
    }
    public void CheckConnection() throws IOException{
        if(!SOCK.isConnected()){
            for(int i=1; i<=Server_chat
            .ConnectionArray.size();i++){
                if(Server_chat.ConnectionArray.get(i) == SOCK){
                    Server_chat.ConnectionArray.remove(i);
                }
            }
            for(int i=1;i<=Server_chat.ConnectionArray.size();i++){
                Socket TEMP_SOCK = (Socket) Server_chat.ConnectionArray.get(i-1);
                PrintWriter TEMP_OUT= new PrintWriter(TEMP_SOCK.getOutputStream()) ;
                TEMP_OUT.println(TEMP_SOCK.getLocalAddress().getHostName() + "disconnected!");
                TEMP_OUT.flush();
                System.out.println(TEMP_SOCK.getLocalAddress().getHostName() + "disconnected");
            }
        }
    }
    public void run()
    {
        try
        {
            try
            {
                INPUT = new Scanner(SOCK.getInputStream());
                OUT = new PrintWriter(SOCK.getOutputStream());
                
                while(true)
                {
                CheckConnection();
                if(!INPUT.hasNext())
                {
                    return;
                }
                MESSAGE = INPUT.nextLine();
                    System.out.println("Client said:" +MESSAGE);
                    for(int i=1; i<=Server_chat.ConnectionArray.size(); i++)
                    {
                        Socket TEMP_SOCK = (Socket) Server_chat.ConnectionArray.get(i-1);
                        PrintWriter TEMP_OUT= new PrintWriter(TEMP_SOCK.getOutputStream());
                        TEMP_OUT.println(MESSAGE);
                        TEMP_OUT.flush();
                        System.out.println("Sent to: " +TEMP_SOCK.getLocalAddress().getHostName());
                    }
                }
            }
            finally
            {
                SOCK.close();
            }
    }
        catch(Exception X)
        {
            System.out.println(X);
        }
}
}