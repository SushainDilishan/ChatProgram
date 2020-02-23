import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    private int port;
    private String hostname;
    private String userName;

    public ChatClient( String hostname,int port) {

        this.hostname = hostname;
        this.port = port;
    }

    public void initiate(){

        try {
            Socket socket = new Socket(hostname,port);
            System.out.println("Connected to Chat Room");

            new ClientThreadRead(socket,this).start();
            new ClientThreadWrite(socket,this).start();

        }catch (UnknownHostException ex){
            System.out.println("Host Server Not Found "+ex.getMessage());

        }
        catch (IOException e){
            System.out.println("Inpur or Output Error "+ e.getMessage());

        }
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args) {

        if(args.length<2) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        ChatClient client = new ChatClient(hostname,port);
        client.initiate();

    }
}
