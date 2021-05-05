/*
 * Author: Kyra Samuel
 * Date: 04/28/2021
 * Purpose: Displays a GUI to the user that allows him or her to input miles or fahrenheit to convert.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class GUIConverter {
    JFrame frame;

    public GUIConverter() { 
        frame = new JFrame("Welcome to Converter");
        frame.setLayout(new GridLayout(2, 1));

        JPanel btnPanel = new JPanel(new GridLayout(1, 1));
        JButton distanceBtn, tempBtn, exitBtn;

        distanceBtn = new JButton("Distance Temperature");
        distanceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double input;

                try {
                    input = getDistanceInput(true);
                } catch (NullPointerException npe) {
                    input = Double.NaN;
                }

                DistanceConverter distanceConverter;

                if (!Double.isNaN(input))
                    distanceConverter = new DistanceConverter(input);
                else
                    distanceConverter = new DistanceConverter();

                double result = distanceConverter.convert();
                String convertResult = "";

                if (!Double.isNaN(result))
                    convertResult = String.valueOf(input) + " M equals "
                            + String.format("%,.3f", distanceConverter.convert()) + " KM.";
                else
                    convertResult = "No input received";

                JOptionPane.showMessageDialog(frame, convertResult);
            }
        });

        btnPanel.add(distanceBtn);

        tempBtn = new JButton("Temperature Converter");
        tempBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double input;

                try {
                    input = getDistanceInput(false);
                } catch (NullPointerException npe) {
                    input = Double.NaN;
                }

                TemperatureConverter temperatureConverter;

                if (!Double.isNaN(input))
                    temperatureConverter = new TemperatureConverter(input);
                else
                    temperatureConverter = new TemperatureConverter();

                double result = temperatureConverter.convert();

                String convertResult = "";

                if (!Double.isNaN(result))
                    convertResult = String.valueOf(input) + " F equals "
                            + String.format("%,.3f", result) + " C.";
                else 
                    convertResult = "No input received";
        
                JOptionPane.showMessageDialog(frame, convertResult);
            }
        });

        btnPanel.add(tempBtn);

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(btnPanel);
        frame.add(exitBtn);

        frame.setSize(new Dimension(400, 300));
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public double getDistanceInput(Boolean forDistance) throws NullPointerException {
        String result = "";
    
        if (forDistance)
            result = (String) JOptionPane.showInputDialog(frame, "Input miles distance to convert (int/double values only)", "Distance Input",
                    JOptionPane.PLAIN_MESSAGE);
        else
            result = (String) JOptionPane.showInputDialog(frame, "Input fahrenheit temperature to convert (int/double values only)", "fahrenheit Input",
                    JOptionPane.PLAIN_MESSAGE);
        
        if (result.trim() != null && result.trim().length() > 0) {
            try {
                double input = Double.parseDouble(result);
                return input;
            } catch(Exception e) {
                return getDistanceInput(forDistance);
            }
        } else 
            return Double.NaN;
    }
}
