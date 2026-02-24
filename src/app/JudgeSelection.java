package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class JudgeSelection extends JFrame {

    private Image backgroundImage;
    private JPanel contentPane;
    
    
    private final String[] judgeNames = {"Chris", "Sam", "Tyler", "Robert", "John"};
    private String selectedJudge = null;
  
    private List<JButton> judgeButtons = new ArrayList<>();
    private JButton btnNext; 

    /**
     * Create the frame.
     */
    public JudgeSelection() {
        
        setTitle("Islander Court - Judge Selection");
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
                    
                    g.setColor(new Color(0, 0, 0, 150)); 
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        
        contentPane.setBorder(new EmptyBorder(40, 40, 40, 40));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane); 
        
        JLabel lblTitle = new JLabel("CHOOSE YOUR JUDGE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(new EmptyBorder(20, 0, 40, 0));
        contentPane.add(lblTitle, BorderLayout.NORTH);

       
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
        cardsPanel.setOpaque(false); 

        for (String name : judgeNames) {
            JButton btnJudge = createJudgeButton(name);
            judgeButtons.add(btnJudge);
            cardsPanel.add(btnJudge);
        }
        contentPane.add(cardsPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        btnNext = new JButton("CONFIRM SELECTION");
        styleNextButton(btnNext);
        btnNext.setEnabled(false); 
        
       
        
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (selectedJudge != null) {
                    
                    
                    BriberyParameters brbpara = new BriberyParameters(selectedJudge);
                    brbpara.setVisible(true);
    				dispose();
                    
                }
            }});

        bottomPanel.add(btnNext);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }

    
    private JButton createJudgeButton(String name) {
        JButton btn = new JButton();
        
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/data/images/" + name + ".png"));
            Image img = icon.getImage().getScaledInstance(180, 240, Image.SCALE_SMOOTH); 
            btn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            btn.setText(name);
        }

        btn.setPreferredSize(new Dimension(180, 280));
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setText(name.toUpperCase());
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.LIGHT_GRAY);
        
        
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	selectJudge(btn, name);
            }});
        
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!name.equals(selectedJudge)) {
                    btn.setBorder(new LineBorder(new Color(255, 255, 255, 100), 2));
                    btn.setBorderPainted(true);
                }
            }
            public void mouseExited(MouseEvent evt) {
                if (!name.equals(selectedJudge)) {
                    btn.setBorderPainted(false);
                }
            }
        });

        return btn;
    }

    
    private void selectJudge(JButton selectedBtn, String name) {
        this.selectedJudge = name;

        for (JButton btn : judgeButtons) {
            btn.setBorderPainted(false);
            btn.setForeground(Color.LIGHT_GRAY);
        }

        selectedBtn.setBorder(new LineBorder(Color.LIGHT_GRAY)); 
        selectedBtn.setBorderPainted(true);
        selectedBtn.setForeground(new Color(255, 215, 0));
        
        btnNext.setEnabled(true);
        btnNext.setForeground(Color.WHITE);
        btnNext.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    
    private void styleNextButton(JButton btn) {
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setFocusPainted(false);
        btn.setForeground(new Color(100, 100, 100));
        btn.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
    }
}