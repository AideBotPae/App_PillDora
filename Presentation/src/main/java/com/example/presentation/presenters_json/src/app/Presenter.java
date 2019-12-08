package app;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


import com.google.gson.Gson;



public class Presenter {
    Gson gson;
    Query query;
    Client_App client;

    public Presenter(String user_id, Client_App client){
        this.query = new Query(user_id);
        this.client = client;
        this.gson = new Gson();

    }

    public String query_creator(String function, Map<String, String> parameters){
        this.query.setParam(function, parameters);
        String json_parsed = this.gson.toJson(this.query);

        return json_parsed;
    }

    public Map<String, Object> processInformation(String json_rebut){
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> map = gson.fromJson(json_rebut, type);
        return map;
    }

    public class Query{
        String user_id;
        String function;
        Map<String, String> parameters;
        
        public Query(String user_id){
            this.user_id = user_id;
            this.function = null;
            this.parameters = new HashMap<>();
        }

        public void setParam(String function, Map<String, String> parameters){
            this.function = function;
            this.parameters = parameters;
        }
    }

	public Client_App getClientApp() {
		return this.client;
	}

    public Gson getGSON() {
		return this.gson;
	}

}