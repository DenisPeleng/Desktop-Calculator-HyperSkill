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
        EquationTextField.setBounds(100, 20, 120, 30);
        EquationTextField.setName("EquationTextField");
        EquationTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(EquationTextField);
        JButton Solve = new JButton("Solve");
        Solve.setName("Solve");
        Solve.setBounds(100, 70, 100, 30);
        add(Solve);
        Solve.addActionListener(e -> {
            String data = EquationTextField.getText();
            String[] valuesInt = data.split("\\+");
            int resultExpression = Integer.parseInt(valuesInt[0]) + Integer.parseInt(valuesInt[1]);
            EquationTextField.setText(data + "=" + resultExpression);
        });
    }

}
