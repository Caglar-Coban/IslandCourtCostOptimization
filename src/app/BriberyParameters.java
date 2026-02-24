package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class BriberyParameters extends JFrame {

    private JPanel contentPane;
    private Image backgroundImage;
    private String currentJudge;

    
    public JTextField txtJudgeAcquit;  
    public JTextField txtJudgeAbstain; 
    public JTextField txtJuryCount;    
    public JTextField txtJuryBribe;    

   
    public BriberyParameters(String judgeName) {
        this.currentJudge = judgeName;

        
        setTitle("Bribery Protocol - Target: " + judgeName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        
        
         backgroundImage = new ImageIcon(getClass().getResource("/data/images/JudgeSelection.png")).getImage();
        

       
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                   
                    g.setColor(new Color(0, 0, 0, 220)); 
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(40, 40, 40, 40));
        contentPane.setLayout(null); 
        setContentPane(contentPane);

       
        JLabel lblTitle = new JLabel("BRIBERY PARAMETERS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(0, 30, 1200, 50);
        contentPane.add(lblTitle);

        JLabel lblSubTitle = new JLabel("JUDGE: " + judgeName.toUpperCase(), SwingConstants.CENTER);
        lblSubTitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSubTitle.setForeground(new Color(255, 215, 0)); 
        lblSubTitle.setBounds(0, 80, 1200, 30);
        contentPane.add(lblSubTitle);

        
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/data/images/" + judgeName + ".png"));
        Image img = icon.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
        JLabel lblJudgeImg = new JLabel(new ImageIcon(img));
        lblJudgeImg.setBounds(100, 50, 100, 140);
        lblJudgeImg.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
        contentPane.add(lblJudgeImg);
        

        
        JPanel formPanel = new JPanel();
        formPanel.setBounds(340, 150, 600, 400);
        formPanel.setOpaque(false);
        formPanel.setLayout(new GridLayout(2, 2, 40, 40)); 

        
        JPanel pnl1 = createInputGroup("Judge Demand (Acquittal)", "Example: 50000");
        txtJudgeAcquit = (JTextField) pnl1.getComponent(1);
        formPanel.add(pnl1);

        
        JPanel pnl2 = createInputGroup("Judge Demand (Abstain)", "Example: 25000");
        txtJudgeAbstain = (JTextField) pnl2.getComponent(1);
        formPanel.add(pnl2);

        
        JPanel pnl3 = createInputGroup("Number of Jurors", "Example: 12");
        txtJuryCount = (JTextField) pnl3.getComponent(1);
        formPanel.add(pnl3);

        
        JPanel pnl4 = createInputGroup("Bribe per Juror", "Example: 5000");
        txtJuryBribe = (JTextField) pnl4.getComponent(1);
        formPanel.add(pnl4);

        contentPane.add(formPanel);

        
        JButton btnCalculate = new JButton("INITIATE SIMULATION");
        btnCalculate.setBounds(440, 600, 400, 60);
        styleButton(btnCalculate);
        
        
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int jAcquit = Integer.parseInt(txtJudgeAcquit.getText());
                    int jAbstain = Integer.parseInt(txtJudgeAbstain.getText());
                    int jCount = Integer.parseInt(txtJuryCount.getText());
                    int jBribe = Integer.parseInt(txtJuryBribe.getText());

                    if (jAcquit < 0 || jAbstain < 0 || jCount < 0 || jBribe < 0) {
                        
                        JOptionPane.showMessageDialog(BriberyParameters.this, "Values ​​cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    
                    CalculationScreen calcScreen = new CalculationScreen(currentJudge, jAcquit, jAbstain, jCount, jBribe);
                    calcScreen.setVisible(true);
                    dispose(); 

                } catch (NumberFormatException ex) {
                    
                    JOptionPane.showMessageDialog(BriberyParameters.this, "Please enter valid numerical values ​​in all fields.", "Incorrect Entry", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
       
        contentPane.add(btnCalculate);
    }

    
    private JPanel createInputGroup(String title, String placeholder) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 10)); 
        panel.setOpaque(false);

        JLabel lbl = new JLabel(title);
        lbl.setForeground(Color.LIGHT_GRAY);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(lbl, BorderLayout.NORTH);

        JTextField field = new JTextField();
        field.setOpaque(false); 
        field.setForeground(Color.WHITE);
        field.setFont(new Font("Consolas", Font.PLAIN, 18));
        field.setCaretColor(Color.WHITE);
        field.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY)); 
        
       
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(new MatteBorder(0, 0, 2, 0, new Color(46, 204, 113))); 
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY));
            }
        });

        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    
    private void styleButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setFocusPainted(false);
        btn.setForeground(new Color(46, 204, 113)); 
        btn.setBorder(BorderFactory.createLineBorder(new Color(46, 204, 113), 2));
        
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(new Color(46, 204, 113));
                btn.setForeground(Color.BLACK);
                btn.setOpaque(true);
            }
            public void mouseExited(MouseEvent evt) {
                btn.setOpaque(false);
                btn.setForeground(new Color(46, 204, 113));
            }
        });
    }
}