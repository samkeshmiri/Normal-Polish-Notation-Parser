/**
 * This calculator computes expressions using normal polish notation (NPN).
 * i.e. to calculate 3 + 4, it must be written + 3 4.
 *
 * @author (Sam Keshmiri)
 * @version (15.01.2018)
 */
import java.util.*;

public class polishParser
{   
    /**
    * Calculate NPN expression given in the standard input
    */
     public static void main(String[] args){
        try (Scanner scanner = new Scanner(System.in)){ // try with resource
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(args));
            while(scanner.hasNext()){
                list.add(scanner.next());
            }
            polishParser polishParser = new polishParser();
            String[] newArgs = new String[list.size()]; // initialize to same size as list elements
            list.toArray(newArgs);
            int result = polishParser.compute(newArgs);
            System.out.println(result);
        } catch (Exception e){
            e.printStackTrace(); //System.out.println(e.getMessage());
        }
    }

    /**
     * Recursively joins an array with whitespace
     */
    private String buildArgs(String output, int index, String[] args) { // concatenated string is the output
        if(index >= args.length){
            return output;
        } else {
            output += " " + args[index];
            return buildArgs(output, index + 1, args);
        }
    }

     /**
     * Performs evaluation of given expression on digits in the numbers list
     */
    private int calculate(char operator, int leftOperand, int rightOperand) throws IllegalArgumentException {
        switch(operator) {

        case '+':
            return leftOperand + rightOperand;
        case '-':
            return leftOperand - rightOperand;
        case 'x':
            return leftOperand * rightOperand;
        default:
          throw new IllegalArgumentException("Incorrect input detected");
        }
    }

    /**
     * Takes the expression as parameter and passes them into their respective lists to be calculate
     * Removes an operator, the first number in the stack (left operand) and then the next (right operand
     * Total is recursively recorded when performing multiple operations.
     */
    private int compute(String[] args) throws IllegalArgumentException {
        String str = buildArgs("", 0, args).trim();
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        LinkedList<Character> operators = new LinkedList<Character>();

        for (String elem : str.split("\\s+")) {
            if(elem.matches("[+\\-x]")) { // check if elem is an operator
                operators.add(elem.charAt(0)); // add that operator into the list of operators
            } else if(elem.matches("-?[0-9]+")){
                numbers.add(Integer.parseInt(elem));
            } else {
                throw new IllegalArgumentException("Incorrect input detected. Please use + - or x to add, subtract or multiply.");
            }
        }

        int total = 0;
        boolean firstRun = true;
        if (operators.size() == 0) {
            return numbers.remove();
        } else {
            while (operators.size() > 0) {
                if (firstRun) {
                    total = numbers.remove();
                    firstRun = false;
                }
                total = calculate(operators.removeLast(), total, numbers.remove());
            }
            return total;
        }
    }
}