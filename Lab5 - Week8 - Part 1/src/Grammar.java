import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Grammar {


    private final String ELEMENT_SEPARATOR = "&";

    private final String SEPARATOR_OR_TRANSITION = "\\|";
    private final String TRANSITION_CONCATENATION = " ";
    private final String EPSILON = "EPS";
    private final String SEPARATOR_LEFT_RIGHT_HAND_SIDE = "->";

    // LR(0)
    private Set<String> nonTerminals;
    private Set<String> terminals;
    private Map<List<String>, Set<List<String>>> productions;
    private String startingSymbol;
    private boolean isCFG;


    /**
     * With this method we load the content from the file (we read every line from the file and
     * classify everything we read as a terminal/non-terminal/production and so on).
     * @param filePath - the path where the file we are reading from is
     */
    private void loadFromFile(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            this.nonTerminals = new HashSet<>(List.of(scanner.nextLine().split(this.ELEMENT_SEPARATOR)));
            this.terminals = new HashSet<>(List.of(scanner.nextLine().split(this.ELEMENT_SEPARATOR)));
            this.startingSymbol = scanner.nextLine();

            this.productions = new HashMap<>();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Grammar(String filePath) {
        this.loadFromFile(filePath);
    }

    public Set<String> getNonTerminals() {
        return this.nonTerminals;
    }

    public Set<String> getTerminals() {
        return this.terminals;
    }

    public Map<List<String>, Set<List<String>>> getProductions() {
        return this.productions;
    }

    public String getStartingSymbol() {
        return this.startingSymbol;
    }

    public boolean isCFG() {
        return this.isCFG;
    }

}
