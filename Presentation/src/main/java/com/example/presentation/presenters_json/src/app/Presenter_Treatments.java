package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presenter_Treatments extends Presenter {
    
    String function;

    public Presenter_Treatments (String user_id, Client_App client){
        super(user_id, client);
    }

    /* Tots els presenters hereden el query creator i per tant per a enviar peticions 
    d'informació no hi haurà cap mena de problema. La diferència serà que cada presenter 
    tindrà métodes diferents de com enviar la informació a les diferents View


    Recordeu que heu de cridar a Client.java per a rebre el json 
    */
    public boolean deleteTreatment(String CN){
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("CN", CN);
        String query = super.query_creator("DELETE TREATMENT", parameters);
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
        return this.process_Delete_Treatment(json_response);

    }
    
    public List<Map<String, String>> getTreatments(){

        // Preparar la query
        String query = super.query_creator("CURRENT TREATMENT", null);
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
        return this.processTreatments(json_response);
    }

    public boolean process_Delete_Treatment(String json_response){
        Map<String, Object> map_response_json = this.processInformation(json_response);
        Map<String,String> param_response= ((Map<String, String>) map_response_json.get("parameters"));
        if (param_response.get("boolean") == "True"){
            return true;
        }else{
            return false;
        }
    }

    public List<Map<String, String>> processTreatments(String json_response){
        List<Map<String, String>> list_map_parameters = new ArrayList<Map<String, String>>();
        Map<String, Object> map_response_json = this.processInformation(json_response);
        Map<String, Object> params = (Map<String, Object>) map_response_json.get("parameters");
        list_map_parameters = (List<Map<String, String>>) params.get("reminder-info");
        return list_map_parameters;
    }

}