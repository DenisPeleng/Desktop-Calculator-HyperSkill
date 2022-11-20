package calculator;

import javax.swing.*;

public class Calculator extends JFrame {

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
        JTextField EquationTextField = new JTextField();
        EquationTextField.setBounds(30, 20, 200, 30);
        EquationTextField.setName("EquationTextField");
        EquationTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(EquationTextField);
        JButton Zero = new JButton("0");
        Zero.setBounds(90, 220, 30, 30);
        Zero.setName("Zero");
        add(Zero);
        JButton Equals = new JButton("=");
        Equals.setName("Equals");
        Equals.setBounds(130, 220, 30, 30);
        add(Equals);
        JButton Subtract = new JButton("-");
        Subtract.setName("Subtract");
        Subtract.setBounds(170, 220, 30, 30);
        add(Subtract);
        JButton One = new JButton("1");
        One.setBounds(50, 170, 30, 30);
        One.setName("One");
        add(One);
        JButton Two = new JButton("2");
        Two.setBounds(90, 170, 30, 30);
        Two.setName("Two");
        add(Two);
        JButton Three = new JButton("3");
        Three.setBounds(130, 170, 30, 30);
        Three.setName("Three");
        add(Three);
        JButton Add = new JButton("+");
        Add.setName("Add");
        Add.setBounds(170, 170, 30, 30);
        add(Add);
        JButton Four = new JButton("4");
        Four.setBounds(50, 120, 30, 30);
        Four.setName("Four");
        add(Four);
        JButton Five = new JButton("5");
        Five.setBounds(90, 120, 30, 30);
        Five.setName("Five");
        add(Five);
        JButton Six = new JButton("6");
        Six.setBounds(130, 120, 30, 30);
        Six.setName("Six");
        add(Six);
        JButton Multiply = new JButton("x");
        Multiply.setName("Multiply");
        Multiply.setBounds(170, 120, 30, 30);
        add(Multiply);
        JButton Seven = new JButton("7");
        Seven.setBounds(50, 70, 30, 30);
        Seven.setName("Seven");
        add(Seven);
        JButton Eight = new JButton("8");
        Eight.setBounds(90, 70, 30, 30);
        Eight.setName("Eight");
        add(Eight);
        JButton Nine = new JButton("9");
        Nine.setBounds(130, 70, 30, 30);
        Nine.setName("Nine");
        add(Nine);
        JButton Divide = new JButton("/");
        Divide.setName("Divide");
        Divide.setBounds(170, 70, 30, 30);
        add(Divide);
        Equals.addActionListener(e -> {
            String data = EquationTextField.getText();
            if (data.contains("+")) {
                String[] valuesInt = data.split("\\+");
                int resultExpression = Integer.parseInt(valuesInt[0]) + Integer.parseInt(valuesInt[1]);
                EquationTextField.setText(data + "=" + resultExpression);
            } else if (data.contains("-")) {
                String[] valuesInt = data.split("-");
                int resultExpression = Integer.parseInt(valuesInt[0]) - Integer.parseInt(valuesInt[1]);
                EquationTextField.setText(data + "=" + resultExpression);

            } else if (data.contains("x")) {
                String[] valuesInt = data.split("x");
                int resultExpression = Integer.parseInt(valuesInt[0]) * Integer.parseInt(valuesInt[1]);
                EquationTextField.setText(data + "=" + resultExpression);

            } else if (data.contains("/")) {
                String[] valuesInt = data.split("/");
                int resultExpression = Integer.parseInt(valuesInt[0]) / Integer.parseInt(valuesInt[1]);
                EquationTextField.setText(data + "=" + resultExpression);

            }

        });
        One.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "1");
        });
        Two.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "2");
        });
        Three.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "3");
        });
        Four.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "4");
        });
        Five.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "5");
        });
        Six.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "6");
        });
        Seven.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "7");
        });
        Eight.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "8");
        });
        Nine.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "9");
        });
        Zero.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "0");
        });
        Add.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "+");
        });
        Subtract.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "-");
        });
        Divide.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "/");
        });
        Multiply.addActionListener(e -> {
            String data = EquationTextField.getText();
            EquationTextField.setText(data + "x");
        });
    }


}
