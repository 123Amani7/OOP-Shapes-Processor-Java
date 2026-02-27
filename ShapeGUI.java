package uml_shape_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;

public class ShapeGUI extends JFrame {

    private JTextArea consoleTextArea;
    private ShapeDrawingPanel drawingPanel;
    private List<Drawable> currentShapes;
    
    private JButton processFileButton;
    private JButton calculateAllButton;

    public ShapeGUI() {
        super("OOP Project: Shapes Processor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
     
        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setFont(new Font("Monospaced", Font.PLAIN, 15)); 
        consoleTextArea.setBackground(new Color(252, 252, 252)); 
        
        drawingPanel = new ShapeDrawingPanel(new Drawable[0]); 
        currentShapes = new ArrayList<>();

     
        processFileButton = createModernButton("1. Read File & Draw", new Color(39, 174, 96));
        calculateAllButton = createModernButton("2. Show Full Results", new Color(41, 128, 185));
        
     
        JPanel buttonsInnerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        buttonsInnerPanel.setBackground(new Color(240, 240, 240));
        buttonsInnerPanel.add(processFileButton);
        buttonsInnerPanel.add(calculateAllButton);

        JPanel buttonArea = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonArea.setBackground(new Color(240, 240, 240));
        buttonArea.add(buttonsInnerPanel);
        
   
        JScrollPane consoleScrollPane = new JScrollPane(consoleTextArea);
        TitledBorder consoleTitle = BorderFactory.createTitledBorder("Console Output & Analytics");
        consoleTitle.setTitleFont(new Font("SansSerif", Font.BOLD, 13));
        consoleScrollPane.setBorder(consoleTitle);
        
    
        JSplitPane bottomSplitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            buttonArea,
            consoleScrollPane
        );
        bottomSplitPane.setResizeWeight(0.10);
        bottomSplitPane.setDividerSize(0); 
        bottomSplitPane.setBorder(null);

        add(drawingPanel, BorderLayout.CENTER);
        add(bottomSplitPane, BorderLayout.SOUTH); 
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
        
     
        processFileButton.addActionListener(e -> {
            playSound();
            consoleTextArea.setText(TestDriver.readInputFile(currentShapes));
            drawingPanel.setShapes(currentShapes.toArray(new Drawable[0]));
        });
        
        calculateAllButton.addActionListener(e -> {
            playSound();
            if (currentShapes.isEmpty()) {
                consoleTextArea.append("\n[Error]: No shapes loaded. Please read file first.");
            } else {
                consoleTextArea.append(TestDriver.calculateAreasAndVolumes(currentShapes));
            }
        });
    }


    private JButton createModernButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 45));
        
  
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(color.brighter()); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(color); }
        });
        return btn;
    }

    private void playSound() {
        try {
            java.io.InputStream audioIn = getClass().getResourceAsStream("click.wav");
            if (audioIn != null) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(audioIn));
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
            }
        } catch (Exception e) { System.err.println("Sound error"); }
    }

    public static void startGUI() {
        SwingUtilities.invokeLater(() -> new ShapeGUI().setVisible(true));
    }
}