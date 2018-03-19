package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import model.Tetraminos;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class InformationPanel extends JPanel {
	
	public Point[] nextTetro;
	int tetroColor;
	
	JLabel label_score ;
	JLabel label ;
	public InformationPanel() {
		setForeground(Color.WHITE);
		setBorder(null);
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel home = new JLabel("New label");
		home.setIcon(new ImageIcon(InformationPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/homeFolder.gif")));
		GridBagConstraints gbc_home = new GridBagConstraints();
		gbc_home.anchor = GridBagConstraints.WEST;
		gbc_home.insets = new Insets(0, 0, 5, 0);
		gbc_home.gridx = 1;
		gbc_home.gridy = 1;
		add(home, gbc_home);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(InformationPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("NEXT");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.BLACK);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 13;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblYourScore = new JLabel("YOUR SCORE");
		lblYourScore.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblYourScore = new GridBagConstraints();
		gbc_lblYourScore.insets = new Insets(0, 0, 5, 0);
		gbc_lblYourScore.gridx = 1;
		gbc_lblYourScore.gridy = 4;
		add(lblYourScore, gbc_lblYourScore);
		
		label_score = new JLabel("0.0");
		label_score.setForeground(Color.WHITE);
		GridBagConstraints gbc_label_score = new GridBagConstraints();
		gbc_label_score.insets = new Insets(0, 0, 5, 0);
		gbc_label_score.gridx = 1;
		gbc_label_score.gridy = 5;
		add(label_score, gbc_label_score);
		
		JLabel lblHighScore = new JLabel("HIGH SCORE");
		lblHighScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblHighScore.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblHighScore = new GridBagConstraints();
		gbc_lblHighScore.insets = new Insets(0, 0, 5, 0);
		gbc_lblHighScore.gridx = 1;
		gbc_lblHighScore.gridy = 8;
		add(lblHighScore, gbc_lblHighScore);
		
		label = new JLabel("0.0");
		label.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 1;
		gbc_label.gridy = 9;
		add(label, gbc_label);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		//this.setLayout(null);
	
		for(int i = 0; i < nextTetro.length; i++){
				g.setColor(choseColor(tetroColor));
				g.fillRect(nextTetro[i].x*20+ 30,nextTetro[i].y*20 + 200, 20, 20);
					
	}
	
	}


	public void createIcons(){
		
	}

	public void updateNextTetro(Tetraminos tetraminos) {
		nextTetro = tetraminos.getTetro();
		tetroColor = tetraminos.getColor();
		this.repaint();
		
	}
	public void updateScore(int score){
		label_score.setText(Integer.toString(score));
		this.repaint();
	}
	public void updateHighScore(int score){
		label.setText(Integer.toString(score));
		this.repaint();
	}
	
	Color choseColor(int nr){
		Color color= null;
		switch(nr){
		case 1: color = Color.MAGENTA;
			break;
		case 2: color = Color.RED;
			break;
		case 3: color =  Color.YELLOW;
			break;
		case 4: color =  Color.BLUE;
			break;
		case 5: color =  Color.GREEN;
			break;
		default: ;
		}
		return color;
	}

	// obs³uga pause new game
	

}
