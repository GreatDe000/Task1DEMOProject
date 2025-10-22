package task.task1demo;

import oop.task.StringUtils;  // импортируем библиотеку из другого пакета
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StringUtilsGUI().setVisible(true);
        });
    }
}
