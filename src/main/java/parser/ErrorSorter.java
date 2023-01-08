package parser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ErrorSorter {
  public static List<Map<String, Integer>> sortErrors(Map<String, Integer> errorCounts) {
    // Création d'une liste d'entrées à partir de la map
    List<Map.Entry<String, Integer>> errorList = new ArrayList<>(errorCounts.entrySet());
 
    // Tri de la liste en fonction du nombre d'occurrences d'erreur (en ordre décroissant)
    Collections.sort(errorList, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    // Conversion de la liste d'entrées en liste de maps (tuples)
    List<Map<String, Integer>> sortedErrors = new ArrayList<>();
    for (Entry<String, Integer> entry : errorList) {
      Map<String, Integer> tuple = Collections.singletonMap(entry.getKey(), entry.getValue());
      sortedErrors.add(tuple);
    }

    return sortedErrors;
  }
}