package Bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import negocio.Jogador;
import ui.Tabuleiro;

public class conexaoBD {

	private Tabuleiro tabuleiro;
	private Connection conn;
	private Statement stmt;

	public conexaoBD() {

		String url = "jdbc:sqlite:teste.db";
		try {
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void criarTabela() {
		try {
			String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='jogadores'";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				String createTableQuery = "CREATE TABLE jogadores (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(32), pontuacao INTEGER)";
				stmt.executeUpdate(createTableQuery);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void criarHistorico() {
		try {
			String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='historico'";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				query = "CREATE TABLE historico (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(32), pontuacao INTEGER)";
				stmt.executeUpdate(query);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void criarTabelaPartida() {
		try {
			String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='partida'";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				query = "CREATE TABLE partida (id_partida INTEGER PRIMARY KEY AUTOINCREMENT, ordem_tabuleiro string, nome_jogador string, elapsed_time int, image_selected int)";
				stmt.executeUpdate(query);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvarPartida(int[] tabuleiro, Jogador jogador, int elapsedTime, int image_selected) {
		
		// Transformar array de ordem do tabuleiro em string para ser armazenado no banco
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tabuleiro.length; i++) {
		    sb.append(tabuleiro[i]);
		    if (i < tabuleiro.length - 1) {
		        sb.append(", ");
		    }
		}
		String numbersString = sb.toString();
				
		try {
			String query = "INSERT INTO partida (ordem_tabuleiro, nome_jogador, elapsed_time, image_selected) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);

			// Percorre o array e insere cada valor na tabela
			stmt.setString(1, numbersString);
			stmt.setString(2, jogador.getNome());
			stmt.setInt(3, elapsedTime);
			stmt.setInt(4, image_selected);
			stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int[][] carregarPartida(int id_partida) {
		int[][] board = new int[4][4];
		
	    
	    try {
	        String query = "SELECT ordem_tabuleiro FROM partida";
	        ResultSet rs = stmt.executeQuery(query);
	        
	        String[] numbersAsStrings = rs.getString(1).split(", ");
	        int[] numbers = Arrays.stream(numbersAsStrings).mapToInt(Integer::parseInt).toArray();
	        rs.close();

	        
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					board[i][j] = numbers[i * 4 + j];
				}
			}
			
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return board;
	}
	
	public int carregarCronometro() {
		int elapsedTime = 0;
	    
	    try {
	        String query = "SELECT elapsed_time FROM partida";
	        ResultSet rs = stmt.executeQuery(query);
	        	        
	        elapsedTime = rs.getInt(1);
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return elapsedTime;	
	}
	
	public int carregarImagemTabuleiro() {
		int image_selected = 1;
	    
	    try {
	        String query = "SELECT image_selected FROM partida";
	        ResultSet rs = stmt.executeQuery(query);
	        	        
	        image_selected = rs.getInt(1);
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return image_selected;	
	}


	public void atualizarHistorico() {
		try {
			String query = "INSERT INTO historico (nome, pontuacao) SELECT nome, pontuacao FROM jogadores WHERE nome NOT IN (SELECT nome FROM historico) ORDER BY pontuacao DESC";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> visualizarHistorico() {
		List<String> historico = new ArrayList<>();
		try {
			String query = "SELECT * FROM historico ORDER BY pontuacao DESC";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nome = rs.getString("nome");
				int movimentos = rs.getInt("pontuacao");
				String item = nome + " - " + movimentos + " pontuação";
				historico.add(item);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return historico;
	}

	public void limparHistorico() {
		try {
			// Crie a query para apagar todos os registros da tabela historico
			String query = "DELETE FROM historico";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void limparTabelaJogadores() {
		try {
			String query = "DELETE FROM jogadores";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirDados(String nome, int pontuacao) {
		try {
			// Verifica se o jogador já existe na tabela jogadores
			String query = "SELECT nome FROM jogadores WHERE nome = '" + nome + "'";
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				// Jogador já existe na tabela, basta atualizar sua pontuação
				query = "UPDATE jogadores SET pontuacao = " + pontuacao + " WHERE nome = '" + nome + "'";
				stmt.executeUpdate(query);
			} else {
				// Jogador não existe na tabela, basta inserir o nome e a pontuação
				query = "INSERT INTO jogadores (nome, pontuacao) VALUES ('" + nome + "', " + pontuacao + ")";
				stmt.executeUpdate(query);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void recuperarDados() {
		try {
			String query = "SELECT * FROM jogadores";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String nome = rs.getString("nome");
				int pontuacao = rs.getInt("pontuacao");
				System.out.println("Nome: " + nome + ", Pontuação: " + pontuacao);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fecharConexao() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirJogador(String nomeJogador) {
		inserirDados(nomeJogador, 0);
	}

}