import java.util.Scanner;

public class Exercise6 {

    public static boolean operation(char x) {
        return (x=='*' || x=='+' || x=='-' || x=='/' || x=='^');
    }
    public static boolean extraOperation(char x) { return (x == '-' || x == '/');}
    public static boolean number(char x) {return (x>47 && x<58);}

    public static boolean legalMathChars(String expression) {
        boolean flag = true;
        for (int i=0; i<expression.length(); i++) {
            int currentChar = expression.charAt(i);
            if (!operation(expression.charAt(i)) && !number(expression.charAt(i)) && !(currentChar==40 || currentChar==41))
                flag = false;
        }
        return flag;
    }

    public static boolean isExpression (String expression) {
        boolean legalChars = legalMathChars(expression);

        if (operation(expression.charAt(0)) || operation(expression.charAt(expression.length()-1)))
            return false;

        int parenthesis = 0;
        boolean parenthesisOrder = true;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                parenthesis++;
            }
            else if (expression.charAt(i) == ')') {
                parenthesis--;
            }
            if (parenthesis<0) {
                parenthesisOrder = false;
            }
        }

        String state = "";
        boolean order = true;
        for (int i = 0; i<expression.length(); i++) {
            if (number(expression.charAt(i))) {
                if (state.equals(")")) {
                    order = false;
                    break;
                }
                state = "num";
            }
            else if (operation(expression.charAt(i))) {
                if (state.equals("(") || state.equals("operation")) {
                    order = false;
                    break;
            }
                state = "operation";
            }
            else if (expression.charAt(i) == '(') {
                if (state.equals("number") || state.equals(")")) {
                    order = false;
                    break;
                }
                state = "(";
            }
            else if (expression.charAt(i) == ')') {
                if (state.equals("operation") || state.equals("(")) {
                    order = false;
                    break;
                }
                state = ")";
            }
        }
        return (parenthesis == 0 && parenthesisOrder && legalChars && order);
    }
/////////////////////////////////////////////////////////////////////////////////////////

    public static String convertDoubleToString(double num) {
       String num1 =num +  "";
       return num1;
    }

    public static double convertStringToDouble(String num) {
        String fakeNum = new String(num);
        double doubleNum = 0;
        char operation = '0';
        if (extraOperation(fakeNum.charAt(0))) {
            operation = fakeNum.charAt(0);
            fakeNum = fakeNum.substring(1);
        }
        doubleNum = Double.parseDouble(fakeNum);

        if (operation == '/')
            doubleNum = 1/ doubleNum;
        else if (operation == '-')
            doubleNum = -doubleNum;
        return doubleNum;
    }

    public static double makeOperation(String expression, char operation, char extraOperation) {
        double result = 0;

        int countOperation = 0;
        for (int i=0; i<expression.length(); i++) {
            if (expression.charAt(i) == operation || expression.charAt(i) == extraOperation)
                countOperation++;
        }
        String [] exp = new String[countOperation +1];
        String help = "";
        int j=0;
        for (int i=0; i<expression.length(); i++) {
            if (expression.charAt(i) == operation || expression.charAt(i) == extraOperation) {
                exp[j] = help;
                j++;
                help = "";
                if (expression.charAt(i) == extraOperation) {
                    help = help + expression.charAt(i);
                }
            }
            else {
                help = help + expression.charAt(i);
            }
        }
        exp[exp.length-1] = help;

        if (operation == '+') {
            for (int i=0; i<exp.length; i++) {
                result = result + makeOperation(exp[i],'*', '/');
            }
        }
        else if (operation == '*') {
            result = 1;
            for (int i=0; i<exp.length; i++) {
                result = result * makeOperation(exp[i],'^', 'X');
            }
        }
        else if (operation == '^') {
            result  = convertStringToDouble(exp[0]);
            for (int i=1; i< exp.length; i++) {
                result = Math.pow(result, convertStringToDouble(exp[i]));
            }
        }
        return result;

    }

    public static double calculate (String expression) {
        double result = 0;
        boolean parenthesisExist = false;
        for (int i=0; i<expression.length(); i++) {
            if (expression.charAt(i) == '(')
                parenthesisExist = true;
        }
        if (parenthesisExist) {
            int parenthesis = 0;
            int indexStart = 0;
            int indexEnd = 0;
            for (int i=0; i<expression.length(); i++) {
                if (expression.charAt(i) == '(') {
                    parenthesis++;
                    if (parenthesis == 1)
                        indexStart = i;
                }
                else if(expression.charAt(i) == ')') {
                    parenthesis--;
                    if (parenthesis == 0) {
                        indexEnd = i;
                        expression = expression.substring(0,indexStart) + convertDoubleToString(calculate(expression.substring(indexStart+1,indexEnd))) + expression.substring(indexEnd+1);
                        result = calculate(expression);
                    }
                }
            }
        }
        else {
            result = makeOperation(expression,'+','-');
        }
        return result;
    }
    //////////////////////////////////////////////////////////////

    public static double insertExpressionForCalculate() {
        Scanner scanner = new Scanner(System.in);
        String expression = "";
        do {
            System.out.println("Insert math expression");
            expression = scanner.nextLine();
        } while (!isExpression(expression));

        return calculate(expression);
    }

    public static void main(String[] args) {
        System.out.println(insertExpressionForCalculate());
    }
}
