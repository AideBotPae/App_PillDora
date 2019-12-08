package app;

import java.util.HashMap;
import java.util.Map;

public class Presenter_New_Prescription extends Presenter {
    
    String function;

    public Presenter_New_Prescription (String user_id, Client_App client){
        super(user_id, client);
    }

    /* Tots els presenters hereden el query creator i per tant per a enviar peticions 
    d'informació no hi haurà cap mena de problema. La diferència serà que cada presenter 
    tindrà métodes diferents de com enviar la informació a les diferents View


    Recordeu que heu de cridar a Client.java per a rebre el json 
    */
    public boolean newPrescription(String CN, String quantity, String freq, String end_date){
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("CN", CN);
        parameters.put("quantity", quantity);
        parameters.put("freq", freq);
        parameters.put("end_date", end_date);
        String query = super.query_creator("INTRODUCE PRESCRIPTION", parameters);
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
        return this.processNewPrescription(json_response);

    }
    
    

    public boolean processNewPrescription(String json_response){
        Map<String, Object> map_response_json = this.processInformation(json_response);
        Map<String,String> param_response= ((Map<String, String>) map_response_json.get("parameters"));
        if (param_response.get("boolean") == "True"){
            return true;
        }else{
            return false;
        }
    }

}