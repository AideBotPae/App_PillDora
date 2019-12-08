package app;

public class Client_TCP {
    
    Client_TCP client;
    String user_id;
    String ip = "147.83.50.15";
    String port = "8999";
    String null_json = "{'user_id': "+ this.user_id + ",'function':'login','parameters':'{'values':'False'}'}"; 

    public Client_TCP(String user_id){

    }

    public void write(String json_parsed){
        this.client.write(json_parsed);
    }

    public String read(){
        String json_parsed = null_json;
        json_parsed = this.client.read();
        return json_parsed;
    }

    public void connect(){
        
    }

    public void close(){
        
    }

}