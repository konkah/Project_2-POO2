package program;

import converters.AbstractConverter;
import converters.MeasureType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainScreen extends JFrame{
    private JPanel panel1;
    private JTextField textField2;
    private JComboBox<AbstractConverter> comboBox1;
    private JComboBox<AbstractConverter> comboBox2;
    private JTextField textField1;
    private JLabel footer;

    private Map<MeasureType, List<AbstractConverter>> converters = new TreeMap<>();
    private Integer convertersCount = 0;

    public MainScreen(){
        File convertersDir = new File("src/converters");
        String[] converterFiles = convertersDir.list();

        if (converterFiles == null) {
            printError("converters folder not found.");
            return;
        }

        List<String> sortedConverterFiles = Arrays.stream(converterFiles)
                .sorted().collect(Collectors.toList());

        for(String converterFile: sortedConverterFiles){
            String name = converterFile.replace(".java", "");
            AbstractConverter converter = newConverter(name);

            if (converter == null) {
                continue;
            }

            comboBox1.addItem(converter);

            MeasureType type = converter.type();
            if (!converters.containsKey(type)) {
                converters.put(type, new ArrayList<>());
            }
            converters.get(type).add(converter);
            convertersCount++;
        }

        fillComboToUnit();

        showConverterCount();

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String command = actionEvent.getActionCommand();
                if (command.equals("comboBoxChanged")) {
                    updateResult();
                    fillComboToUnit();
                }
            }
        });

        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String command = actionEvent.getActionCommand();
                if (command.equals("comboBoxChanged")) {
                    updateResult();
                }
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                updateResult();
            }
        });
    }

    private void fillComboToUnit() {
        comboBox2.removeAllItems();

        AbstractConverter fromUnit = fromUnit();
        if (fromUnit == null)
            return;

        MeasureType type = fromUnit.type();

        for (AbstractConverter toUnit : converters.get(type)) {
            if (fromUnit != toUnit) {
                comboBox2.addItem(toUnit);
            }
        }
    }

    private AbstractConverter newConverter(String name) {
        try {
            boolean isConverter = name.endsWith("Converter");
            boolean isMother = name.startsWith("Abstract");

            if (!isConverter || isMother)
                return null;

            Class<?> _class = Class.forName("converters." + name);
            return (AbstractConverter) _class.newInstance();
        } catch (InstantiationException e) {
            printError("Class " + name + " cannot be initialized.");
            return null;
        } catch (IllegalAccessException e) {
            printError("It was not possible to access class " + name + ".");
            return null;
        } catch (ClassNotFoundException e) {
            printError("Class " + name + " was not found at package converters.");
            return null;
        }
    }

    private void printError(String message) {
        footer.setText("Error: " + message);
        footer.setForeground(new Color( 153, 0, 0));
    }

    private void showConverterCount() {
        footer.setText(convertersCount + " unit converter(s) available");
        footer.setForeground(textField1.getForeground());
    }

    private void updateResult() {
        String typedValue = textField1.getText();

        float value;
        try
        {
            value = Float.parseFloat(typedValue);
        }
        catch(NumberFormatException e) {
            if (!typedValue.isEmpty()) {
                printError("'" + typedValue + "' is not a number.");
            }
            return;
        }

        AbstractConverter originalUnit = fromUnit();
        AbstractConverter destinyUnit = toUnit();

        float basicUnit = originalUnit.toBasicUnit(value);
        float newValue = destinyUnit.fromBasicUnit(basicUnit);

        textField2.setText(Float.toString(newValue));

        showConverterCount();
    }

    private AbstractConverter fromUnit() {
        return (AbstractConverter) comboBox1.getSelectedItem();
    }

    private AbstractConverter toUnit() {
        return (AbstractConverter) comboBox2.getSelectedItem();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainScreen");
        frame.setContentPane(new MainScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
