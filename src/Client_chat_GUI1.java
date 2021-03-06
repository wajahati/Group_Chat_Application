import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

public class Client_chat_GUI1 {
    private static Client_Chat ClientChat;
    public static String UserName = "Anonymous";

    public static JFrame MainWindow = new JFrame();
    private static JButton B_ABOUT = new JButton();
    private static JButton B_CONNECT = new JButton();
    private static JButton B_DISCONNECT = new JButton();
    private static JButton B_HELP = new JButton();
    private static JButton B_SEND = new JButton();
    private static JLabel L_Message = new JLabel("Message: ");
    public static JTextField TF_Message = new JTextField(20);
    private static JLabel L_Conversation = new JLabel();
    public static JTextArea TA_CONVERSATION = new JTextArea();
    private static JScrollPane SP_CONVERSATION = new JScrollPane();
    private static JLabel L_ONLINE = new JLabel();
    public static JList JL_ONLINE = new JList();
    private static JScrollPane SP_ONLINE = new JScrollPane();
    private static JLabel L_LoggedInAs = new JLabel();
    private static JLabel L_LoggedInAsBox = new JLabel(); 
    
    public static JFrame LogInWindow = new JFrame();
    public static JTextField TF_UserNameBox = new JTextField(20);
    private static JButton B_ENTER = new JButton("Enter");
    private static JLabel L_EnterUserName = new JLabel("Enter Username: ");
    private static JPanel P_LogIn = new JPanel();
    
    public static void main(String args[])
    {
        BuildMainWindow();
        Initialize();
    }    
    
    public static void Connect()
    {
      try  
      {
          final int PORT = 4146;
          final String HOST = "0.0.0.0";
          Socket SOCK = new Socket(HOST,PORT);
          System.out.println("You connected to: " + HOST);
          
          ClientChat = new Client_Chat(SOCK);
          
          PrintWriter OUT = new PrintWriter(SOCK.getOutputStream());
          OUT.println(UserName);
          OUT.flush();
          
          Thread X= new Thread(ClientChat);
          X.start();
      }
      catch(Exception X)
              {
                  System.out.println(X);
                  JOptionPane.showMessageDialog(null, "Server not Responding");
                  System.exit(0);
              }
    }
    
    public static void Initialize()
    {
        B_SEND.setEnabled(false);
        B_DISCONNECT.setEnabled(false);
        B_CONNECT.setEnabled(true);
    }
    public static void BuildLogInWindow()
    {
        LogInWindow.setTitle("What's your name?");
        LogInWindow.setSize(400,100);
        LogInWindow.setLocation(250,200);       
        LogInWindow.setResizable(false);
        P_LogIn = new JPanel();
        P_LogIn.add(L_EnterUserName);
        P_LogIn.add(TF_UserNameBox);
        P_LogIn.add(B_ENTER);
        LogInWindow.add(P_LogIn);
        
        Login_Action();
        LogInWindow.setVisible(true);
    }
    public static void BuildMainWindow()
    {
        MainWindow.setTitle(UserName + "'s Chat Box");
        MainWindow.setSize(450,500);
        MainWindow.setLocation(220,180);
        MainWindow.setResizable(false);
        ConfigureMainWindow();
        MainWindow_Action();
        MainWindow.setVisible(true);
    }
    public static void ConfigureMainWindow()
    {
        MainWindow.setBackground(new java.awt.Color(255,255,255));
        MainWindow.setSize(510,320);
        MainWindow.getContentPane().setLayout(null);
        
        B_SEND.setBackground(new java.awt.Color(0,0,255));
        B_SEND.setForeground(new java.awt.Color(255,255,255));
        B_SEND.setText("SEND");
        MainWindow.getContentPane().add(B_SEND);
        B_SEND.setBounds(250,40,81,25);    
        
        B_DISCONNECT.setBackground(new java.awt.Color(0,0,255));
        B_DISCONNECT.setForeground(new java.awt.Color(255,255,255));
        B_DISCONNECT.setText("DISCONNECT");
        MainWindow.getContentPane().add(B_DISCONNECT);
        B_DISCONNECT.setBounds(10,40,110,25);
        
        B_CONNECT.setBackground(new java.awt.Color(0,0,255));
        B_CONNECT.setForeground(new java.awt.Color(255,255,255));       
        B_CONNECT.setText("CONNECT");
        B_CONNECT.setToolTipText("");
        MainWindow.getContentPane().add(B_CONNECT);        
        B_CONNECT.setBounds(130,40,110,25);
                
        B_HELP.setBackground(new java.awt.Color(0,0,255));
        B_HELP.setForeground(new java.awt.Color(255,255,255)); 
        B_HELP.setText("HELP");
        MainWindow.getContentPane().add(B_HELP);        
        B_HELP.setBounds(340,40,75,25); 
                
        B_ABOUT.setBackground(new java.awt.Color(0,0,255));
        B_ABOUT.setForeground(new java.awt.Color(255,255,255)); 
        B_ABOUT.setText("ABOUT");
        MainWindow.getContentPane().add(B_ABOUT);        
        B_ABOUT.setBounds(420,40,75,25);        
        
        L_Message.setBackground(new java.awt.Color(0,0,255));
        L_Message.setForeground(new java.awt.Color(0,0,0)); 
        L_Message.setText("Message: ");
        MainWindow.getContentPane().add(L_Message);        
        L_Message.setBounds(10,10,60,20);
        
        TF_Message.setForeground(new java.awt.Color(0,0,255));
        TF_Message.requestFocus();
        MainWindow.getContentPane().add(TF_Message);        
        TF_Message.setBounds(70,4,260,30);
        
        MainWindow.getContentPane().add(L_Conversation);        
        L_Conversation.setBounds(100,70,140,16);
        
        TA_CONVERSATION.setColumns(20);
        TA_CONVERSATION.setFont(new java.awt.Font("Tahoma",0,12));
        TA_CONVERSATION.setForeground(new java.awt.Color(0,0,255));
        TA_CONVERSATION.setLineWrap(true);
        TA_CONVERSATION.setRows(5);
        TA_CONVERSATION.setEditable(false);
        
        SP_CONVERSATION.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_CONVERSATION.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_CONVERSATION.setViewportView(TA_CONVERSATION);
        MainWindow.getContentPane().add(SP_CONVERSATION);       
        SP_CONVERSATION.setBounds(10,90,330,180);
                
        L_ONLINE.setHorizontalAlignment(SwingConstants.CENTER);
        L_ONLINE.setText("Currently Online");
        L_ONLINE.setToolTipText("");
        MainWindow.getContentPane().add(L_ONLINE);
        L_ONLINE.setBounds(350,70,130,16);
        
        
        JL_ONLINE.setForeground(new java.awt.Color(0,0,255));
        
        SP_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_ONLINE.setViewportView(JL_ONLINE);
        MainWindow.getContentPane().add(SP_ONLINE);       
        SP_ONLINE.setBounds(350,90,130,180);
        
        L_LoggedInAs.setFont(new java.awt.Font("Tahoma",0,12));
        L_LoggedInAs.setText("Currently Logged in as");
        MainWindow.getContentPane().add(L_LoggedInAs);
        L_LoggedInAs.setBounds(348,0,140,15);
        
        L_LoggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
        L_LoggedInAsBox.setFont(new java.awt.Font("Tahoma",0,12));
        L_LoggedInAsBox.setForeground(new java.awt.Color(255,0,0));;
        L_LoggedInAsBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(255,0,0)));
        MainWindow.getContentPane().add(L_LoggedInAsBox);
        L_LoggedInAsBox.setBounds(340,17,150,20);
    }
    public static void Login_Action()
    {
        B_ENTER.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
              ACTION_B_ENTER(); 
            }
        });
    }
    public static void ACTION_B_ENTER()
    {
        if(!TF_UserNameBox.getText().equals(""))
        {
            UserName = TF_UserNameBox.getText().trim();
            L_LoggedInAsBox.setText(UserName);
            Server_chat.CurrentUsers.add(UserName);
            MainWindow.setTitle(UserName + "'s ChatBox");
            LogInWindow.setVisible(false);
            B_SEND.setEnabled(true);
            B_DISCONNECT.setEnabled(true);
            B_CONNECT.setEnabled(false);
            Connect();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Please enter a name");
        }
    }
    public static void MainWindow_Action()
    {
        B_SEND.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    ACTION_B_SEND(); 
                }
            }
        );
        
        B_DISCONNECT.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    ACTION_B_DISCONNECT(); 
                }
            }
        );
        
        B_CONNECT.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    BuildLogInWindow(); 
                }
            }
        );
        
        B_HELP.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    ACTION_B_HELP(); 
                }
            }
        );
        
        B_ABOUT.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    ACTION_B_ABOUT(); 
                }
            }
        );
    }
    
    public static void ACTION_B_SEND()
    {
        if(!TF_Message.getText().equals(""))
        {
            ClientChat.SEND(TF_Message.getText());
            TF_Message.requestFocus();
        }
    }
    
    public static void ACTION_B_DISCONNECT()
    {
        try
        {
          ClientChat.DISCONNECT();  
        }
        catch(Exception Y)
        {
            Y.printStackTrace();
        }
    }
    
    public static void ACTION_B_HELP()
    {
        JOptionPane.showMessageDialog(null, "To connect with server press CONNECT \nTo disconnect with server press DISCONNECT \nTo send message press SEND button");
    }
    
    public static void ACTION_B_ABOUT()
    {
        JOptionPane.showMessageDialog(null, "         Semester Project ");
    }
}
