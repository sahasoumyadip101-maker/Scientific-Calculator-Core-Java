package com.soumyadip.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame
        implements ActionListener {

    private JTextField display;

    private CalculatorEngine engine;


    public CalculatorFrame() {

        engine =
                new CalculatorEngine();

        setTitle(
                "Scientific Calculator"
        );

        setSize(
                450,
                550
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createUI();
    }


    private void createUI() {

        /*
         * Main layout.
         */

        setLayout(
                new BorderLayout(10, 10)
        );


        /*
         * Display.
         */

        display =
                new JTextField();

        display.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        28
                )
        );

        display.setHorizontalAlignment(
                JTextField.RIGHT
        );

        display.setEditable(false);

        add(
                display,
                BorderLayout.NORTH
        );


        /*
         * Button panel.
         */

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setLayout(
                new GridLayout(
                        6,
                        5,
                        5,
                        5
                )
        );


        String[] buttons = {

                "C",
                "(",
                ")",
                "⌫",
                "/",

                "7",
                "8",
                "9",
                "%",
                "*",

                "4",
                "5",
                "6",
                "^",
                "-",

                "1",
                "2",
                "3",
                ".",
                "+",

                "0",
                "00",
                "±",
                "=",
                " "

        };


        /*
         * Create each button.
         */

        for (int i = 0;
             i < buttons.length;
             i++) {

            JButton button =
                    new JButton(
                            buttons[i]
                    );

            button.setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            18
                    )
            );

            button.addActionListener(
                    this
            );

            buttonPanel.add(
                    button
            );
        }


        add(
                buttonPanel,
                BorderLayout.CENTER
        );
    }


    /*
     * ==========================================
     * ACTION LISTENER
     * ==========================================
     */

    @Override
    public void actionPerformed(
            ActionEvent event) {

        String command =
                event.getActionCommand();


        try {

            /*
             * Clear.
             */

            if (command.equals("C")) {

                display.setText("");
            }


            /*
             * Backspace.
             */

            else if (command.equals("⌫")) {

                String text =
                        display.getText();

                if (!text.isEmpty()) {

                    display.setText(
                            text.substring(
                                    0,
                                    text.length() - 1
                            )
                    );
                }
            }


            /*
             * Equals.
             */

            else if (command.equals("=")) {

                calculate();
            }


            /*
             * Plus / minus.
             */

            else if (command.equals("±")) {

                String text =
                        display.getText();

                if (!text.isEmpty()) {

                    display.setText(
                            "-(" + text + ")"
                    );
                }
            }


            /*
             * Ignore blank button.
             */

            else if (command.equals(" ")) {

                return;
            }


            /*
             * Every other button
             * simply gets added to display.
             */

            else {

                display.setText(
                        display.getText()
                                + command
                );
            }

        } catch (Exception e) {

            display.setText(
                    "Error: "
                            + e.getMessage()
            );
        }
    }


    /*
     * Calculate current expression.
     */

    private void calculate() {

        String expression =
                display.getText();

        if (expression.isEmpty()) {

            return;
        }

        double result =
                engine.calculate(
                        expression
                );

        display.setText(
                formatNumber(result)
        );
    }


    /*
     * Format result.
     */

    private String formatNumber(
            double value) {

        if (value ==
                Math.rint(value)) {

            return String.format(
                    "%.0f",
                    value
            );
        }

        return String.format(
                "%.10f",
                value
        )
        .replaceAll(
                "0+$",
                ""
        )
        .replaceAll(
                "\\.$",
                ""
        );
    }
}