package app;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Image backgroundImage;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Main frame = new Main();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	/**
	 * Create the frame.
	 */
	public Main() {
		
		backgroundImage = new ImageIcon(getClass().getResource("/data/images/LandingScreen.jpg")).getImage();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		
		
		 contentPane = new JPanel() {
	            protected void paintComponent(Graphics g) {
	            	super.paintComponent(g);
	                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	                g.setColor(new Color(0, 0, 0, 120));
	                g.fillRect(0, 0, getWidth(), getHeight());
	            }
	        };
		
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        contentPane.setLayout(null);
	        setContentPane(contentPane);

	        JLabel lblTitle = new JLabel("Islander Court Simulation");
	        lblTitle.setForeground(Color.WHITE);
	        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 64));
	        lblTitle.setBounds(246, 116, 773, 120);
	        contentPane.add(lblTitle);

	        JLabel lblSubtitle = new JLabel("Calculate the way to gain your freedom at the lowest cost.");
	        lblSubtitle.setForeground(Color.WHITE);
	        lblSubtitle.setFont(new Font("Times New Roman", Font.BOLD, 32));
	        lblSubtitle.setBounds(236, 273, 793, 64);
	        contentPane.add(lblSubtitle);

	     
	        JButton btnStart = new JButton("Start Simulation") {
	            @Override
	            protected void paintComponent(Graphics g) {
	                if (getModel().isRollover()) {
	                    g.setColor(new Color(255, 255, 255, 40)); 
	                    g.fillRect(0, 0, getWidth(), getHeight());
	                }
	                super.paintComponent(g);
	            }
	        };
	        
	        btnStart.setFont(new Font("Serif", Font.BOLD, 26));
	        btnStart.setBounds(440, 380, 400, 90);

	        ImageIcon gavelIcon = new ImageIcon(getClass().getResource("/data/images/gavel_icon.png"));
            Image scaledStart = gavelIcon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
            btnStart.setIcon(new ImageIcon(scaledStart));
	        
	        btnStart.setHorizontalTextPosition(JButton.RIGHT);
	        btnStart.setIconTextGap(12);

	        styleButton(btnStart, 3);
	        
	        btnStart.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	JudgeSelection judgeslc = new JudgeSelection();
	            	judgeslc.setVisible(true);
					dispose();
					
	            }});
	        contentPane.add(btnStart);

	      
	        JButton btnAbout = new JButton("Story") {
	            @Override
	            protected void paintComponent(Graphics g) {
	                if (getModel().isRollover()) {
	                    g.setColor(new Color(255, 255, 255, 40));
	                    g.fillRect(0, 0, getWidth(), getHeight());
	                }
	                super.paintComponent(g);
	            }
	        };
	        
	        btnAbout.setFont(new Font("Serif", Font.BOLD, 18));
	        btnAbout.setBounds(540, 500, 200, 60);

	        ImageIcon aboutIcon = new ImageIcon(getClass().getResource("/data/images/about_icon.png"));
            Image scaledAbout = aboutIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnAbout.setIcon(new ImageIcon(scaledAbout));
	        
	        btnAbout.setHorizontalTextPosition(JButton.RIGHT);
	        btnAbout.setIconTextGap(8);

	        styleButton(btnAbout, 2);
	        
	        
	        btnAbout.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String storyText = "<html><body style='width: 500px;'>" + 
	                        "You are going on vacation to an island. While wandering through the forest, " +
	                        "a man lies on the ground with a knife in his chest. Just as you pull out the knife, " +
	                        "the island police arrest you.<br><br>" + 
	                        "The justice system works a little differently on the island:<br><br>" +
	                        "There are a total of 5 different judges on the island. Who will hear the case " +
	                        "is determined at the last minute on the court day.<br>" +
	                        "Additionally, a certain number of jurors serve in each case. The jury number " +
	                        "is also announced at the last minute." +
	                        "</body></html>";

	              
	                JOptionPane.showMessageDialog(contentPane, storyText, "Background Story", JOptionPane.INFORMATION_MESSAGE);
	            }
	        });
	        
	        contentPane.add(btnAbout);

	     
	        JButton btnExit = new JButton("Exit") {
	            
	            protected void paintComponent(Graphics g) {
	                if (getModel().isRollover()) {
	                    g.setColor(new Color(255, 255, 255, 40));
	                    g.fillRect(0, 0, getWidth(), getHeight());
	                }
	                super.paintComponent(g);
	            }
	        };
	        
	        btnExit.setFont(new Font("Serif", Font.BOLD, 16));
	        btnExit.setBounds(590, 580, 100, 50);

	      
	        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/data/images/exit_icon.png"));
            Image scaledExit = exitIcon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            btnExit.setIcon(new ImageIcon(scaledExit));
	        btnExit.setHorizontalTextPosition(JButton.RIGHT);
	        btnExit.setIconTextGap(6);

	        styleButton(btnExit, 2);

	       
	        btnExit.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.exit(0);
	            }});
	        contentPane.add(btnExit);
	    }
	    
	    private void styleButton(JButton button, int borderSize) {
	        button.setContentAreaFilled(false); 
	        button.setOpaque(false);            
	        button.setFocusPainted(false);      
	        button.setForeground(Color.BLACK);  
	        
	        button.setBorder(
	                BorderFactory.createLineBorder(Color.BLACK, borderSize)
	        );
	    }
}
