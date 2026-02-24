package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CalculationScreen extends JFrame {

    private JPanel contentPane;
    private Image backgroundImage;
    private int costScenario1; 
    private int costScenario2; 
    private int costScenario3; 
    private String judgeName;
    private int judgeAcquitCost;
    private int judgeAbstainCost;
    private int juryCount;
    private int juryBribe;

   
    public CalculationScreen(String judgeName, int judgeAcquit, int judgeAbstain, int juryCount, int juryBribe) {
        this.judgeName = judgeName;
        this.judgeAcquitCost = judgeAcquit;
        this.judgeAbstainCost = judgeAbstain;
        this.juryCount = juryCount;
        this.juryBribe = juryBribe;

        
        calculateCosts();

       
        setTitle("Islander Court - Strategic Analysis");
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
                    g.setColor(new Color(0, 0, 0, 230)); 
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(0, 20));
        setContentPane(contentPane);

        
        JLabel lblTitle = new JLabel("OPTIMAL STRATEGY ANALYSIS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setForeground(Color.WHITE);
        contentPane.add(lblTitle, BorderLayout.NORTH);

       
        JPanel resultsPanel = new JPanel();
        resultsPanel.setOpaque(false);
        resultsPanel.setLayout(new GridLayout(1, 3, 30, 0)); 
        resultsPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        
        int minCost = Math.min(costScenario1, Math.min(costScenario2, costScenario3));

        
        resultsPanel.add(createResultCard("SCENARIO 1","Judge Acquits", "You pay the judge for immediate freedom.",costScenario1,minCost == costScenario1));

        
        resultsPanel.add(createResultCard("SCENARIO 2","Judge Abstains","Judge takes less, but you need " + ((juryCount / 2) + 1) + " jurors.",costScenario2,minCost == costScenario2));

        
        resultsPanel.add(createResultCard("SCENARIO 3","Total Jury Buyout","Judge finds you guilty. You bribe ALL " + juryCount + " jurors.",costScenario3,minCost == costScenario3));

        contentPane.add(resultsPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        
        JButton btnRestart = new JButton("START NEW SIMULATION");
        btnRestart.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnRestart.setForeground(Color.WHITE);
        btnRestart.setBackground(new Color(50, 50, 50));
        btnRestart.setFocusPainted(false);
        btnRestart.setPreferredSize(new Dimension(300, 50));
        
        
        btnRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Main mainScreen = new Main();
                mainScreen.setVisible(true);
                dispose();
            }});
        
      
        
        bottomPanel.add(btnRestart);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    
    private void calculateCosts() {
        
        costScenario1 = judgeAcquitCost;

        
        int majorityNeeded = (juryCount / 2) + 1;
        costScenario2 = judgeAbstainCost + (majorityNeeded * juryBribe);

        
        costScenario3 = juryCount * juryBribe; 
    }

   
    private JPanel createResultCard(String scenarioTitle, String subTitle, String desc, int cost, boolean isBest) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setOpaque(false); 
        
       
        Color borderColor;
        Color textColor;

        if (isBest == true) {
            borderColor = new Color(46, 204, 113);
            textColor = new Color(46, 204, 113);
        } else {
            borderColor = new Color(100, 100, 100);
            textColor = Color.LIGHT_GRAY;
        }

        
        JPanel innerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                g.setColor(new Color(30, 30, 30, 200));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                
                
                if (isBest) {
                    g.setColor(new Color(46, 204, 113, 30));
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                }
            }
        };
        innerPanel.setLayout(new GridLayout(5, 1));
        if (isBest == true) {
            innerPanel.setBorder(new LineBorder(borderColor, 4));
        } 
        else {
            innerPanel.setBorder(new LineBorder(borderColor, 1));
        }

       
        JLabel lblScen = new JLabel(scenarioTitle, SwingConstants.CENTER);
        lblScen.setForeground(Color.GRAY);
        lblScen.setFont(new Font("Segoe UI", Font.BOLD, 14));
        innerPanel.add(lblScen);

        
        JLabel lblHead = new JLabel(subTitle, SwingConstants.CENTER);
        lblHead.setForeground(Color.WHITE);
        lblHead.setFont(new Font("Segoe UI", Font.BOLD, 22));
        innerPanel.add(lblHead);

        
        JLabel lblCost = new JLabel("$" + cost, SwingConstants.CENTER);
        lblCost.setForeground(textColor);
        lblCost.setFont(new Font("Consolas", Font.BOLD, 36));
        innerPanel.add(lblCost);

        
        JLabel lblDesc = new JLabel("<html><div style='text-align: center; width: 200px;'>" + desc + "</div></html>", SwingConstants.CENTER);
        lblDesc.setForeground(Color.LIGHT_GRAY);
        innerPanel.add(lblDesc);

        
        JLabel lblBadge = new JLabel();

     
     if (isBest == true) {
         lblBadge.setText("★ RECOMMENDED ★");
     } 
     else {
         lblBadge.setText("");
     }

     
     lblBadge.setHorizontalAlignment(SwingConstants.CENTER);
        lblBadge.setForeground(textColor);
        lblBadge.setFont(new Font("Segoe UI", Font.BOLD, 16));
        innerPanel.add(lblBadge);

        card.add(innerPanel);
        return card;
    }
}