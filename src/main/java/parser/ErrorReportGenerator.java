package parser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ErrorReportGenerator {
    public static void generateReport(List<Map<String, Integer>> sortedErrors, String logFileDirectory) {
        ObjectMapper mapper = new ObjectMapper();
        for (Map<String, Integer> error : sortedErrors) {
        	  for (Map.Entry<String, Integer> entry : error.entrySet()) {
        	    System.out.println(entry.getKey() + ": " + entry.getValue());
        	  }
        	}

        try {
            mapper.writeValue(new FileWriter("error_report.json"), sortedErrors);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
}