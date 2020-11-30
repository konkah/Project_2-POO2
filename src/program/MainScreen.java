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
    private JPanel form;
    private JTextField textResultValue;
    private JComboBox<AbstractConverter> comboFromUnit;
    private JComboBox<AbstractConverter> comboToUnit;
    private JTextField textOriginalValue;
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

            comboFromUnit.addItem(converter);

            MeasureType type = converter.type();
            if (!converters.containsKey(type)) {
                converters.put(type, new ArrayList<>());
            }
            converters.get(type).add(converter);
            convertersCount++;
        }

        fillComboToUnit();

        showConverterCount();

        comboFromUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String command = actionEvent.getActionCommand();
                if (command.equals("comboBoxChanged")) {
                    updateResult();
                    fillComboToUnit();
                }
            }
        });

        comboToUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String command = actionEvent.getActionCommand();
                if (command.equals("comboBoxChanged")) {
                    updateResult();
                }
            }
        });
        textOriginalValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                updateResult();
            }
        });
    }

    private void fillComboToUnit() {
        comboToUnit.removeAllItems();

        AbstractConverter fromUnit = fromUnit();
        if (fromUnit == null)
            return;

        MeasureType type = fromUnit.type();

        for (AbstractConverter toUnit : converters.get(type)) {
            if (fromUnit != toUnit) {
                comboToUnit.addItem(toUnit);
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
        footer.setForeground(textOriginalValue.getForeground());
    }

    private void updateResult() {
        String typedValue = textOriginalValue.getText();

        float originalValue;
        try
        {
            originalValue = Float.parseFloat(typedValue);
        }
        catch(NumberFormatException e) {
            if (!typedValue.isEmpty()) {
                printError("'" + typedValue + "' is not a number.");
            }
            return;
        }

        AbstractConverter originalUnit = fromUnit();
        AbstractConverter resultUnit = toUnit();

        float basicUnit = originalUnit.toBasicUnit(originalValue);
        float resultValue = resultUnit.fromBasicUnit(basicUnit);

        textResultValue.setText(Float.toString(resultValue));

        showConverterCount();
    }

    private AbstractConverter fromUnit() {
        return (AbstractConverter) comboFromUnit.getSelectedItem();
    }

    private AbstractConverter toUnit() {
        return (AbstractConverter) comboToUnit.getSelectedItem();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainScreen");
        frame.setContentPane(new MainScreen().form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
