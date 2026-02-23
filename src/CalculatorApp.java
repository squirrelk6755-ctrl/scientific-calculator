import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0;
    private String operator = "";

    public CalculatorApp() {

        setTitle("Advanced Scientific Calculator");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 26));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(450, 70));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 4, 8, 8));

        String[] buttons = {
                "sin", "cos", "tan", "√",
                "log", "ln", "x²", "xʸ",
                "π", "e", "+/-", "C",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        try {

            if (command.matches("[0-9.]")) {
                display.setText(display.getText() + command);
            }

            else if (command.matches("[+\\-*/]")) {
                num1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            }

            else if (command.equals("=")) {
                double num2 = Double.parseDouble(display.getText());
                double result = 0;

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            display.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    case "xʸ":
                        result = Math.pow(num1, num2);
                        break;
                }

                display.setText(String.valueOf(result));
            }

            else if (command.equals("sin")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sin(Math.toRadians(value))));
            }

            else if (command.equals("cos")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.cos(Math.toRadians(value))));
            }

            else if (command.equals("tan")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.tan(Math.toRadians(value))));
            }

            else if (command.equals("√")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sqrt(value)));
            }

            else if (command.equals("log")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.log10(value)));
            }

            else if (command.equals("ln")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.log(value)));
            }

            else if (command.equals("x²")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.pow(value, 2)));
            }

            else if (command.equals("π")) {
                display.setText(display.getText() + Math.PI);
            }

            else if (command.equals("e")) {
                display.setText(display.getText() + Math.E);
            }

            else if (command.equals("+/-")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(-value));
            }

            else if (command.equals("C")) {
                display.setText("");
            }

        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}