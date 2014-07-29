package tetris;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Menu extends JPanel{
    ImageIcon image;
    JLabel picture;
    JLabel welcome;



        
	public Menu(final Tetris p) {
        // Add the background pictures
        this.image = new ImageIcon(this.getClass().getResource("hs1.png"));
        this.picture = new JLabel(new ImageIcon(this.image.getImage()));
        add(this.picture);
        
        // welcome
        welcome = new JLabel("Welcome to Tetris Awesome!");
        welcome.setFont (welcome.getFont ().deriveFont (20.0f));
        add(welcome, BorderLayout.CENTER);
		
        // buttons
        JButton button1 = new JButton();
        button1.setText("Play the Game");
        add(button1);
        
        JButton button2 = new JButton();
        button2.setText("See the Computer Playing");
        add(button2);
        
        
        button1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            p.setState(Tetris.STATE.GAME);
            } 
        });
	}
	
	

}
