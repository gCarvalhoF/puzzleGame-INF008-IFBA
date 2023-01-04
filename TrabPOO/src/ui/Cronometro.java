package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Cronometro extends JPanel implements ActionListener{
 
 JFrame frame = new JFrame();
 JButton startButton = new JButton("PAUSAR");
 JButton resetButton = new JButton("REINICIAR");
 JLabel timeLabel = new JLabel();
 int elapsedTime = 0;
 int seconds =0;
 int minutes =0;
 boolean started = true;
 String seconds_string = String.format("%02d", seconds);
 String minutes_string = String.format("%02d", minutes);
 
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
 
 
 Cronometro(){
  start();
  
  timeLabel.setText(minutes_string+":"+seconds_string);
  timeLabel.setBounds(10,11,165,100);
  timeLabel.setFont(new Font("Arial",Font.BOLD,35));
  timeLabel.setBorder(BorderFactory.createBevelBorder(1));
  timeLabel.setOpaque(true);
  timeLabel.setHorizontalAlignment(JTextField.CENTER);
  
  startButton.setBounds(10,111,165,50);
  startButton.setFont(new Font("Arial",Font.PLAIN,20));
  startButton.setFocusable(false);
  startButton.addActionListener(this);
  
  resetButton.setBounds(10,161,165,50);
  resetButton.setFont(new Font("Arial",Font.PLAIN,20));
  resetButton.setFocusable(false);
  resetButton.addActionListener(this);
  
  frame.getContentPane().add(startButton);
  frame.getContentPane().add(resetButton);
  frame.getContentPane().add(timeLabel);
  
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.setSize(203,250);
  frame.getContentPane().setLayout(null);
  frame.setVisible(true);
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {
  
  if(e.getSource()==startButton) {
   
   if(started==false) {
    started=true;
    startButton.setText("PAUSAR");
    start();
   }
   else {
    started=false;
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
 }

}