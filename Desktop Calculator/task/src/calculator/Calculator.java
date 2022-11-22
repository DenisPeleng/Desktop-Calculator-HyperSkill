package calculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Calculator extends JFrame {
    private static final char additionSymbol = '\u002B';
    private static final char subtractionSymbol = '-';
    private static final char multiplicationSymbol = '\u00D7';
    private static final char divisionSymbol = '\u00F7';

    public Calculator() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        initComponents();
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JLabel EquationLabel = new JLabel();
        EquationLabel.setBounds(70, 55, 200, 50);
        EquationLabel.setName("EquationLabel");
        EquationLabel.setForeground(Color.GREEN.darker());
        EquationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(EquationLabel);
        JLabel ResultLabel = new JLabel();
        ResultLabel.setBounds(70, 0, 200, 50);
        ResultLabel.setName("ResultLabel");
        Font font = new Font("Courier", Font.BOLD, 25);
        ResultLabel.setFont(font);
        ResultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(ResultLabel);
        JButton Dot = new JButton(".");
        Dot.setBounds(10, 320, 70, 50);
        Dot.setName("Dot");
        add(Dot);
        JButton Zero = new JButton("0");
        Zero.setBounds(80, 320, 70, 50);
        Zero.setName("Zero");
        add(Zero);
        JButton Equals = new JButton("=");
        Equals.setName("Equals");
        Equals.setBounds(150, 320, 70, 50);
        add(Equals);
        JButton Subtract = new JButton("-");
        Subtract.setName("Subtract");
        Subtract.setBounds(220, 320, 70, 50);
        add(Subtract);
        JButton One = new JButton("1");
        One.setBounds(10, 265, 70, 50);
        One.setName("One");
        add(One);
        JButton Two = new JButton("2");
        Two.setBounds(80, 265, 70, 50);
        Two.setName("Two");
        add(Two);
        JButton Three = new JButton("3");
        Three.setBounds(150, 265, 70, 50);
        Three.setName("Three");
        add(Three);
        JButton Add = new JButton("+");
        Add.setName("Add");
        Add.setBounds(220, 265, 70, 50);
        add(Add);
        JButton Four = new JButton("4");
        Four.setBounds(10, 210, 70, 50);
        Four.setName("Four");
        add(Four);
        JButton Five = new JButton("5");
        Five.setBounds(80, 210, 70, 50);
        Five.setName("Five");
        add(Five);
        JButton Six = new JButton("6");
        Six.setBounds(150, 210, 70, 50);
        Six.setName("Six");
        add(Six);
        JButton Multiply = new JButton("x");
        Multiply.setName("Multiply");
        Multiply.setBounds(220, 210, 70, 50);
        add(Multiply);
        JButton Seven = new JButton("7");
        Seven.setBounds(10, 155, 70, 50);
        Seven.setName("Seven");
        add(Seven);
        JButton Eight = new JButton("8");
        Eight.setBounds(80, 155, 70, 50);
        Eight.setName("Eight");
        add(Eight);
        JButton Nine = new JButton("9");
        Nine.setBounds(150, 155, 70, 50);
        Nine.setName("Nine");
        add(Nine);
        JButton Divide = new JButton("/");
        Divide.setName("Divide");
        Divide.setBounds(220, 155, 70, 50);
        add(Divide);
        JButton Clear = new JButton("C");
        Clear.setBounds(150, 100, 70, 50);
        Clear.setName("Clear");
        add(Clear);
        JButton Delete = new JButton("Del");
        Delete.setBounds(220, 100, 70, 50);
        Delete.setName("Delete");
        add(Delete);

        Equals.addActionListener(e -> {
                    String data = EquationLabel.getText();
                    if (data.contains(divisionSymbol + "0")) {
                        EquationLabel.setForeground(Color.RED.darker());
                        return;
                    }
                    ArrayList<String> infixExpression = infixParse(data);
                    EquationLabel.setText(infixArrToStr(infixExpression));
                    if (isOperator(infixExpression.get(infixExpression.size() - 1))) {
                        EquationLabel.setForeground(Color.RED.darker());
                        return;
                    }
                    ArrayList<String> postfixExpression = infixToPostfixConvertor(infixExpression);
                    String resultExpression = calculatePostfixExpression(postfixExpression);
                    ResultLabel.setText(resultExpression);
                }

        );
        Clear.addActionListener(e -> EquationLabel.setText(""));
        Delete.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            if (data.length() > 0) {
                EquationLabel.setText(data.substring(0, data.length() - 1));
            }

        });
        One.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "1");
        });
        Two.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "2");
        });
        Three.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "3");
        });
        Four.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "4");
        });
        Five.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "5");
        });
        Six.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "6");
        });
        Seven.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "7");
        });
        Eight.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "8");
        });
        Nine.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "9");
        });
        Zero.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "0");
        });
        Add.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            if (data.isEmpty()) {
                return;
            }
            if (isLastSymbolOperator(data)) {
                data = data.substring(0, data.length() - 1);
            }
            ArrayList<String> infixExpression = infixParse(data + additionSymbol);
            EquationLabel.setText(infixArrToStr(infixExpression));
        });
        Subtract.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            if (data.isEmpty()) {
                return;
            }
            if (isLastSymbolOperator(data)) {
                data = data.substring(0, data.length() - 1);
            }

            ArrayList<String> infixExpression = infixParse(data + subtractionSymbol);
            EquationLabel.setText(infixArrToStr(infixExpression));
        });
        Divide.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            if (data.isEmpty()) {
                return;
            }
            if (isLastSymbolOperator(data)) {
                data = data.substring(0, data.length() - 1);
            }
            ArrayList<String> infixExpression = infixParse(data + divisionSymbol);
            EquationLabel.setText(infixArrToStr(infixExpression));
        });
        Multiply.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            if (data.isEmpty()) {
                return;
            }
            if (isLastSymbolOperator(data)) {
                data = data.substring(0, data.length() - 1);
            }
            ArrayList<String> infixExpression = infixParse(data + multiplicationSymbol);
            EquationLabel.setText(infixArrToStr(infixExpression));
        });
        Dot.addActionListener(e ->

        {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + ".");


        });
    }

    private ArrayList<String> infixParse(String data) {
        ArrayList<String> resultArr = new ArrayList<>();
        String[] dataInStr = data.split("");
        StringBuilder tempNumber = new StringBuilder();
        for (int i = 0; i < dataInStr.length; i++) {
            String currentStr = dataInStr[i];
            if (!isOperator(String.valueOf(currentStr))) {
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

                resultArr.add(tempNumber.toString());
                resultArr.add(String.valueOf(currentStr));
                tempNumber = new StringBuilder();
            }
        }
        if (!tempNumber.isEmpty()) {
            resultArr.add(tempNumber.toString());
        }
        return resultArr;
    }


    private ArrayList<String> infixToPostfixConvertor(ArrayList<String> expression) {
        Stack<String> stackStr = new Stack<>();
        ArrayList<String> resultStrArr = new ArrayList<>();

        for (int i = 0; i < expression.size(); i++) {
            String currentStr = expression.get(i);
            int precedenceOperator = precedenceOfOperatorSign(currentStr);
            int precedenceOfStackOperator = stackStr.isEmpty() ? 0 : precedenceOfOperatorSign(stackStr.peek());
            if (precedenceOperator != 0) {
                if (stackStr.isEmpty()) {
                    stackStr.push(currentStr);
                } else if (precedenceOperator > precedenceOfStackOperator) {
                    stackStr.push(currentStr);
                } else if (precedenceOperator < precedenceOfStackOperator) {
                    resultStrArr.add(String.valueOf(stackStr.pop()));
                    i--;
                } else {
                    resultStrArr.add(String.valueOf(stackStr.pop()));
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


    private int precedenceOfOperatorSign(String str) {
        switch (str.toCharArray()[0]) {
            case subtractionSymbol, additionSymbol -> {
                return 1;
            }
            case multiplicationSymbol, divisionSymbol -> {
                return 2;
            }
            default -> {
                return 0;
            }
        }
    }

    private boolean isOperator(String str) {
        switch (str.toCharArray()[0]) {
            case subtractionSymbol, additionSymbol, multiplicationSymbol, divisionSymbol -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    private String calculatePostfixExpression(ArrayList<String> expression) {
        Stack<Double> stackDouble = new Stack<>();
        for (String currentStr : expression
        ) {
            if (!isOperator(currentStr)) {
                stackDouble.push(Double.parseDouble(currentStr));
            } else {
                double number1 = stackDouble.pop();
                double number2 = stackDouble.pop();
                double result = 0;
                switch (currentStr.toCharArray()[0]) {
                    case additionSymbol -> result = number2 + number1;
                    case subtractionSymbol -> result = number2 - number1;
                    case multiplicationSymbol -> result = number2 * number1;
                    case divisionSymbol -> result = number2 / number1;
                }
                stackDouble.push(result);

            }
        }

        double resultNumber = stackDouble.pop();
        return resultNumber % 1 == 0 ? String.valueOf((int) resultNumber) : String.valueOf(resultNumber);
    }

    private boolean isLastSymbolOperator(String str) {
        String[] strArr = str.split("");
        return isOperator(strArr[strArr.length - 1]);
    }

    private String infixArrToStr(ArrayList<String> arrayListStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String currentStr : arrayListStr
        ) {
            stringBuilder.append(currentStr);
        }
        return stringBuilder.toString();
    }

}


