package calculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static calculator.CalculatorLogic.*;

public class Calculator extends JFrame {
    protected static final char additionSymbol = '\u002B';
    protected static final char subtractionSymbol = '-';
    protected static final char multiplicationSymbol = '\u00D7';
    protected static final char divisionSymbol = '\u00F7';
    protected static final char rootSymbol = '\u221A';
    protected static final char plusMinusSymbol = '\u00b1';
    protected static final char powSymbol = '^';
    private boolean isParenthesesOpened = false;
    private int numberOfParentheses = 0;

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
        JButton PlusMinus = new JButton(String.valueOf(plusMinusSymbol));
        PlusMinus.setBounds(10, 325, 70, 40);
        PlusMinus.setName("PlusMinus");
        add(PlusMinus);
        JButton Zero = new JButton("0");
        Zero.setBounds(80, 325, 70, 40);
        Zero.setName("Zero");
        add(Zero);
        JButton Dot = new JButton(".");
        Dot.setBounds(150, 325, 70, 40);
        Dot.setName("Dot");
        add(Dot);
        JButton Equals = new JButton("=");
        Equals.setName("Equals");
        Equals.setBounds(220, 325, 70, 40);
        add(Equals);
        JButton One = new JButton("1");
        One.setBounds(10, 280, 70, 40);
        One.setName("One");
        add(One);
        JButton Two = new JButton("2");
        Two.setBounds(80, 280, 70, 40);
        Two.setName("Two");
        add(Two);
        JButton Three = new JButton("3");
        Three.setBounds(150, 280, 70, 40);
        Three.setName("Three");
        add(Three);
        JButton Subtract = new JButton("-");
        Subtract.setName("Subtract");
        Subtract.setBounds(220, 280, 70, 40);
        add(Subtract);
        JButton Four = new JButton("4");
        Four.setBounds(10, 235, 70, 40);
        Four.setName("Four");
        add(Four);
        JButton Five = new JButton("5");
        Five.setBounds(80, 235, 70, 40);
        Five.setName("Five");
        add(Five);
        JButton Six = new JButton("6");
        Six.setBounds(150, 235, 70, 40);
        Six.setName("Six");
        add(Six);
        JButton Add = new JButton("+");
        Add.setName("Add");
        Add.setBounds(220, 235, 70, 40);
        add(Add);
        JButton Seven = new JButton("7");
        Seven.setBounds(10, 190, 70, 40);
        Seven.setName("Seven");
        add(Seven);
        JButton Eight = new JButton("8");
        Eight.setBounds(80, 190, 70, 40);
        Eight.setName("Eight");
        add(Eight);
        JButton Nine = new JButton("9");
        Nine.setBounds(150, 190, 70, 40);
        Nine.setName("Nine");
        add(Nine);
        JButton Multiply = new JButton(String.valueOf(multiplicationSymbol));
        Multiply.setName("Multiply");
        Multiply.setBounds(220, 190, 70, 40);
        add(Multiply);
        JButton PowerTwo = new JButton("^2");
        PowerTwo.setBounds(10, 145, 70, 40);
        PowerTwo.setName("PowerTwo");
        add(PowerTwo);
        JButton PowerY = new JButton("^Y");
        PowerY.setBounds(80, 145, 70, 40);
        PowerY.setName("PowerY");
        add(PowerY);
        JButton SquareRoot = new JButton(String.valueOf(rootSymbol));
        SquareRoot.setBounds(150, 145, 70, 40);
        SquareRoot.setName("SquareRoot");
        add(SquareRoot);
        JButton Divide = new JButton(String.valueOf(divisionSymbol));
        Divide.setName("Divide");
        Divide.setBounds(220, 145, 70, 40);
        add(Divide);

        JButton Parentheses = new JButton("()");
        Parentheses.setBounds(10, 100, 70, 40);
        Parentheses.setName("Parentheses");
        add(Parentheses);
        JButton ClearE = new JButton("CE");
        ClearE.setBounds(80, 100, 70, 40);
        ClearE.setName("ClearE");
        add(ClearE);
        JButton Clear = new JButton("C");
        Clear.setBounds(150, 100, 70, 40);
        Clear.setName("Clear");
        add(Clear);
        JButton Delete = new JButton("Del");
        Delete.setBounds(220, 100, 70, 40);
        Delete.setName("Delete");
        add(Delete);

        Equals.addActionListener(e -> {
                    String data = EquationLabel.getText();
                    if (data.isEmpty()) {
                        return;
                    }
                    if (isInvalidExpression(data) || isParenthesesOpened) {
                        EquationLabel.setForeground(Color.RED.darker());
                        return;
                    }
                    ArrayList<String> infixExpression = infixParse(data);
                    EquationLabel.setForeground(Color.GREEN.darker());
                    ArrayList<String> postfixExpression = infixToPostfixConvertor(infixExpression);

                    String resultExpression = calculatePostfixExpression(postfixExpression);
                    double result = Double.parseDouble(resultExpression);
                    if (Double.isNaN(result)) {
                        EquationLabel.setForeground(Color.RED.darker());
                        return;
                    }
                    ResultLabel.setText(resultExpression);
                }

        );
        Clear.addActionListener(e -> {
            EquationLabel.setText("");
            numberOfParentheses = 0;
            isParenthesesOpened = false;
            EquationLabel.setForeground(Color.GREEN.darker());

        });
        ClearE.addActionListener(e -> ResultLabel.setText(""));
        Delete.addActionListener(e ->
        {
            String data = EquationLabel.getText();
            if (data.length() > 0) {
                EquationLabel.setText(data.substring(0, data.length() - 1));
            }
            if (data.length() > 2) {
                String lastSymbol = getLastSymbol(data);
                if (lastSymbol.contains("(")) {
                    numberOfParentheses--;
                    if (numberOfParentheses == 0) {
                        isParenthesesOpened = false;
                    }
                } else if (lastSymbol.contains(")")) {
                    numberOfParentheses++;
                    if (numberOfParentheses > 0) {
                        isParenthesesOpened = true;
                    }
                }

            }
            data = EquationLabel.getText();
            if (isInvalidExpression(data) || isParenthesesOpened) {
                EquationLabel.setForeground(Color.RED.darker());
            } else {
                EquationLabel.setForeground(Color.GREEN.darker());
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
            String lastSymbol = getLastSymbol(data);
            if (lastSymbol.contains("(")) {
                return;
            }
            if (isOperator(lastSymbol)) {
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
            String lastSymbol = getLastSymbol(data);
            if (lastSymbol.contains("(")) {
                return;
            }
            if (isOperator(lastSymbol)) {
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
            String lastSymbol = getLastSymbol(data);
            if (lastSymbol.contains("(")) {
                return;
            }
            if (isOperator(lastSymbol)) {
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
            String lastSymbol = getLastSymbol(data);
            if (lastSymbol.contains("(")) {
                return;
            }
            if (isOperator(lastSymbol)) {
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

        Parentheses.addActionListener(e -> {
            String data = EquationLabel.getText();
            String lastSymbol = "";
            if (!data.isEmpty()) {
                lastSymbol = getLastSymbol((data));
            }

            if (!data.isEmpty() && !isParenthesesOpened && !isOperator(lastSymbol)) {
                return;
            }
            if (!data.isEmpty() && isParenthesesOpened && isOperator(lastSymbol)) {
                return;
            }
            if (lastSymbol.contains("(")) {
                numberOfParentheses++;
                EquationLabel.setText(data + "(");
            } else if (isParenthesesOpened) {
                EquationLabel.setText(data + ")");
                numberOfParentheses--;
                if (numberOfParentheses < 1) {
                    isParenthesesOpened = false;
                }
            } else {
                EquationLabel.setText(data + "(");
                numberOfParentheses++;
                isParenthesesOpened = true;
            }

        });
        SquareRoot.addActionListener(e -> {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + rootSymbol + "(");
            isParenthesesOpened = true;
            numberOfParentheses++;


        });
        PowerY.addActionListener(e -> {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "^(");
            isParenthesesOpened = true;
            numberOfParentheses++;
        });
        PowerTwo.addActionListener(e -> {
            String data = EquationLabel.getText();
            EquationLabel.setText(data + "^(2)");
        });
        PlusMinus.addActionListener(e -> {
            String data = EquationLabel.getText();
            String lastSymbol;
            if (!data.isEmpty()) {
                ArrayList<String> infixExpression = infixParse(data);
                lastSymbol = getLastSymbol(data);
                if (isContainsNegation(infixExpression)) {
                    ArrayList<String> newInfixExpression = eraseNegation(infixExpression);
                    isParenthesesOpened = false;
                    numberOfParentheses--;
                    EquationLabel.setText(infixArrToStr(newInfixExpression));
                } else if (lastSymbol.contains(")")) {
                    if (isContainsNegation(infixExpression)) {
                        ArrayList<String> newInfixExpression = eraseNegation(infixExpression);
                        EquationLabel.setText(infixArrToStr(newInfixExpression));
                    } else {
                        ArrayList<String> newInfixExpression = insertNegation(infixExpression);
                        EquationLabel.setText(infixArrToStr(newInfixExpression));
                    }

                } else if (!isOperator(lastSymbol)) {
                    infixExpression.set(infixExpression.size() - 1, "(-");
                    infixExpression.add(lastSymbol);
                    isParenthesesOpened = true;
                    numberOfParentheses++;
                    EquationLabel.setText(infixArrToStr(infixExpression));

                }
            } else {
                isParenthesesOpened = true;
                numberOfParentheses++;
                EquationLabel.setText("(-");
            }
        });
    }


}


