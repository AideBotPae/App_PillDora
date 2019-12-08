package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Presenter_Inventory extends Presenter {
    
    String function;

    public Presenter_Inventory (String user_id, Client_App client){
        super(user_id, client);
    }

    /* Tots els presenters hereden el query creator i per tant per a enviar peticions 
    d'informació no hi haurà cap mena de problema. La diferència serà que cada presenter 
    tindrà métodes diferents de com enviar la informació a les diferents View


    Recordeu que heu de cridar a Client.java per a rebre el json 
    */
    
    
    public List<Map<String, String>> getInventory(){

        // Preparar la query
        String query = super.query_creator("INVENTORY", null);
        // Enviar la informació
        Client_App clApp = super.getClientApp();
        clApp.connect();
        clApp.write(query);
        // Client llegeix ...
        String json_response = null;
        while (json_response == null){
            json_response = clApp.read();
        }
        // Ara toca enviar només la infromació per Treatments
        clApp.close();
        return this.processInventory(json_response);
    }


    public List<Map<String, String>> processInventory(String json_response){
        List<Map<String, String>> list_map_parameters = new ArrayList<Map<String, String>>();
        Map<String, Object> map_response_json = this.processInformation(json_response);
        Map<String, Object> params = (Map<String, Object>) map_response_json.get("parameters");
        list_map_parameters = (List<Map<String, String>>) params.get("inventory");
        return list_map_parameters;
    }

}