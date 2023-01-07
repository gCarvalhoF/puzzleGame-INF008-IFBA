package Bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public void criarTabelaTabuleiro() {
		try {
			String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='tabuleiro'";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
				query = "CREATE TABLE tabuleiro (id INTEGER PRIMARY KEY AUTOINCREMENT, valor INTEGER, linha INTEGER, coluna INTEGER)";
				stmt.executeUpdate(query);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void salvarTabuleiro(int[][] tabuleiro) {
		try {
			String query = "INSERT INTO tabuleiro (linha, coluna, valor) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);

			// Percorre o tabuleiro e insere cada valor na tabela
			for (int i = 0; i < tabuleiro.length; i++) {
				for (int j = 0; j < tabuleiro[i].length; j++) {
					stmt.setInt(1, i);
					stmt.setInt(2, j);
					stmt.setInt(3, tabuleiro[i][j]);
					stmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int[][] carregarTabuleiro() {
		int[][] board = new int[4][4];
		try {
			String query = "SELECT * FROM tabuleiro ORDER BY linha, coluna";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int valor = rs.getInt("valor");
				int linha = rs.getInt("linha");
				int coluna = rs.getInt("coluna");
				board[linha][coluna] = valor;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
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

	public void inserirJogadores(String nomeJogador1, String nomeJogador2, String nomeJogador3) {
		// TODO Auto-generated method stub
		inserirDados(nomeJogador1, 0);
		inserirDados(nomeJogador2, 0);
		inserirDados(nomeJogador3, 0);

	}

}
