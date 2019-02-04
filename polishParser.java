/**
 * This calculator computes expressions using normal polish notation (NPN).
 * i.e. to calculate 3 + 4, it must be written + 3 4.
 *
 * @author (Sam Keshmiri)
 * @version (04.02.2018)
 */
import java.util.*;
public class polishParser {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) { // try with resource
            Stack<String> originalStack = new Stack<String>();
            Stack<Integer> numberStack = new Stack<Integer>();

            while (scanner.hasNext()) {
                originalStack.push(scanner.next());
            }

            for (int i = originalStack.size(); i > 0; i--){
                if (originalStack.peek().matches("-?[0-9]+")){
                    numberStack.push(Integer.parseInt(originalStack.pop()));
                } else {
                    char operator = originalStack.pop().charAt(0);
                    if (numberStack.size()>1) {
                        int leftOperand = numberStack.pop();
                        int rightOperand = numberStack.pop();
                        numberStack.push(calculate(operator, leftOperand, rightOperand));
                    }
                }
            }
            System.out.println(numberStack.pop());
        }
    }

    public static int calculate(char operator, int leftOperand, int rightOperand) throws IllegalArgumentException {
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
}
