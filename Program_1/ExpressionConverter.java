import java.util.Scanner;
import java.util.Stack;

public class ExpressionConverter {
    
 
 
   public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // Pop '('
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid infix expression";
            }
            postfix.append(stack.pop());
        }
        
        return postfix.toString();
    }
    
    public static String postfixToInfix(String postfix) {
    Stack<String> stack = new Stack<>();
    
    for (String token : postfix.split("\\s+")) {
        if (token.matches("[a-zA-Z0-9]+")) {
            stack.push(token);
        } else {
            String operand2 = stack.pop();
            String operand1 = stack.pop();
            String expression = "(" + operand1 + " " + token + " " + operand2 + ")";
            stack.push(expression);
        }
    }
    
    return stack.pop();
    }
    
    public static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("What expression would you like to convert your expression to? (infix, postfix, or quit) ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("quit")) {
                break;
            } else if (choice.equals("postfix")) {
                System.out.print("Enter infix expression: ");
                String infix = scanner.nextLine();
                String postfix = infixToPostfix(infix);
                System.out.println("Postfix expression: " + postfix);
            } else if (choice.equals("infix")) {
                System.out.print("Enter postfix expression: ");
                String postfix = scanner.nextLine();
                String infix = postfixToInfix(postfix);
                System.out.println("Infix expression: " + infix);
            } else {
                System.out.println("Invalid choice. Please enter 'infix', 'postfix', or 'quit'.");
            }
        }

        System.out.println("Exiting the program.");
    }
}
