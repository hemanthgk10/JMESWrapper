package org.wrapper;

import org.junit.Assert;
import org.junit.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JmesWrapperTest {
	//JmesPathWrapper jmespath = new JmesPathWrapper();

	@Test
	public void jsonTest() {
		String expression = "foo.bar.baz[2]";

		String jsonLine = "{"
        		+ "\"foo\": "
        		+ "{\"bar\": "
        		+ "{\"baz\": [0, 1, 2, 3, 4]}}}";
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject  jobject = jelement.getAsJsonObject();
        System.out.println("JSON DATA:" + jobject.toString());
        
		Object result = JmesWrapper.search(jobject.toString(), expression);
		Assert.assertEquals(2, result);
	}
	
	@Test(expected = SyntaxException.class)
	public void invalidJsonTest(){
		String expression = "foo.bar.baz[2]";

		String jsonLine = "{"
        		+ "\"foo\": "
        		+ "{\"bar\": "
        		+ "{\"baz\": 0, 1, 2, 3, 4]}}}";
		
		Object result = JmesWrapper.search(jsonLine.toString(), expression);
	}
	
	@Test
	public void stringJsonTest(){
		String expression = "foo.bar.baz[2]";

		String jsonLine = "{"
        		+ "\"foo\": "
        		+ "{\"bar\": "
        		+ "{\"baz\": [0, 1, 2, 3, 4]}}}";
		
		Object result = JmesWrapper.search(jsonLine.toString(), expression);
		Assert.assertEquals(2, result);
	}
	
	@Test
	public void functionTest() {
		String expression = "length(people)";
		String jsonLine = "{"
				+ "\"people\": ["
				+ "{"
				+ "\"name\" : \"b\","
				+ "\"age\" : 30"
				+ "},"
				+ "{"
				+ "\"name\" : \"c\","
				+ "\"age\" : 30"
				+ "},"
				+ "{"
				+ "\"name\" : \"d\","
				+ "\"age\" : 30"
				+ "}"
				+ "]"
				+ "}";
		Object result = JmesWrapper.search(jsonLine.toString(), expression);
		Assert.assertEquals("3", result.toString());
	}
}
