package org.wrapper;


import java.io.InputStream;
import java.io.InputStreamReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JmesWrapper {
	
	private static ScriptEngine engine;
	
	public static Object search(String json, String expression){
		initializeEngine();
		if(!isValidJson(json))
			throw new SyntaxException("Not a valid Json");
		
		String searchCypher = "jmespath.search(" + json + ",'" + expression +"')";
		System.out.println("Search Query:" + searchCypher);
		try {
			return engine.eval(searchCypher);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	private static boolean isValidJson(String jsonString){
		try{
			JsonParser parser = new JsonParser();
			parser.parse(jsonString);
		} catch(JsonSyntaxException jse){
			return false;
		} 
		return true;
	}

	private static void initializeEngine(){
        engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			InputStream in = JmesWrapper.class.getResourceAsStream("/js/jmespath.js"); 
			System.out.println("Input Stream:" + in.toString());
		    engine.eval(new InputStreamReader(in));
		} catch (ScriptException e) {
			e.printStackTrace();
		} 
	}
}
