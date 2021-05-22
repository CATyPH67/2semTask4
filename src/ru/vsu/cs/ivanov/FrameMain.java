package ru.vsu.cs.ivanov;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FrameMain extends JFrame{
    private JPanel panelMain;
    private JTextField inputTextField;
    private JButton sortButton;
    private JButton loadFromFileButton;
    private JTextField outputTextField;
    private JPanel buttonsPanel;

    public FrameMain() {
        super("Application");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = inputTextField.getText();
                if (inputString.matches("[\\d\\s]+")) {
                    Integer[] arr = ArrayUtils.strArrToInt(inputString);
                    ArrayUtils.sort(arr);
                    outputTextField.setText(Arrays.toString(arr));
                } else if (inputString.matches("[A-Za-zА-Яа-я\\s]+")) {
                    String[] arr = inputString.split("\\s+");
                    ArrayUtils.sort(arr);
                    outputTextField.setText(Arrays.toString(arr));
                } else {
                    JOptionPane.showMessageDialog(panelMain,"Enter the integer numbers or words separated by a space!");
                }
            }
        });

        loadFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("./tests"));
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getPath();
                    Scanner scanner;
                    try {
                        scanner = new Scanner(new File(name));
                    } catch (FileNotFoundException fileNotFoundException) {
                        inputTextField.setText("File not found");
                        return;
                    }
                    inputTextField.setText(scanner.nextLine());
                }
            }
        });
    }
}
