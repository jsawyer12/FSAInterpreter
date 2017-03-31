public class Rule {

    private int startingState;
    private char input;
    private int endingState;
    private boolean isFinal;


    public int getStartingState() {
        return this.startingState;
    }

    public char getInput() {
        return this.input;
    }

    public int getEndingState() {
        return this.endingState;
    }

    public boolean getIsFinal() {
        return this.isFinal;
    }

    public Rule(int startingState, char input, int endingState, boolean isFinal) {
        this.startingState = startingState;
        this.input = input;
        this.endingState = endingState;
        this.isFinal = isFinal;
    }
}
