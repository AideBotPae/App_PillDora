package app;

import java.util.HashMap;
import java.util.Map;

public class Presenter_Journey extends Presenter {
    
    String function;

    public Presenter_Journey(String user_id, Client_App client){
        super(user_id, client);
    }

    /* Tots els presenters hereden el query creator i per tant per a enviar peticions 
    d'informació no hi haurà cap mena de problema. La diferència serà que cada presenter 
    tindrà métodes diferents de com enviar la informació a les diferents View


    Recordeu que heu de cridar a Client.java per a rebre el json 
    */
    
    
    public Map<String, String> getJourney(String departure, String arrival){
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("departure_date", departure);
        parameters.put("arrival_date", arrival);
        // Preparar la query
        String query = super.query_creator("JOURNEY", parameters);
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
        return this.processJourney(json_response);
    }


    public Map<String, String> processJourney(String json_response){
        Map<String, String> map_parameters = new HashMap<String, String>();
        Map<String, Object> map_response_json = this.processInformation(json_response);
        Map<String, Object> params = (Map<String, Object>) map_response_json.get("parameters");
        map_parameters = (Map<String, String>) params.get("journey_info");
        return map_parameters;
    }

}