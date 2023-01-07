package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Cronometro extends JPanel implements ActionListener{
 
 JPanel panel = new JPanel();
 JButton startButton = new JButton("PAUSAR");
 JButton resetButton = new JButton("REINICIAR");
 JLabel timeLabel = new JLabel();
 int elapsedTime = 0;
 int seconds =0;
 int minutes =0;
 boolean started = true;
 String seconds_string = String.format("%02d", seconds);
 String minutes_string = String.format("%02d", minutes);
 Tabuleiro tabuleiro;
 
 Timer timer = new Timer(1000, new ActionListener() {
  
  public void actionPerformed(ActionEvent e) {
   
   elapsedTime=elapsedTime+1000;
   minutes = (elapsedTime/60000) % 60;
   seconds = (elapsedTime/1000) % 60;
   seconds_string = String.format("%02d", seconds);
   minutes_string = String.format("%02d", minutes);
   timeLabel.setText(minutes_string+":"+seconds_string);
   
  }
  
 });
 
 
 public Cronometro(Tabuleiro tabuleiro){  
  timeLabel.setBounds(124,100,219,45);
  timeLabel.setText(minutes_string+":"+seconds_string);
  timeLabel.setFont(new Font("Arial",Font.BOLD,35));
  timeLabel.setBorder(BorderFactory.createBevelBorder(1));
  timeLabel.setOpaque(true);
  timeLabel.setHorizontalAlignment(JTextField.CENTER);
  
  startButton.setBounds(124,156,109,33);
  startButton.setFont(new Font("Arial", Font.PLAIN, 15));
  startButton.setFocusable(false);
  startButton.addActionListener(this);
  
  resetButton.setBounds(234,156,109,33);
  resetButton.setFont(new Font("Arial", Font.PLAIN, 15));
  resetButton.setFocusable(false);
  resetButton.addActionListener(this);
  setLayout(null);
  
  this.tabuleiro = tabuleiro;
  
  add(startButton);
  add(resetButton);
  add(timeLabel);
 }
 
 public void actionPerformed(ActionEvent e) {

	 if(e.getSource()==startButton) {

	 if(started==false) {
	 started=true;
	 this.tabuleiro.setEnableMovement(started);
	 
	 startButton.setText("PAUSAR");
	 start();
	 }
	 else {
	 started=false;
	 this.tabuleiro.setEnableMovement(started);
	 
	 startButton.setText("COMEÇAR");
	 stop();
	 }

	 }
	 if(e.getSource()==resetButton) {
	 started=false;
	 startButton.setText("COMEÇAR");
	 reset();
	 }

	 }

	 void start() {
	 timer.start();
	 }

	 void stop() {
	 timer.stop();
	 }

	 void reset() {
	 timer.stop();
	 elapsedTime=0;
	 seconds =0;
	 minutes=0;
	 seconds_string = String.format("%02d", seconds);
	 minutes_string = String.format("%02d", minutes);
	 timeLabel.setText(minutes_string+":"+seconds_string);
	 
	 this.tabuleiro.shuffleBoard();
	 }

	 }
