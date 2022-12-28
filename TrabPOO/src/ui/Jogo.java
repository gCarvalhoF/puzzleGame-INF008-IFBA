package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Bd.conexaoBD;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Jogo {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jogo window = new Jogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 559, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("MENU", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Jogar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cria uma instância da classe conexaoBD
				conexaoBD conexao = new conexaoBD();
				
				// Obtém os valores dos campos de texto
				String nomeJogador1 = textField.getText();
				String nomeJogador2 = textField_1.getText();
				String nomeJogador3 = textField_2.getText();
				
				tabbedPane.setSelectedIndex(1);
				
				// Chama o método inserirJogadores da classe conexaoBD, passando os valores dos campos de texto como argumentos
				conexao.criarTabela();
				conexao.inserirJogadores(nomeJogador1, nomeJogador2, nomeJogador3);
				conexao.fecharConexao();
			}
		});


		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(163, 223, 206, 51);
		panel.add(btnNewButton);
		
		JRadioButton rdbtnEmbaralharImpar = new JRadioButton("EMBARALHAR IMPAR");
		rdbtnEmbaralharImpar.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEmbaralharImpar.setBounds(212, 169, 164, 23);
		panel.add(rdbtnEmbaralharImpar);
		
		JLabel lblNewLabel = new JLabel("Opcional:");
		lblNewLabel.setBounds(147, 173, 66, 14);
		panel.add(lblNewLabel);
		
		JLabel lblJogador = new JLabel("Jogador 3:");
		lblJogador.setBounds(147, 132, 80, 14);
		panel.add(lblJogador);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(226, 132, 143, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(226, 107, 143, 20);
		panel.add(textField_1);
		
		JLabel lbJogador2 = new JLabel("Jogador 2:");
		lbJogador2.setBounds(147, 107, 80, 14);
		panel.add(lbJogador2);
		
		JLabel lbJogador1 = new JLabel("Jogador 1:");
		lbJogador1.setBounds(147, 82, 80, 14);
		panel.add(lbJogador1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(226, 82, 143, 20);
		panel.add(textField_2);
		
		JLabel lbTitulo = new JLabel("MENU INICIAL");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbTitulo.setBounds(0, 11, 543, 60);
		panel.add(lbTitulo);
		
		JButton btnLimparHistorico = new JButton("LIMPAR HIST\u00D3RICO");
		btnLimparHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();
				
				conexao.limparHistorico();
				conexao.fecharConexao();

			}
		});
		btnLimparHistorico.setBounds(343, 285, 185, 23);
		panel.add(btnLimparHistorico);
		
		JButton btnVerHistrico = new JButton("VER HISTÓRICO");
		btnVerHistrico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        conexaoBD conexao = new conexaoBD();
		        
		        conexao.criarHistorico(); 
		        conexao.atualizarHistorico();
		        conexao.visualizarHistorico();
		        conexao.fecharConexao();
		    }
		});

		btnVerHistrico.setBounds(10, 285, 185, 23);
		panel.add(btnVerHistrico);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("JOGO", null, panel_1, null);
		panel_1.setLayout(null);
		
		Button button = new Button("PAUSAR");
		button.setBounds(0, 0, 70, 22);
		panel_1.add(button);
		
		Button button_1 = new Button("SALVAR");
		button_1.setBounds(228, 0, 70, 22);
		panel_1.add(button_1);
		
		Button button_1_1 = new Button("REINICIAR");
		button_1_1.setBounds(468, 0, 70, 22);
		panel_1.add(button_1_1);
		
		JPanel painel_tabuleiro = new Tabuleiro(3);
		painel_tabuleiro.setBounds(0, 28, 544, 319);
		panel_1.add(painel_tabuleiro);
	}
}

