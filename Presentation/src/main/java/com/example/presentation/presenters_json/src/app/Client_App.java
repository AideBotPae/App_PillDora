package app;


public class Client_App {
    
    Client_TCP client;
    

    public Client_App(String user_id){
        this.client = new Client_TCP(user_id);
    }

    public void write(String json_parsed){
        this.client.write(json_parsed);
    }

    public String read(){
        String json_parsed = this.client.read();
        return json_parsed;
    }

    public void connect(){
        this.client.connect();
    }

    public void close(){
        this.client.close();
    }

}
