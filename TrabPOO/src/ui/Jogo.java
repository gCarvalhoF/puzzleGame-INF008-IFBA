package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Bd.conexaoBD;
import negocio.Jogador;
import negocio.Partida;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;

public class Jogo {

	private JFrame frame;
	private JTextField txtJogador3;
	private JTextField txtJogador2;
	private JTextField txtJogador1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

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
		ArrayList<JLabel> partidas = new ArrayList<JLabel>();
		ArrayList<JRadioButton> btsPartidas = new ArrayList<JRadioButton>();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./Images/iconJogo.png"));
		frame.setBounds(100, 100, 1048, 425);
		frame.setTitle("Puzzle-Game POO");
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
		rdbtnEmbaralharImpar.setBounds(446, 230, 164, 23);
		panel.add(rdbtnEmbaralharImpar);

		JButton btNextPlayer = new JButton("Próximo Jogador");
		btNextPlayer.setVisible(false);
		btNextPlayer.setFont(new Font("Arial", Font.PLAIN, 13));
		btNextPlayer.setBounds(803, 31, 164, 23);
		panel_1.add(btNextPlayer);

		JButton btVoltar = new JButton("Voltar ao Menu");
		btVoltar.setFont(new Font("Arial", Font.PLAIN, 14));
		btVoltar.setBounds(803, 261, 164, 23);
		panel_1.add(btVoltar);

		JLabel lbImgCompleta = new JLabel("");
		lbImgCompleta.setBounds(559, 6, 186, 187);
		panel_1.add(lbImgCompleta);

		JButton btTrocarFoto = new JButton("Trocar Foto");
		btTrocarFoto.setFont(new Font("Arial", Font.BOLD, 13));
		btTrocarFoto.setBounds(569, 207, 164, 34);
		panel_1.add(btTrocarFoto);

		JLabel lbSelecioneImg = new JLabel("Selecione a imagem:");
		lbSelecioneImg.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbSelecioneImg.setHorizontalAlignment(SwingConstants.CENTER);
		lbSelecioneImg.setBounds(569, 206, 149, 23);
		lbSelecioneImg.setVisible(false);
		panel_1.add(lbSelecioneImg);

		JRadioButton rdBtFenda = new JRadioButton("Fenda Do Bíquini");
		rdBtFenda.setVisible(false);
		buttonGroup.add(rdBtFenda);
		rdBtFenda.setBounds(569, 287, 149, 23);
		panel_1.add(rdBtFenda);

		JLabel lblNewLabel = new JLabel("Opcional:");
		lblNewLabel.setBounds(395, 234, 66, 14);
		panel.add(lblNewLabel);

		JLabel lblJogador = new JLabel("Jogador 3:");
		lblJogador.setBounds(388, 193, 80, 14);
		panel.add(lblJogador);

		txtJogador3 = new JTextField();
		txtJogador3.setColumns(10);
		txtJogador3.setBounds(467, 193, 143, 20);
		panel.add(txtJogador3);

		txtJogador2 = new JTextField();
		txtJogador2.setColumns(10);
		txtJogador2.setBounds(467, 168, 143, 20);
		panel.add(txtJogador2);

		JLabel lbJogador2 = new JLabel("Jogador 2:");
		lbJogador2.setBounds(388, 168, 80, 14);
		panel.add(lbJogador2);

		JLabel lbJogador1 = new JLabel("Jogador 1:");
		lbJogador1.setBounds(388, 143, 80, 14);
		panel.add(lbJogador1);

		txtJogador1 = new JTextField();
		txtJogador1.setColumns(10);
		txtJogador1.setBounds(467, 143, 143, 20);
		panel.add(txtJogador1);

		JLabel lbImageTitulo = new JLabel("");
		ImageIcon icon2 = new ImageIcon(
				new ImageIcon("./Images/Title.png").getImage().getScaledInstance(400, 91, Image.SCALE_DEFAULT));
		lbImageTitulo.setIcon(icon2);
		lbImageTitulo.setBounds(312, 0, 400, 95);
		panel.add(lbImageTitulo);

		JLabel lbTitulo = new JLabel("MENU INICIAL");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 21));
		lbTitulo.setBounds(231, 109, 543, 23);
		panel.add(lbTitulo);

		JButton btnLimparHistorico = new JButton("LIMPAR HISTÓRICO");
		btnLimparHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();

				conexao.limparHistorico(); // Limpa o histórico
				conexao.limparTabelaJogadores();
				conexao.fecharConexao(); // Fecha a conexão com o banco de dados
			}
		});

		btnLimparHistorico.setBounds(790, 202, 185, 23);
		panel.add(btnLimparHistorico);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Partidas Anteriores", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Histórico", null, panel_3, null);
		
		JButton btnVerHistrico = new JButton("VER HISTÓRICO");
		btnVerHistrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();
				
				conexao.atualizarHistorico();
				List<String> historico = conexao.visualizarHistorico();
				
				// Crie a nova janela ou aba para exibir os dados do histórico
				JTextArea areaTexto = new JTextArea();
				for (String linha : historico) {
					areaTexto.append(linha + "\n");
				}
				conexao.fecharConexao();
				panel_3.add(areaTexto);
				tabbedPane.setSelectedIndex(3);
			}
		});

		btnVerHistrico.setBounds(790, 176, 185, 23);
		panel.add(btnVerHistrico);

		JLabel lbJogadorAtual = new JLabel();
		lbJogadorAtual.setFont(new Font("Arial", Font.BOLD, 12));
		lbJogadorAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lbJogadorAtual.setBounds(79, 0, 98, 26);
		panel_1.add(lbJogadorAtual);

		JRadioButton rdBtManoelGomes = new JRadioButton("Manoel Gomes");
		rdBtManoelGomes.setVisible(false);
		buttonGroup.add(rdBtManoelGomes);
		rdBtManoelGomes.setBounds(569, 236, 149, 23);
		panel_1.add(rdBtManoelGomes);

		JButton btnNewButton = new JButton("Novo Jogo");
		ArrayList<Jogador> jogadores = new ArrayList<Jogador>();

		Partida partida = new Partida(jogadores, 1);

		Cronometro painel_cronometro = partida.cronometro;
		painel_cronometro.setBounds(650, 28, 544, 319);
		panel_1.add(painel_cronometro);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(txtJogador1.getText().isEmpty()) || !(txtJogador2.getText().isEmpty())
						|| !(txtJogador3.getText().isEmpty())) {

					conexaoBD banco = new conexaoBD();
					banco.criarTabelaPartida();

					// Cria uma instância da classe conexaoBD
					conexaoBD conexao = new conexaoBD();
					conexao.criarTabela();

					if (!(txtJogador1.getText().isEmpty())) {
						Jogador jogador1 = new Jogador(txtJogador1.getText());
						jogadores.add(jogador1);
						conexao.inserirJogador(jogador1.getNome());
					}

					if (!(txtJogador2.getText().isEmpty())) {
						Jogador jogador2 = new Jogador(txtJogador2.getText());
						jogadores.add(jogador2);
						conexao.inserirJogador(jogador2.getNome());
					}

					if (!(txtJogador3.getText().isEmpty())) {
						Jogador jogador3 = new Jogador(txtJogador3.getText());
						jogadores.add(jogador3);
						conexao.inserirJogador(jogador3.getNome());
					}
					// Obtém os valores dos campos de texto
					tabbedPane.setSelectedIndex(1);
					rdBtManoelGomes.setSelected(true);

					txtJogador1.setText("");
					txtJogador2.setText("");
					txtJogador3.setText("");
					conexao.fecharConexao();
					lbJogadorAtual.setText(jogadores.get(0).getNome());

					partida.iniciarPartida(jogadores, 2);
					painel_cronometro.start();
				} else {
					JOptionPane.showMessageDialog(null,
							"Não há jogadores escalados para a partida, favor inserir o(s) nome(s)!");
				}
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(403, 260, 206, 51);
		panel.add(btnNewButton);

		Tabuleiro painel_tabuleiro = partida.tabuleiro;
		painel_tabuleiro.setBounds(0, 28, 544, 319);
		panel_1.add(painel_tabuleiro);

		JLabel lblNewLabel_1 = new JLabel("Jogador Atual:");
		lblNewLabel_1.setBounds(10, 6, 98, 14);
		panel_1.add(lblNewLabel_1);

		rdBtManoelGomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtManoelGomes.isSelected()) {
					painel_tabuleiro.switchImage(1);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 1 + ".png").getImage()
							.getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});

		JRadioButton rdBtOrochi = new JRadioButton("Orochi");
		rdBtOrochi.setVisible(false);
		rdBtOrochi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtOrochi.isSelected()) {
					painel_tabuleiro.switchImage(2);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 2 + ".png").getImage()
							.getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});
		buttonGroup.add(rdBtOrochi);
		rdBtOrochi.setBounds(569, 261, 149, 23);
		panel_1.add(rdBtOrochi);

		rdBtFenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdBtFenda.isSelected()) {
					painel_tabuleiro.switchImage(3);
					ImageIcon icon = new ImageIcon(new ImageIcon("./Images/" + 3 + ".png").getImage()
							.getScaledInstance(190, 190, Image.SCALE_DEFAULT));
					lbImgCompleta.setIcon(icon);
				}
			}
		});

		btTrocarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdBtManoelGomes.setVisible(true);
				rdBtOrochi.setVisible(true);
				rdBtFenda.setVisible(true);
				lbSelecioneImg.setVisible(true);
				btTrocarFoto.setVisible(false);
			}
		});

		JButton btSalvarJogo = new JButton("Salvar Jogo");
		btSalvarJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conexaoBD conexao = new conexaoBD();

				int[] arrayBoard = painel_tabuleiro.boardToArray();
				// Salva o tabuleiro no banco de dados
				for (int i = 0; i < jogadores.size(); i++) {
					conexao.salvarPartida(arrayBoard, jogadores.get(i), painel_cronometro.getElapsedTime(),
							Integer.parseInt(painel_tabuleiro.image_selected), partida.getId()); // chama o método salvarTabuleiro da classe
				}
				
				jogadores.clear();
																			// conexaoBD
				JOptionPane.showMessageDialog(null, "Jogo salvo com sucesso!");
			}
		});

		btSalvarJogo.setFont(new Font("Arial", Font.PLAIN, 12));
		btSalvarJogo.setBounds(161, 66, 150, 23);
		painel_cronometro.add(btSalvarJogo);

		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel_cronometro.stop();
				tabbedPane.setSelectedIndex(0);
			}
		});

		ImageIcon icon = new ImageIcon(
				new ImageIcon("./Images/" + 1 + ".png").getImage().getScaledInstance(190, 190, Image.SCALE_DEFAULT));
		lbImgCompleta.setIcon(icon);

		JButton btAutoResolve = new JButton("Auto-Resolve");
		btAutoResolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painel_cronometro.stop();
				painel_tabuleiro.solveBoard();
				painel_cronometro.setVisibilityStart(false);
				btNextPlayer.setVisible(true);

				// btAutoResolve.setEnabled(false);
			}
		});
		btAutoResolve.setFont(new Font("Arial", Font.PLAIN, 13));
		btAutoResolve.setBounds(124, 189, 219, 25);
		painel_cronometro.add(btAutoResolve);

		JButton btContinuarJogo = new JButton("Carregar Jogo Anterior");
		btContinuarJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				conexaoBD conexao = new conexaoBD();
				
				conexao.listarPartidas();
				
				JLabel lbPartida1 = new JLabel("Partida 1");
				lbPartida1.setToolTipText("");
				lbPartida1.setBounds(410, 71, 217, 23);
				panel_2.add(lbPartida1);

				JRadioButton rdbtnNewRadioButton = new JRadioButton("");
				buttonGroup_1.add(rdbtnNewRadioButton);
				rdbtnNewRadioButton.setBounds(373, 71, 21, 23);
				panel_2.add(rdbtnNewRadioButton);

				/*
				 * int[][] board = conexao.carregarPartida(1); painel_tabuleiro.setBoard(board);
				 * 
				 * painel_cronometro.setElapsedTime(conexao.carregarCronometro());
				 * 
				 * int selected_image = conexao.carregarImagemTabuleiro();
				 * painel_tabuleiro.switchImage(selected_image); ImageIcon icon = new
				 * ImageIcon(new ImageIcon("./Images/" + selected_image + ".png").getImage()
				 * .getScaledInstance(190, 190, Image.SCALE_DEFAULT));
				 * lbImgCompleta.setIcon(icon); switch (selected_image) { case 1: {
				 * rdBtManoelGomes.setSelected(true); ; } case 2: {
				 * rdBtOrochi.setSelected(true); ; } case 3: { rdBtFenda.setSelected(true); ; }
				 * }
				 * 
				 * tabbedPane.setSelectedIndex(1); painel_cronometro.start();
				 * 
				 * conexao.fecharConexao();
				 */
			}
		});
		btContinuarJogo.setFont(new Font("Arial", Font.PLAIN, 13));
		btContinuarJogo.setBounds(388, 319, 242, 23);
		panel.add(btContinuarJogo);

		JLabel lbBG = new JLabel("");
		ImageIcon icon3 = new ImageIcon(
				new ImageIcon("./Images/4.png").getImage().getScaledInstance(1034, 300, Image.SCALE_DEFAULT));
		lbBG.setIcon(icon3);
		lbBG.setBounds(0, 55, 1034, 311);
		panel.add(lbBG);

		JLabel lbBG2 = new JLabel("");
		ImageIcon icon4 = new ImageIcon(
				new ImageIcon("./Images/5.png").getImage().getScaledInstance(145, 132, Image.SCALE_DEFAULT));
		lbBG2.setIcon(icon4);
		lbBG2.setBounds(732, -46, 180, 156);
		panel.add(lbBG2);

		JLabel lblNewLabel_2 = new JLabel("ESCOLHA A PARTIDA QUE DESEJA RETOMAR:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 27));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(111, 11, 819, 32);
		panel_2.add(lblNewLabel_2);
		
		JButton btVoltarTela = new JButton("Voltar ao Menu");
		btVoltarTela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btVoltarTela.setBounds(410, 324, 192, 23);
		panel_2.add(btVoltarTela);
		btVoltarTela.setBounds(410, 324, 192, 23);

		JButton btnNewButton_1 = new JButton("Carregar Partida");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_1.setBounds(353, 272, 295, 43);
		panel_2.add(btnNewButton_1);
	}
}
