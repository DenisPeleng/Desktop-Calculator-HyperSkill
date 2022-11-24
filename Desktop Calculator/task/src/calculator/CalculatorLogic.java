package calculator;

import java.util.ArrayList;
import java.util.Stack;

import static calculator.Calculator.*;

public class CalculatorLogic {

    protected static ArrayList<String> infixParse(String data) {
        ArrayList<String> resultArr = new ArrayList<>();
        String[] dataInStr = data.split("");
        StringBuilder tempNumber = new StringBuilder();
        for (int i = 0; i < dataInStr.length; i++) {
            String currentStr = dataInStr[i];
            if (currentStr.contains(String.valueOf(subtractionSymbol)) && i > 0 && dataInStr[i - 1].contains("(")) {

                if (!tempNumber.isEmpty()) {
                    tempNumber.deleteCharAt(tempNumber.length() - 1);
                    resultArr.add(tempNumber.toString());
                    tempNumber = new StringBuilder();
                }


                tempNumber.append(subtractionSymbol);
            } else if (!currentStr.contains("(") && !currentStr.contains(")") && !isOperator(currentStr)) {
                if (currentStr.contains(".")) {
                    if (i == 0 || isOperator(dataInStr[i - 1])) {
                        tempNumber.append("0.");
                    } else if (i < dataInStr.length - 1 && isOperator(dataInStr[i + 1])) {
                        tempNumber.append(".0");
                    } else {
                        tempNumber.append(currentStr);
                    }
                } else {
                    tempNumber.append(currentStr);
                }
            } else {

                if (!tempNumber.isEmpty()) {
                    resultArr.add(tempNumber.toString());
                }
                resultArr.add(currentStr);
                tempNumber = new StringBuilder();
            }
        }
        if (!tempNumber.isEmpty()) {
            resultArr.add(tempNumber.toString());
        }
        return resultArr;
    }


    protected static ArrayList<String> infixToPostfixConvertor(ArrayList<String> expression) {
        Stack<String> stackStr = new Stack<>();
        ArrayList<String> resultStrArr = new ArrayList<>();

        for (int i = 0; i < expression.size(); i++) {
            String currentStr = expression.get(i);
            int precedenceOperator = precedenceOfOperatorSign(currentStr);
            int precedenceOfStackOperator = stackStr.isEmpty() ? 0 : precedenceOfOperatorSign(stackStr.peek());
            if (precedenceOperator != 0) {
                if (currentStr.contains("(")) {
                    stackStr.push(currentStr);
                } else if (currentStr.contains(")")) {
                    while (!stackStr.isEmpty() && !stackStr.peek().contains("(")) {
                        resultStrArr.add(stackStr.pop());
                    }
                    if (!stackStr.isEmpty()) {
                        stackStr.pop();
                    }

                } else if (precedenceOperator > precedenceOfStackOperator) {
                    if (!currentStr.contains("(")) {
                        stackStr.push(currentStr);
                    }
                } else if (precedenceOperator < precedenceOfStackOperator) {
                    resultStrArr.add(stackStr.pop());
                    i--;
                } else {
                    resultStrArr.add(stackStr.pop());
                    stackStr.push(currentStr);
                }
            } else {
                resultStrArr.add(currentStr);
            }
        }
        while (!stackStr.isEmpty()) {
            resultStrArr.add(String.valueOf(stackStr.pop()));
        }
        return resultStrArr;
    }


    protected static int precedenceOfOperatorSign(String str) {
        switch (str.toCharArray()[0]) {
            case subtractionSymbol, additionSymbol -> {
                return 1;
            }
            case multiplicationSymbol, divisionSymbol -> {
                return 2;
            }
            case powSymbol, rootSymbol -> {
                return 3;
            }
            case '(', ')' -> {
                return -1;
            }
            default -> {
                return 0;
            }
        }
    }

    protected static boolean isOperator(String str) {
        if (str.length() == 1) {
            switch (str.toCharArray()[0]) {
                case subtractionSymbol, additionSymbol, multiplicationSymbol, divisionSymbol, powSymbol, rootSymbol -> {
                    return true;
                }
                default -> {
                    return false;
                }
            }
        } else return false;
    }

    protected static String calculatePostfixExpression(ArrayList<String> expression) {
        Stack<Double> stackDouble = new Stack<>();
        for (String currentStr : expression) {
            if (!isOperator(currentStr)) {
                stackDouble.push(Double.parseDouble(currentStr));
            } else if (currentStr.contains(String.valueOf(rootSymbol))) {
                double number1 = stackDouble.pop();
                stackDouble.push(Math.sqrt(number1));
            } else {
                double number1 = stackDouble.pop();
                if (currentStr.toCharArray()[0] == subtractionSymbol && stackDouble.isEmpty()) {
                    double newNumber = 0 - number1;
                    stackDouble.push(newNumber);
                } else {
                    double number2 = stackDouble.pop();
                    double result = 0;
                    switch (currentStr.toCharArray()[0]) {
                        case additionSymbol -> result = number2 + number1;
                        case subtractionSymbol -> result = number2 - number1;
                        case multiplicationSymbol -> result = number2 * number1;
                        case divisionSymbol -> result = number2 / number1;
                        case powSymbol -> result = Math.pow(number2, number1);
                    }
                    stackDouble.push(result);

                }
            }
        }

        double resultNumber = stackDouble.pop();
        return resultNumber % 1 == 0 ? String.valueOf((int) resultNumber) : String.valueOf(resultNumber);
    }

    protected static String getLastSymbol(String str) {
        String[] strArr = str.split("");
        return strArr[strArr.length - 1];
    }

    protected static String infixArrToStr(ArrayList<String> arrayListStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String currentStr : arrayListStr) {
            stringBuilder.append(currentStr);
        }
        return stringBuilder.toString();
    }

    protected static ArrayList<String> insertNegation(ArrayList<String> infixExpression) {
        Stack<String> tempSymbols = new Stack<>();
        ArrayList<String> newInfixExpression = new ArrayList<>();
        for (int i = infixExpression.size() - 1; i >= 0; i--) {
            String currentStr = infixExpression.get(i);
            if (currentStr.contains("(")) {
                tempSymbols.push(currentStr);
                if (i > 0) {
                    newInfixExpression.addAll(infixExpression.subList(0, i));

                }
                break;
            } else {
                tempSymbols.push(currentStr);
            }
        }
        newInfixExpression.add("(-");
        while (!tempSymbols.isEmpty()) {
            newInfixExpression.add(tempSymbols.pop());
        }
        newInfixExpression.add(")");
        return newInfixExpression;
    }

    protected static boolean isContainsNegation(ArrayList<String> infixExpression) {

        for (int i = 0; i < infixExpression.size(); i++) {
            if (infixExpression.get(i).contains("(") && i + 1 < infixExpression.size() && infixExpression.get(i + 1).contains(String.valueOf(subtractionSymbol))) {
                return true;
            }
        }

        return false;
    }

    protected static ArrayList<String> eraseNegation(ArrayList<String> infixExpression) {
        ArrayList<String> newInfixExpression = new ArrayList<>();
        int amountParentheses = 0;
        boolean negationFlag = false;
        for (int i = 0; i < infixExpression.size(); i++) {
            if (infixExpression.get(i).contains("(")) {
                amountParentheses++;
            }
            if (infixExpression.get(i).contains(")")) {
                amountParentheses--;
                if (negationFlag && amountParentheses == 0) {
                    break;
                }
            }
            if (infixExpression.get(i).contains("(") && (i + 1 < infixExpression.size()) && infixExpression.get(i + 1).contains(String.valueOf(subtractionSymbol))) {
                String negativeStr = infixExpression.get(i + 1);
                if (negativeStr.split("").length > 1 && Double.parseDouble(negativeStr) < 0) {
                    double newNumber = 0 - Double.parseDouble(infixExpression.get(i + 1));
                    String strNumber = newNumber % 1 == 0 ? String.valueOf((int) newNumber) : String.valueOf(newNumber);
                    newInfixExpression.add(strNumber);
                } else {
                    infixExpression.remove(i);
                    infixExpression.remove(i);

                }
                i++;
                negationFlag = true;
            }
        }
        return newInfixExpression;
    }

    protected static boolean isInvalidExpression(String expression) {
        String lastSymbol = getLastSymbol(expression);
        if (lastSymbol.contains("(")) {
            return true;
        } else if (expression.contains(divisionSymbol + "0")) {
            return true;
        }
        return isOperator(lastSymbol);
    }
}
