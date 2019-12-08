package app;

import java.util.HashMap;
import java.util.Map;

public class Presenter_Calendar extends Presenter {
    
    String function;

    public Presenter_Calendar(String user_id, Client_App client){
        super(user_id, client);
    }

    /* Tots els presenters hereden el query creator i per tant per a enviar peticions 
    d'informació no hi haurà cap mena de problema. La diferència serà que cada presenter 
    tindrà métodes diferents de com enviar la informació a les diferents View


    Recordeu que heu de cridar a Client.java per a rebre el json 
    */
    
    
    public Map<String, String> getCalendar(String date){
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("date", date);
        // Preparar la query
        String query = super.query_creator("TASKS CALENDAR", parameters);
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
        return this.processCalendar(json_response);
    }


    public Map<String, String> processCalendar(String json_response){
        Map<String, String> map_parameters = new HashMap<String, String>();
        Map<String, Object> map_response_json = this.processInformation(json_response);
        Map<String, Object> params = (Map<String, Object>) map_response_json.get("parameters");
        map_parameters = (Map<String, String>) params.get("tasks");
        return map_parameters;
    }

}