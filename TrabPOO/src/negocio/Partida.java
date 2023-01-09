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
	
	public Partida(ArrayList<Jogador> jogadores, int image_selected) {
		iniciarPartida(jogadores, image_selected);
	}

	public void iniciarPartida(ArrayList<Jogador> jogadores, int image_selected) {
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

	void checkWin() {
		if (!tabuleiro.checkWin()) {
			cronometro.setVisibilityStart(true);
		} else {
			cronometro.stop();
			cronometro.setVisibilityStart(false);
		}
	}


	public void setPlayers(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
}
