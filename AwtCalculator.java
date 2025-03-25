/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.awtcalculator;

/**
 *
 * @author Almera Perit
 */

import java.awt.*;
import java.awt.event.*;

public class AwtCalculator extends Frame implements ActionListener {
    private TextField d;
    private String operator;
    private double n1, n2, r;
    

    public AwtCalculator() {
        
        
        setSize(300, 400);
        setLayout(new BorderLayout(10, 25));
        setResizable(false);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
       

        d = new TextField();
        d.setFont(new Font("Arial", Font.PLAIN, 50));
        d.setEditable(false);
        d.setBackground(Color.BLACK);
        add(d, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4, 6, 6));
        
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setBackground(Color.PINK);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if ("0123456789".contains(command)) {
                d.setText(d.getText() + command);
            } else if ("+-*/".contains(command)) {
                n1= Double.parseDouble(d.getText());
                operator = command;
                d.setText("");
            } else if ("=".equals(command)) {
                n2 = Double.parseDouble(d.getText());
                switch (operator) {
                    case "+":
                        r = n1 + n2;
                        break;
                    case "-":
                        r = n1 - n2;
                        break;
                    case "*":
                        r = n1 * n2;
                        break;
                    case "/":
                        if (n2 == 0) {
                            d.setText("Undefined");
                            return;
                        }
                        r = n1 / n2;
                        break;
                }
                d.setText(String.valueOf(r));
            } else if ("C".equals(command)) {
                d.setText("");
                n1 = n2 = r = 0;
                operator = "";
            }
        } catch (NumberFormatException ex) {
            d.setText("Syntax Error");
        }
    }

    public static void main(String[] args) {
        new AwtCalculator();
    }
}