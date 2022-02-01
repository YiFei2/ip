package bobby;

import java.time.format.DateTimeParseException;

/**
 * The Parser class deals with converting user input into commands
 * understood by the program and executes them accordingly.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Converts user input into commands, passes the commands to TaskList
     * to be executed, or terminates the Bobby program.
     * @param tasks TaskList that handles the execution of commands.
     * @param userInput user input to be parsed.
     * @param bobby instance of Bobby to be terminated if BYE command given.
     */
    public static void parse(TaskList tasks, String userInput, Bobby bobby) {
        String[] inputs = userInput.split(" ", 2);
        String command = inputs[0];
        try {
            switch (Commands.valueOf(command.toUpperCase())) {
            case BYE:
                Ui.printExit();
                bobby.terminate();
                break;
            case LIST:
                tasks.list();
                break;
            case MARK:
                try {
                    int i = Integer.parseInt(inputs[1]) - 1;
                    tasks.mark(i);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case UNMARK:
                try {
                    int k = Integer.parseInt(inputs[1]) - 1;
                    tasks.unmark(k);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case TODO:
                try {
                    tasks.addToDo(userInput);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case DEADLINE:
                try {
                    tasks.addDeadline(userInput);
                } catch (BobbyException e) {
                    System.out.println(e);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
                break;
            case EVENT:
                try {
                    tasks.addEvent(userInput);
                } catch (BobbyException e) {
                    System.out.println(e);
                }
                break;
            case DELETE:
                try {
                    tasks.delete(userInput);
                } catch (BobbyException e) {
                    System.err.println(e);
                }
                break;
            default:
                try {
                    throw new BobbyException("Bobby does not understand you. Please use valid inputs.");
                } catch (BobbyException e) {
                    System.err.println(e);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Bobby does not understand you. Please use valid inputs.");
        }
    }
}
