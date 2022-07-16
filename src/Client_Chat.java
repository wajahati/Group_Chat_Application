import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client_Chat implements Runnable
{
    Socket SOCK;
    Scanner INPUT;
    Scanner SEND= new Scanner (System.in);
    PrintWriter OUT;
    
    public Client_Chat(Socket X)
    {
       this.SOCK = X;
    }
    
    public void run()
    {
        try{
            try{
                INPUT = new Scanner(SOCK.getInputStream());
                OUT = new PrintWriter(SOCK.getOutputStream());
                OUT.flush();
                CheckStream();
            }
            finally{
                SOCK.close();
            }
        }
        catch(Exception X){
            System.out.print(X);
        } 
    }
    
    public void DISCONNECT() throws IOException {
        OUT.println(Client_chat_GUI1.UserName + "has disconnected");
        OUT.flush();
        SOCK.close();
        JOptionPane.showMessageDialog(null, "You disconnected!");
        System.exit(0);
    }
    
    public void CheckStream(){
        
        while(true){
            RECIEVE();
        }
    }
    
    public void RECIEVE(){
        if(INPUT.hasNext()){
            String MESSAGE = INPUT.nextLine();
            
            if(MESSAGE.contains("#?!")){
                String TEMP1= MESSAGE.substring(3);
                       TEMP1= TEMP1.replace("[","");
                       TEMP1= TEMP1.replace("]","");
                
                String[] CurrentUsers = TEMP1.split(", ");
                Client_chat_GUI1.JL_ONLINE.setListData(CurrentUsers);
                
            }
            else{
                Client_chat_GUI1.TA_CONVERSATION.append(MESSAGE + "\n");
            }
        }
    }
    
    public void SEND(String X){
        OUT.println(Client_chat_GUI1.UserName + ": " + X);
        OUT.flush();
        Client_chat_GUI1.TF_Message.setText("");
    }
}
