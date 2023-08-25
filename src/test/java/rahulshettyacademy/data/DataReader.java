package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>>  getJsonDataToMap() throws IOException {
		//Read JOSN to String
		String jsonConnect = FileUtils.readFileToString(new File (System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchageOrder.json"), 
				StandardCharsets.UTF_8);
		 //String to HashMap we need dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonConnect, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
		//{{hmap1},{hmap2}}
	}

}
