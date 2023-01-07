package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Bd.conexaoBD;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

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
import java.util.List;
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
		frame.setBounds(100, 100, 1048, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("MENU", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("JOGO", null, panel_1, null);
		panel_1.setLayout(null);
				
		JRadioButton rdbtnEmbaralharImpar = new JRadioButton("EMBARALHAR IMPAR");
		rdbtnEmbaralharImpar.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnEmbaralharImpar.setBounds(462, 169, 164, 23);
		panel.add(rdbtnEmbaralharImpar);
		
		JLabel lblNewLabel = new JLabel("Opcional:");
		lblNewLabel.setBounds(397, 173, 66, 14);
		panel.add(lblNewLabel);
		
		JLabel lblJogador = new JLabel("Jogador 3:");
		lblJogador.setBounds(397, 132, 80, 14);
		panel.add(lblJogador);
		
		txtJogador3 = new JTextField();
		txtJogador3.setColumns(10);
		txtJogador3.setBounds(476, 132, 143, 20);
		panel.add(txtJogador3);
		
		txtJogador2 = new JTextField();
		txtJogador2.setColumns(10);
		txtJogador2.setBounds(476, 107, 143, 20);
		panel.add(txtJogador2);
		
		JLabel lbJogador2 = new JLabel("Jogador 2:");
		lbJogador2.setBounds(397, 107, 80, 14);
		panel.add(lbJogador2);
		
		JLabel lbJogador1 = new JLabel("Jogador 1:");
		lbJogador1.setBounds(397, 82, 80, 14);
		panel.add(lbJogador1);
		
		txtJogador1 = new JTextField();
		txtJogador1.setColumns(10);
		txtJogador1.setBounds(476, 82, 143, 20);
		panel.add(txtJogador1);
		
		JLabel lbImageTitulo = new JLabel("");
		ImageIcon icon2 = new ImageIcon(new ImageIcon("./Images/Title.png").
				getImage().getScaledInstance(400, 91, Image.SCALE_DEFAULT));
		lbImageTitulo.setIcon(icon2);
		lbImageTitulo.setBounds(314, 0, 400, 90);
		panel.add(lbImageTitulo);
		
		JLabel lbTitulo = new JLabel("MENU INICIAL");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbTitulo.setBounds(250, 11, 543, 60);
		panel.add(lbTitulo);
		
		JButton btnLimparHistorico = new JButton("LIMPAR HIST\u00D3RICO");
		btnLimparHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();
				
				conexao.limparHistorico(); // Limpa o histórico
				conexao.limparTabelaJogadores();
				conexao.fecharConexao(); // Fecha a conexão com o banco de dados
			}
		});


		btnLimparHistorico.setBounds(593, 285, 185, 23);
		panel.add(btnLimparHistorico);
		
		JButton btnVerHistrico = new JButton("VER HISTÓRICO");
		btnVerHistrico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        conexaoBD conexao = new conexaoBD();
		       
		        conexao.atualizarHistorico();
		        // Obtem os dados do histórico
		        List<String> historico = conexao.visualizarHistorico();

		        // Crie a nova janela ou aba para exibir os dados do histórico
		        JFrame janelaHistorico = new JFrame("Histórico de Vencedores");
		        JTabbedPane abas = new JTabbedPane();
		        JPanel painelHistorico = new JPanel();
		        JTextArea areaTexto = new JTextArea();
		        JScrollPane barraRolagem = new JScrollPane(areaTexto);
		        abas.addTab("Histórico", barraRolagem);
		        janelaHistorico.getContentPane().add(abas);
		        janelaHistorico.setSize(300, 200);
		        janelaHistorico.setLocationRelativeTo(null);
		        janelaHistorico.setVisible(true);

		        // Exiba os dados do histórico na nova janela ou aba
		        for (String linha : historico) {
		            areaTexto.append(linha + "\n");
		        }

		        conexao.fecharConexao();
		    }
		});


		btnVerHistrico.setBounds(260, 285, 185, 23);
		panel.add(btnVerHistrico);
		
		Random random = new Random();
		int numero = random.nextInt(4);
		while(numero == 0) {
			numero = random.nextInt(4);
		}
		

		Tabuleiro painel_tabuleiro = new Tabuleiro(numero, true);
		painel_tabuleiro.setBounds(0, 28, 544, 319);
		panel_1.add(painel_tabuleiro);
		
		JLabel lbImgCompleta = new JLabel("");
		lbImgCompleta.setBounds(559, 6, 186, 187);
		panel_1.add(lbImgCompleta);
		
		
		JLabel lblNewLabel_1 = new JLabel("Jogador Atual:");
		lblNewLabel_1.setBounds(10, 6, 98, 14);
		panel_1.add(lblNewLabel_1);
		
		Cronometro painel_cronometro = new Cronometro((Tabuleiro) painel_tabuleiro, this);
		painel_cronometro.setBounds(650, 28, 544, 319);
		panel_1.add(painel_cronometro);
		
		JLabel lbJogadorAtual = new JLabel();
		lbJogadorAtual.setFont(new Font("Arial Black", Font.BOLD, 12));
		lbJogadorAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lbJogadorAtual.setBounds(79, 0, 98, 26);
		panel_1.add(lbJogadorAtual);
		
		JRadioButton rdBtManoelGomes = new JRadioButton("Manoel Gomes");
		rdBtManoelGomes.setVisible(false);
		rdBtManoelGomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdBtManoelGomes.isSelected()) {
					painel_tabuleiro.switchImage(1);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 1 + ".png").
							getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});
		buttonGroup.add(rdBtManoelGomes);
		rdBtManoelGomes.setBounds(569, 236, 149, 23);
		panel_1.add(rdBtManoelGomes);
		
		JRadioButton rdBtOrochi = new JRadioButton("Orochi");
		rdBtOrochi.setVisible(false);
		rdBtOrochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdBtOrochi.isSelected()) {
					painel_tabuleiro.switchImage(2);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 2 + ".png").
							getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});
		buttonGroup.add(rdBtOrochi);
		rdBtOrochi.setBounds(569, 261, 149, 23);
		panel_1.add(rdBtOrochi);
		
		JRadioButton rdBtFenda = new JRadioButton("Fenda Do Bíquini");
		rdBtFenda.setVisible(false);
		rdBtFenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdBtFenda.isSelected()) {
					painel_tabuleiro.switchImage(3);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 3 + ".png").
							getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});
		buttonGroup.add(rdBtFenda);
		rdBtFenda.setBounds(569, 287, 149, 23);
		panel_1.add(rdBtFenda);
		
		
		if(numero == 1) {
			rdBtManoelGomes.setSelected(true);
		}else if (numero == 2){
			rdBtOrochi.setSelected(true);
		}else {
			rdBtFenda.setSelected(true);
		}
		
		JLabel lblNewLabel_2 = new JLabel("Selecione a imagem:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(569, 206, 149, 23);
		lblNewLabel_2.setVisible(false);
		panel_1.add(lblNewLabel_2);
		
		JButton btTrocarFoto = new JButton("Trocar Foto");
		btTrocarFoto.setFont(new Font("Arial", Font.BOLD, 13));
		btTrocarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdBtManoelGomes.setVisible(true);
				rdBtOrochi.setVisible(true);
				rdBtFenda.setVisible(true);
				lblNewLabel_2.setVisible(true);
				btTrocarFoto.setVisible(false);
				
			}
		});
		btTrocarFoto.setBounds(569, 207, 164, 34);
		panel_1.add(btTrocarFoto);
		
		
		JButton btSalvarJogo = new JButton("Salvar Jogo");
		btSalvarJogo.setFont(new Font("Arial", Font.PLAIN, 12));
		btSalvarJogo.setBounds(161, 66, 150, 23);
		painel_cronometro.add(btSalvarJogo);
		
		JButton btAutoResolve = new JButton("Auto-Resolve");
		btAutoResolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel_cronometro.stop();
				painel_tabuleiro.solveBoard();
			}
		});
		btAutoResolve.setFont(new Font("Arial", Font.PLAIN, 13));
		btAutoResolve.setBounds(124, 189, 219, 25);
		painel_cronometro.add(btAutoResolve);
		
		ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + numero + ".png").
				getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
		lbImgCompleta.setIcon(icon);
		
		JButton btnNewButton = new JButton("Jogar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				conexaoBD banco = new conexaoBD();
				banco.criarTabelaTabuleiro();
				
				// Cria uma instância da classe conexaoBD
				conexaoBD conexao = new conexaoBD();
				
				// Obtém os valores dos campos de texto
				String nomeJogador1 = txtJogador1.getText();
				String nomeJogador2 = txtJogador2.getText();
				String nomeJogador3 = txtJogador3.getText();
								
				((Tabuleiro) painel_tabuleiro).shuffleBoard();
				tabbedPane.setSelectedIndex(1);
				painel_cronometro.start();
				
				//Nome Jogador1
				lbJogadorAtual.setText(txtJogador1.getText());
								
				// Chama o método inserirJogadores da classe conexaoBD, passando os valores dos campos de texto como argumentos
				conexao.criarTabela();
				conexao.inserirJogadores(nomeJogador1, nomeJogador2, nomeJogador3);
				conexao.fecharConexao();
			}
		});


		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(413, 223, 206, 51);
		panel.add(btnNewButton);
		
		JLabel lbBG = new JLabel("");
		ImageIcon icon3 = new ImageIcon(new ImageIcon("./Images/4.png").
				getImage().getScaledInstance(1034, 300, Image.SCALE_DEFAULT));
		lbBG.setIcon(icon3);
		lbBG.setBounds(0, 55, 1034, 284);
		panel.add(lbBG);
		
		JLabel lbBG2 = new JLabel("");
		ImageIcon icon4 = new ImageIcon(new ImageIcon("./Images/5.png").
				getImage().getScaledInstance(145, 132, Image.SCALE_DEFAULT));
		lbBG2.setIcon(icon4);
		lbBG2.setBounds(732, -35, 180, 156);
		panel.add(lbBG2);
	}
}

