package app;

import java.util.HashMap;
import java.util.Map;

public class Test_Definitiu {

    public static void main (String [ ] args) {

        String user_id = "hamza";
        Client_App clapp = new Client_App(user_id);
        String function = "current treatments";
        Presenter_Treatments pr_treatments = new Presenter_Treatments(user_id, clapp);
        /*
        Presenter_Inventory pr_inventory = new Presenter_Inventory(user_id, clapp);
        Presenter_History pr_history = new Presenter_History(user_id, clapp);
        Presenter_Calendar pr_calendar = new Presenter_Calendar(user_id, clapp);
        Presenter_Journey pr_journey = new Presenter_Journey(user_id, clapp);
        Presenter_New_Medicine pr_new_medicine = new Presenter_New_Medicine(user_id, clapp);
        Presenter_New_Prescription pr_new_prescription = new Presenter_New_Prescription(user_id, clapp);

        String json_response_delete_treatment = "";
        String json_response_treatments = "";
        String json_response_inventory = "";
        String json_response_history = "";
        String json_response_calendar = "";
        String json_response_journey = "";
        String json_response_new_medicine = "";
        String json_response_new_prescription = "";

        boolean deleted_treatment = pr_treatments.process_Delete_Treatment(json_response_delete_treatment);
            System.out.println("Test of DELETED TREATMENT");
            System.out.println(deleted_treatment);
            System.out.println("");

            List<Map<String,String>> llista_de_treatments = pr_treatments.processTreatments(json_response_treatments);
            System.out.println("Test of LIST OF TREATMENTS");
            System.out.println(llista_de_treatments.toString());
            System.out.println("");
        
        List<Map<String,String>> llista_de_inventory = pr_treatments.processInventory(json_response_inventory);
            System.out.println("Test of INVENTORY");
            System.out.println(llista_de_inventory.toString());
            System.out.println("");
        
        Map<String,String> mapa_history = pr_treatments.processHistory(json_response_history);
            System.out.println("Test of HISTORY");
            System.out.println(mapa_history.toString());
            System.out.println("");

        Map<String,String> mapa_calendar_tasks= pr_treatments.processCalendar(json_response_calendar);
            System.out.println("Test of CALENDAR TASKS");
            System.out.println(mapa_calendar_tasks.toString());
            System.out.println("");

        Map<String,String> mapa_journey =  pr_treatments.processJourney(json_response_calendar);
            System.out.println("Test of JOURNEY");
            System.out.println(mapa_journey.toString());
            System.out.println("");

        boolean new_Medicine = pr_new_medicine.processNewMedicine(json_response_new_medicine);
            System.out.println("Test of NEW MEDICINE");
            System.out.println(new_Medicine);
            System.out.println("");

        boolean new_Prescription = pr_new_prescription.processNewPrescription(json_response_new_prescription);
            System.out.println("Test of NEW PRESCRIPTION");
            System.out.println(new_Prescription);
            System.out.println("");





        

        */
    }

}