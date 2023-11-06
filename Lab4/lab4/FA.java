package lab4;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class FA {
    private List<String> states;
    private List<String> alphabet;
    private List<Transition> transitions;
    private String initialState;
    private List<String> outputStates;
    private String filename;

    public FA(String filename) {
        this.filename = filename;
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.transitions = new ArrayList<>();
        this.initialState = "";
        this.outputStates = new ArrayList<>();
        try {
            init();
        } catch (Exception e) {
            System.out.println("Error when initializingFA");
        }
    }


    private void init() throws Exception {
        var regex = Pattern.compile("^([a-z_]*)=");
        for (String line: Files.readAllLines(Paths.get(filename))) {
            var matcher = regex.matcher(line);
            var match = matcher.find();
            if (matcher.group(0) == null) {
                throw new Exception("Invalid line: " + line);
            }
            switch (matcher.group(0)) {
                case "states=":
                    var statesWithCurlyBrackets = line.substring(line.indexOf("=") + 1);
                    var states = statesWithCurlyBrackets.substring(1, statesWithCurlyBrackets.length() - 1).trim();
                    this.states = List.of(states.split(", *"));
                    break;
                case "alphabet=":
                    var alphabetWithCurlyBrackets = line.substring(line.indexOf("=") + 1);
                    var alphabet = alphabetWithCurlyBrackets.substring(1, alphabetWithCurlyBrackets.length() - 1).trim();
                    this.alphabet = List.of(alphabet.split(", *"));
                    break;

                default:
                    throw new Exception("Invalid line in file");
            }
        }
    }


    public void printStates() {
        printListOfString("states", states);
    }

    public void printAlphabet() {
        printListOfString("alphabet", alphabet);
    }

    public void printOutputStates() {
        printListOfString("out_states", outputStates);
    }

    public void printInitialState() {
        System.out.println("initial_state = " + initialState);
    }

    public void printTransitions() {
        System.out.print("transitions = {");
        for (int i = 0; i < transitions.size(); i++) {
            if (i != transitions.size() - 1) {
                System.out.print("(" + transitions.get(i).getFrom() + ", " + transitions.get(i).getTo() + ", " + transitions.get(i).getLabel() + "); ");
            } else {
                System.out.print("(" + transitions.get(i).getFrom() + ", " + transitions.get(i).getTo() + ", " + transitions.get(i).getLabel() + ")");
            }
        }
        System.out.println("}");
    }

    private void printListOfString(String listname, List<String> list) {
        System.out.print(listname + " = {");
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                System.out.print(list.get(i) + ", ");
            } else {
                System.out.print(list.get(i));
            }
        }
        System.out.println("}");
    }
}


