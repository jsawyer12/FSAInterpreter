import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class fsainterpreter {

    static ArrayList<Rule> ruleStore = new ArrayList<Rule>();
    static BufferedReader br;
    static int initialState, currentState;
    static boolean accepted, reachedFinalState;

    public static void lexLine(String fsaLine) {
        boolean isFinal = false;
        String fsaLine2 = fsaLine.replace(" ", "");
        if (fsaLine2.length() < 3 || fsaLine2.length() > 4) {
            System.out.println("Incorrect format for rule: \"" +fsaLine2 +"\" rule ignored");
        }
        else {
            if (fsaLine2.length() == 4 && fsaLine2.charAt(3) == '*') {
                isFinal = true;
            }
            if (!Character.isDigit(fsaLine2.charAt(0)) || !Character.isDigit(fsaLine2.charAt(2))){
                System.out.println("States each must be digit 1-9: \"" +fsaLine2 +"\" rule ignored");
            }
            else {
                int startingState = Integer.parseInt(fsaLine2.substring(0,1));
                char input = fsaLine2.charAt(1);
                int endingState = Integer.parseInt(fsaLine2.substring(2,3));
                Rule newRule = new Rule(startingState, input, endingState, isFinal);
                if (ruleStore.isEmpty()) {
                    ruleStore.add(newRule);
                    initialState = startingState; //Initializes initial state to initial triple
                    currentState = initialState;
                }
                else {
                    boolean canAdd = true;
                    for (int x = 0; x < ruleStore.size(); x++) {
                        if (ruleStore.get(x).getInput() == newRule.getInput() && ruleStore.get(x).getStartingState() == newRule.getStartingState() && ruleStore.get(x).getEndingState() != newRule.getEndingState()) {
                            System.out.println("New rule contradicts previously input rule");
                            canAdd = false;
                        }
                    }
                    if (canAdd) {
                        ruleStore.add(newRule);
                    }
                }
            }
        }
    }

    public static void runTest(String testLine) {
        for (int a = 0; a < testLine.length(); a++) {
            boolean isRule = false;
            char c = testLine.charAt(a);
            for (int k = 0; k < ruleStore.size(); k++) {
                if (c == ruleStore.get(k).getInput()) {
                    isRule = true;
                    if (currentState == ruleStore.get(k).getStartingState()) {
                        currentState = ruleStore.get(k).getEndingState();
                        if (ruleStore.get(k).getIsFinal()) {
                            reachedFinalState = true;
                        }
                    }
                }
            }
            if (!isRule) {
                accepted = false;
            }
        }
    }

    public static void main(String[] args) {
        String fsaFile = args[0];
        String fsaLine, testLine;
        accepted = true;
        reachedFinalState = false;
        try {
            br = new BufferedReader(new FileReader(fsaFile));
            while ((fsaLine = br.readLine()) != null) {
                lexLine(fsaLine);
            }
            br.close();
            Scanner testFile = new Scanner(System.in);
            while (testFile.hasNext() && (testLine = testFile.nextLine()) != null) {
                runTest(testLine);
                if (!accepted) {
                    System.out.println("Not Accepted");
                    break;
                }
                if (accepted) {
                    if (reachedFinalState) {
                        System.out.println("Accepted");
                        break;
                    }
                }
            }
            testFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("file could not be found");
        } catch (IOException e) {
            System.out.println("Problem reading from file");
        }
    }
}