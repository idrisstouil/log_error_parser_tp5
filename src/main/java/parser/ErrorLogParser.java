package parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ErrorLogParser {
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.out.println("Please provide the file path as a command line argument");
			return;
		}
		
		String fileName = args[0];
		File logFile = new File(fileName);
		String logFileDirectory = logFile.getParent();
		parseFile(fileName,logFileDirectory);
	} 
		public static List<ErrorLogEntry> parseFile(String filePath,String logFileDirectory) throws IOException {
        List<ErrorLogEntry> errorLogEntries = new ArrayList<ErrorLogEntry>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("ERROR")) {
            	errorLogEntries.add(parseLine(line));
            }
        }
        reader.close();
        List<Map<String, Integer>> sortedErrors = ErrorSorter.sortErrors(countErrors(errorLogEntries));
        ErrorReportGenerator.generateReport(sortedErrors,logFileDirectory + File.separator + "sorted_log_error.json");
        return errorLogEntries;
    }
    public static ErrorLogEntry parseLine(String line) {
        // Extraire les différentes parties de la ligne de log
        String[] parts = line.split(" - ");
        String errorType = parts[0].split(" ")[3];
        String[] userInfo = parts[1].split(";")[1].split(", ");
        String methodName = parts[1].split(";")[0].split(": ")[1];
        String inputValuesString = parts[1].split(";")[2];

       User user = new User(userInfo[0].split("=")[1], userInfo[1].split("=")[1],userInfo[2].split("=")[1], userInfo[3].split("=")[1]);

        // Créer la liste d'objets représentant les valeurs en entrée
        List<Object> inputValues = new ArrayList<Object>();
        if (inputValuesString.startsWith("Constructor")) {
            // Pas de valeurs en entrée
        } else {
            // Extraire les valeurs en entrée à partir de la chaîne de caractères
            String[] inputValuesArray = inputValuesString.split(", ");
            for (String inputValue : inputValuesArray) {
                inputValues.add(inputValue);
            }
        }

        // Créer et retourner l'objet ErrorLogEntry
        return new ErrorLogEntry(user, methodName, errorType, inputValues);
    }
    public static Map<String, Integer> countErrors(List<ErrorLogEntry> logEntries) {
        Map<String, Integer> errorCounts = new HashMap<String, Integer>();
        for (ErrorLogEntry entry : logEntries) {
            String errorType = entry.getErrorType();
            if (!errorCounts.containsKey(errorType)) {
                errorCounts.put(errorType, 1);
            } else {
                errorCounts.put(errorType, errorCounts.get(errorType) + 1);
            }
        }
        return errorCounts;
    }
}