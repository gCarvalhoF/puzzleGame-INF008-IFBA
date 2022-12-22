package Bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class conexaoBD {
	private Connection conn;
	private Statement stmt;
	
	public conexaoBD() {
		String url="jdbc:sqlite:teste.db";
		try {
			conn=DriverManager.getConnection(url);
			stmt=conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void criarTabela() {
		try {
			String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='jogadores'";
			ResultSet rs = stmt.executeQuery(query);
			if (!rs.next()) {
			String createTableQuery = "CREATE TABLE jogadores (nome VARCHAR(32), pontuacao INTEGER)";
			stmt.executeUpdate(createTableQuery);
			}
			rs.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}}
	
	
	public void criarHistorico() {
		try {
		String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='historico'";
		ResultSet rs = stmt.executeQuery(query);
		if (!rs.next()) {
		query = "CREATE TABLE historico "
		+ "(nome VARCHAR(32), "
		+ "pontuacao INTEGER)";
		stmt.executeUpdate(query);
		}

		rs.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public void atualizarHistorico() {
		try {
			String query = "INSERT INTO historico (nome, pontuacao) SELECT nome, pontuacao FROM jogadores WHERE nome NOT IN (SELECT nome FROM historico) ORDER BY pontuacao DESC";
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void visualizarHistorico() {
		try {
			String query = "SELECT * FROM historico";
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


	public void limparHistorico() {
		try {
			String query = "DELETE FROM historico";
			stmt.executeUpdate(query);
			atualizarHistorico();
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
		
