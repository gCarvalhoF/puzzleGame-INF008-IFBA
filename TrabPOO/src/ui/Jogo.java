package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Bd.conexaoBD;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;


import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;

public class Jogo {

	private JFrame frame;
	private JTextField txtJogador3;
	private JTextField txtJogador2;
	private JTextField txtJogador1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	

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
		frame.setBounds(100, 100, 788, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("MENU", null, panel, null);
		panel.setLayout(null);
			
		JRadioButton rdbtnEmbaralharImpar = new JRadioButton("EMBARALHAR IMPAR");
		rdbtnEmbaralharImpar.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEmbaralharImpar.setBounds(304, 169, 164, 23);
		panel.add(rdbtnEmbaralharImpar);
		
		JLabel lblNewLabel = new JLabel("Opcional:");
		lblNewLabel.setBounds(239, 173, 66, 14);
		panel.add(lblNewLabel);
		
		JLabel lblJogador = new JLabel("Jogador 3:");
		lblJogador.setBounds(239, 132, 80, 14);
		panel.add(lblJogador);
		
		txtJogador3 = new JTextField();
		txtJogador3.setColumns(10);
		txtJogador3.setBounds(318, 132, 143, 20);
		panel.add(txtJogador3);
		
		txtJogador2 = new JTextField();
		txtJogador2.setColumns(10);
		txtJogador2.setBounds(318, 107, 143, 20);
		panel.add(txtJogador2);
		
		JLabel lbJogador2 = new JLabel("Jogador 2:");
		lbJogador2.setBounds(239, 107, 80, 14);
		panel.add(lbJogador2);
		
		JLabel lbJogador1 = new JLabel("Jogador 1:");
		lbJogador1.setBounds(239, 82, 80, 14);
		panel.add(lbJogador1);
		
		txtJogador1 = new JTextField();
		txtJogador1.setColumns(10);
		txtJogador1.setBounds(318, 82, 143, 20);
		panel.add(txtJogador1);
		
		JLabel lbTitulo = new JLabel("MENU INICIAL");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbTitulo.setBounds(92, 11, 543, 60);
		panel.add(lbTitulo);
		
		JButton btnLimparHistorico = new JButton("LIMPAR HIST\u00D3RICO");
		btnLimparHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();
				
				conexao.limparHistorico();
				conexao.fecharConexao();

			}
		});
		btnLimparHistorico.setBounds(435, 285, 185, 23);
		panel.add(btnLimparHistorico);
		
		JButton btnVerHistrico = new JButton("VER HISTÓRICO");
		btnVerHistrico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        conexaoBD conexao = new conexaoBD();

		        conexao.criarHistorico(); 
		        conexao.atualizarHistorico();
		        conexao.visualizarHistorico();
		        conexao.fecharConexao();
		        
		        /*// show a joptionpane dialog using showMessageDialog
		        JOptionPane.showMessageDialog(frame,"Problem writing to backup directory:", "Histórico de Vencedores",JOptionPane.INFORMATION_MESSAGE);*/
		    }
		});

		btnVerHistrico.setBounds(102, 285, 185, 23);
		panel.add(btnVerHistrico);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("JOGO", null, panel_1, null);
		panel_1.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Jogador Atual:");
		lblNewLabel_1.setBounds(10, 6, 98, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lbJogadorAtual = new JLabel();
		lbJogadorAtual.setFont(new Font("Arial Black", Font.BOLD, 12));
		lbJogadorAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lbJogadorAtual.setBounds(79, 0, 98, 26);
		panel_1.add(lbJogadorAtual);
		
		JRadioButton rdBtManoelGomes = new JRadioButton("Manoel Gomes");
		rdBtManoelGomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdBtManoelGomes.isSelected()) {
					//trocarFoto
				}
			}
		});
		buttonGroup.add(rdBtManoelGomes);
		rdBtManoelGomes.setBounds(569, 236, 149, 23);
		panel_1.add(rdBtManoelGomes);
		
		JRadioButton rdBtOrochi = new JRadioButton("Orochi");
		rdBtOrochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdBtOrochi.isSelected()) {
					//trocarFoto
				}
			}
		});
		buttonGroup.add(rdBtOrochi);
		rdBtOrochi.setBounds(569, 261, 149, 23);
		panel_1.add(rdBtOrochi);
		
		JRadioButton rdBtFenda = new JRadioButton("Fenda Do Bíquini");
		rdBtFenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdBtFenda.isSelected()) {
					//trocarFoto
				}
			}
		});
		buttonGroup.add(rdBtFenda);
		rdBtFenda.setBounds(569, 287, 149, 23);
		panel_1.add(rdBtFenda);
		
		JLabel lbImgCompleta = new JLabel("");
		lbImgCompleta.setBounds(559, 6, 186, 187);
		panel_1.add(lbImgCompleta);
		
		JButton btnNewButton = new JButton("Jogar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random random = new Random();
				int numero = random.nextInt(4);
				while(numero == 0) {
					numero = random.nextInt(4);
				}
				
				if(numero == 1) {
					rdBtManoelGomes.setSelected(true);
				}else if (numero == 2){
					rdBtOrochi.setSelected(true);
				}else {
					rdBtFenda.setSelected(true);
				}

				
				// Cria uma instância da classe conexaoBD
				conexaoBD conexao = new conexaoBD();
				
				// Obtém os valores dos campos de texto
				String nomeJogador1 = txtJogador1.getText();
				String nomeJogador2 = txtJogador2.getText();
				String nomeJogador3 = txtJogador3.getText();
				
				JPanel painel_tabuleiro = new Tabuleiro(numero);
				painel_tabuleiro.setBounds(0, 28, 544, 319);
				panel_1.add(painel_tabuleiro);
				
				JPanel painel_cronometro = new Cronometro();
				painel_cronometro.setBounds(159, 0, 374, 205);
				panel_1.add(painel_cronometro);
				
				tabbedPane.setSelectedIndex(1);
				
				//Nome Jogador1
				lbJogadorAtual.setText(txtJogador1.getText());
				
				ImageIcon icon = new ImageIcon(new ImageIcon("C:/Users/Lucas/Desktop/FotosTrabPOO/" + numero + ".png").
						getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
				lbImgCompleta.setIcon(icon);
				
				// Chama o método inserirJogadores da classe conexaoBD, passando os valores dos campos de texto como argumentos
				conexao.criarTabela();
				conexao.inserirJogadores(nomeJogador1, nomeJogador2, nomeJogador3);
				conexao.fecharConexao();
			}
		});


		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(255, 223, 206, 51);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Selecione a imagem:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(569, 206, 149, 23);
		panel_1.add(lblNewLabel_2);
		
	}
}

