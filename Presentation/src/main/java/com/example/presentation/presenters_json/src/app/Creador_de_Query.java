package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Creador_de_Query {

    public static void main(String[] args) {

        String user_id = "hamza";
        Client_App clapp = new Client_App(user_id);
        Presenter pr = new Presenter(user_id, clapp);

        HashMap<String, String> parameters_treatments = new HashMap<String, String>();
        String query_jason_treatments = pr.query_creator("CURRENT TREATMENT", parameters_treatments);

        HashMap<String, String> parameters_inventory = new HashMap<String, String>();
        String query_jason_inventory = pr.query_creator("INVENTORY", parameters_inventory);

        HashMap<String, String> parameters_history = new HashMap<String, String>();
        parameters_history.put("date", "08/12/2019");
        String query_jason_history = pr.query_creator("HISTORY", parameters_history);

        HashMap<String, String> parameters_calendar = new HashMap<String, String>();
        parameters_calendar.put("date", "08/12/2019");
        String query_jason_calendar = pr.query_creator("TASKS CALENDAR", parameters_calendar);

        HashMap<String, String> parameters_journey = new HashMap<String, String>();
        parameters_journey.put("departure_date", "10/12/2019");
        parameters_journey.put("arrival_date", "31/12/2019");
        String query_jason_journey = pr.query_creator("JOURNEY", parameters_journey);

        HashMap<String, String> parameters_new_medicine = new HashMap<String, String>();
        parameters_new_medicine.put("CN", "664029.6");
        parameters_new_medicine.put("quantity", "2");
        parameters_new_medicine.put("expiration_date", "10/01/2022");
        String query_jason_new_medicine = pr.query_creator("INTRODUCE MEDICINE", parameters_new_medicine);

        HashMap<String, String> parameters_new_prescription = new HashMap<String, String>();
        parameters_new_prescription.put("CN", "664029.6");
        parameters_new_prescription.put("quantity", "56");
        parameters_new_prescription.put("freq", "2");
        parameters_new_prescription.put("end_date", "31/12/2019");
        String query_jason_new_prescription = pr.query_creator("INTRODUCE PRESCRIPTION", parameters_new_prescription);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("query_jason_creation", true));
            writer.append('\n');
            writer.append(query_jason_inventory);
            writer.append('\n');
            writer.append(query_jason_history);
            writer.append('\n');
            writer.append(query_jason_calendar);
            writer.append('\n');
            writer.append(query_jason_journey);
            writer.append('\n');
            writer.append(query_jason_new_medicine); 
            writer.append('\n');
            writer.append(query_jason_new_prescription); 
            writer.append('\n');
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}