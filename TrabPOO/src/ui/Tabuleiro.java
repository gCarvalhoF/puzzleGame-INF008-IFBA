package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;

public class Tabuleiro extends JPanel {
	private static final int PUZZLE_SIZE = 4;
	private int[][] board;
	private JButton[][] buttons;
	public String image_selected;
	private boolean enableMovement = true;
	private int[][] tabuleiroInicial;

	public Tabuleiro(int image_selected, boolean isSolved) {
		this.board = createBoard(PUZZLE_SIZE, isSolved);
		this.tabuleiroInicial = board;
		this.buttons = new JButton[PUZZLE_SIZE][PUZZLE_SIZE];

		setLayout(new GridLayout(PUZZLE_SIZE, PUZZLE_SIZE));

		for (int row = 0; row < PUZZLE_SIZE; row++) {
			for (int col = 0; col < PUZZLE_SIZE; col++) {
				int value = board[row][col];
				String str_value = Integer.toString(value);
				this.image_selected = Integer.toString(image_selected);
				JButton button = new JButton(String.valueOf(value));

				// Load the icon image from a file
				ImageIcon icon = new ImageIcon("./Images/" + image_selected + "/" + str_value + ".png");
				Image scaled_icon = icon.getImage().getScaledInstance(146, 82, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(scaled_icon);

				button.setMargin(new Insets(0, 0, 0, 0));
				// Set the icon for the button
				button.setIcon(icon);

				button.addActionListener(new ButtonMover(row, col));
				add(button);
				buttons[row][col] = button;
			}
		}
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
		updateButtons();
	}

	private void updateButtons() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				int value = board[row][col];

				ImageIcon icon = new ImageIcon(
						"./Images/" + this.image_selected + "/" + Integer.toString(value) + ".png");
				Image scaled_icon = icon.getImage().getScaledInstance(146, 82, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(scaled_icon);

				buttons[row][col].setText(String.valueOf(value));
				buttons[row][col].setIcon(icon);
			}
		}
	}

	private int[][] createBoard(int size, boolean isSolved) {
		ArrayList<Integer> values = new ArrayList<>();
		for (int i = 1; i < size * size; i++) {
			values.add(i);
		}
		values.add(0);

		if (!isSolved) {
			Collections.shuffle(values);
		}

		int[][] board = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = values.get(i * size + j);
			}
		}
		return board;
	}

	public int[] boardToArray() {
		
		int[] array = Arrays.stream(this.board).flatMapToInt(Arrays::stream).toArray();
		
//		int size = PUZZLE_SIZE * PUZZLE_SIZE;
//		int[] array = new int[size];
//
//		for (int i = 0; i < PUZZLE_SIZE; i++) {
//			for (int j = 0; j < PUZZLE_SIZE; j++) {
//				array[i * PUZZLE_SIZE + j] = this.board[i][j];
//			}
//		}

		return array;
	}

	public void shuffleBoard() {
		// Create a new shuffled board using the createBoard() method
		int[][] shuffledBoard = createBoard(PUZZLE_SIZE, false);

		// Set the board field of the Tabuleiro class to the new shuffled board
		setBoard(shuffledBoard);
	}

	public void solveBoard() {
		// Create a new shuffled board using the createBoard() method
		int[][] solvedBoard = createBoard(PUZZLE_SIZE, true);

		// Set the board field of the Tabuleiro class to the new shuffled board
		setBoard(solvedBoard);
	}

	public void switchImage(int newImage) {
		// Create a new shuffled board using the createBoard() method
		this.image_selected = Integer.toString(newImage);

		// Set the board field of the Tabuleiro class to the new shuffled board
		updateButtons();
	}

	public void setEnableMovement(boolean test) {
		this.enableMovement = test;
	}

	public boolean checkWin() {
		int[][] solvedBoard = createBoard(PUZZLE_SIZE, true);
		for (int row = 0; row < PUZZLE_SIZE; row++) {
			for (int col = 0; col < PUZZLE_SIZE; col++) {
				if (solvedBoard[row][col] != board[row][col]) {
					return false;
				}
			}
		}
		return true;
	}

	private class ButtonMover implements ActionListener {
		private int row;
		private int col;

		public ButtonMover(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// get the button that was clicked
			JButton button = (JButton) e.getSource();

			// try to move the button to the left, right, up, or down

			if (enableMovement) {
				if (checkWin()) {
					JOptionPane.showMessageDialog(null, "Parabéns! Você venceu o jogo!");
					return;
				}

				tryMove(row, col - 1, button);
				tryMove(row, col + 1, button);
				tryMove(row - 1, col, button);
				tryMove(row + 1, col, button);
			} else {
				JOptionPane.showMessageDialog(null, "O jogo está pausado, retome a partida para poder mover as peças!");
			}
		}

		// try to move the button to the specified row and column
		private void tryMove(int tryRow, int tryColumn, JButton button) {
			// check if the row and column are within the bounds of the board
			if (tryRow >= 0 && tryRow < PUZZLE_SIZE && tryColumn >= 0 && tryColumn < PUZZLE_SIZE) {
				// get the button at the specified row and column
				JButton otherButton = buttons[tryRow][tryColumn];

				// check if the button is blank
				if (otherButton.getText().equals("0")) {
					// swap the buttons
					swapButtons(button, otherButton);

					// update the board
					board[this.row][this.col] = 0;
					board[tryRow][tryColumn] = Integer.parseInt(otherButton.getText());
				}
			}
		}

		// swap the text of the two buttons
		private void swapButtons(JButton button1, JButton button2) {
			String text = button1.getText();
			ImageIcon icon = (ImageIcon) button1.getIcon();

			button1.setText(button2.getText());
			button1.setIcon(button2.getIcon());
			button2.setText(text);
			button2.setIcon(icon);
		}
		
	}

	public int[][] getTabuleiroInicial() {
		return tabuleiroInicial;
	}


}
