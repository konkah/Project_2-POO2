package program;

import converters.LitreConverter;
import converters.MilliLitreConverter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class MainScreen extends JFrame{
    private JPanel panel1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;

    public MainScreen(){
        comboBox1.addItem("Litro");
        comboBox2.addItem("Mililitro");
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String command = actionEvent.getActionCommand();
                if (command.equals("comboBoxChanged")) {
                    String originalValue = textField1.getText();
                    float value = Float.parseFloat(originalValue);
                    LitreConverter litreConverter = new LitreConverter();
                    MilliLitreConverter milliLitreConverter = new MilliLitreConverter();
                    float basicUnit = litreConverter.toBasicUnit(value);
                    float newValue = milliLitreConverter.fromBasicUnit(basicUnit);
                    textField2.setText(Float.toString(newValue));
                }
            }
        });
        comboBox2.addKeyListener(new KeyAdapter() {
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainScreen");
        frame.setContentPane(new MainScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    /*
    public Form() {
        comboBox1.addItem("X");
        comboBox1.addItem("Y");

        comboBox1.addActionListener(actionEvent -> {
            String command = actionEvent.getActionCommand();
            if (command.equals("comboBoxChanged")) {
                Object item = comboBox1.getSelectedItem();
                System.out.println(item);
            }
        });
    }
    */
}
