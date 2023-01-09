package negocio;

import java.util.ArrayList;

import Bd.conexaoBD;
import ui.Cronometro;
import ui.Tabuleiro;

public class Partida {

	public ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	public Tabuleiro tabuleiro;
	public Cronometro cronometro;
	private int id;
	private int currentPlayer = -1;
	private float pontuacao;

	public Partida(ArrayList<Jogador> jogadores, int image_selected) {
		iniciarPartida(jogadores, image_selected);
	}

	public void iniciarPartida(ArrayList<Jogador> jogadores, int image_selected) {
		this.jogadores = jogadores;
		this.tabuleiro = new Tabuleiro(image_selected, false);
		this.cronometro = new Cronometro((Tabuleiro) this.tabuleiro);
		this.checkWin();
		this.setId();
	}

	public int getId() {
		return id;
	}

	public void setId() {
		conexaoBD conexao = new conexaoBD();
		this.id = conexao.getIdUltimoRegistroPartida() + 1;
	}

	public boolean checkWin() {
		if (!tabuleiro.checkWin()) {
			cronometro.setVisibilityStart(true);
			return true;
		} else {
			cronometro.stop();
			cronometro.setVisibilityStart(false);
			return false;
		}
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int incrementCurrentPlayer() {
		setCurrentPlayer(currentPlayer + 1);
		return currentPlayer;
	}
	
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public float calcularPontuacao(float tempo) {
		if (tempo <= 60) {
			this.setPontuacao(tempo * (50.0f / 3.0f));
		} else if (tempo <= 600) {
			this.setPontuacao(tempo * 6);
		} else {
			this.setPontuacao(tempo * (1.0f / 600.0f));
		}
		return getPontuacao();
	}

	public float getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(float pontuacao) {
		this.pontuacao = pontuacao;
	}

}