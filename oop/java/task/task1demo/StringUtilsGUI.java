package task.task1demo;

import oop.task.StringUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StringUtilsGUI extends JFrame {

    private JTextField input1, input2, input3;
    private JTextArea output;
    private JComboBox<String> operation;

    public StringUtilsGUI() {
        setTitle("StringUtils");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Компоненты ---
        JLabel lblOperation = new JLabel("Выберите операцию:");
        JLabel lblInput1 = new JLabel("Строка 1 (основной текст):");
        JLabel lblInput2 = new JLabel("Строка 2 / разделитель / что заменить:");
        JLabel lblInput3 = new JLabel("Доп. параметр / новый текст / join-разделитель / аргументы для format:");

        input1 = new JTextField();
        input2 = new JTextField();
        input3 = new JTextField();

        operation = new JComboBox<>(new String[]{
                "split", "join", "replace", "equalsIgnoreCase",
                "indexOf", "format", "reverse"
        });

        JButton runBtn = new JButton("Выполнить");
        runBtn.addActionListener(this::handleOperation);

        output = new JTextArea(8, 45);
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);

        // --- Размещение элементов ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblOperation, gbc);
        gbc.gridy++; panel.add(operation, gbc);

        gbc.gridy++; panel.add(lblInput1, gbc);
        gbc.gridy++; panel.add(input1, gbc);

        gbc.gridy++; panel.add(lblInput2, gbc);
        gbc.gridy++; panel.add(input2, gbc);

        gbc.gridy++; panel.add(lblInput3, gbc);
        gbc.gridy++; panel.add(input3, gbc);

        gbc.gridy++; panel.add(runBtn, gbc);

        gbc.gridy++; panel.add(new JLabel("Результат:"), gbc);
        gbc.gridy++; panel.add(scrollPane, gbc);

        add(panel);
    }

    private void handleOperation(ActionEvent e) {
        String a = input1.getText();
        String b = input2.getText();
        String c = input3.getText();
        String op = (String) operation.getSelectedItem();
        String result = "";

        try {
            switch (op) {
                case "split":
                    String[] arr = StringUtils.split(a, b);
                    StringBuilder sb = new StringBuilder();
                    for (String s : arr) sb.append(s).append("\n");
                    result = sb.toString();
                    break;
                case "join":
                    String[] items = StringUtils.split(a, b);
                    result = StringUtils.join(items, c);
                    break;
                case "replace":
                    result = StringUtils.replace(a, b, c);
                    break;
                case "equalsIgnoreCase":
                    result = String.valueOf(StringUtils.equalsIgnoreCase(a, b));
                    break;
                case "indexOf":
                    result = String.valueOf(StringUtils.indexOf(a, b));
                    break;
                case "format":
                    String[] args = c.split(",");
                    result = StringUtils.format(a, (Object[]) args);
                    break;
                case "reverse":
                    result = StringUtils.reverse(a);
                    break;
                default:
                    result = "Неизвестная операция!";
            }
        } catch (Exception ex) {
            result = "Ошибка: " + ex.getMessage();
        }

        output.setText(result);
    }
}
