package app;

import java.util.HashMap;
import java.util.Map;

public class Test_Presenter {

    public static void main (String [ ] args) {

        String user_id = "hamza";
        Client_App clapp = new Client_App(user_id);
        Presenter pr = new Presenter(user_id, clapp);

        String function = "dummy_test_1";
        HashMap<String, String> parameters = new HashMap<String,String>();
        parameters.put("key_1", "value_1");
        parameters.put("key_2", "value_2");
        parameters.put("key_3", "value_3");
        String query_jason = pr.query_creator(function, parameters);

        System.out.println(query_jason);

        Map<String, Object> parsed = pr.processInformation(query_jason);

        String parsed_1 = parsed.toString();
        
        System.out.println(parsed.get("parameters").getClass());


    }

}