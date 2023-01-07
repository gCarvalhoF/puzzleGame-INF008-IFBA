package negocio;

import java.util.ArrayList;
import ui.Cronometro;
import ui.Tabuleiro;

public class Partida {
	
	public ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	public Tabuleiro tabuleiro;
	public Cronometro cronometro;
	
	public Partida(ArrayList<Jogador> jogadores, int image_selected) {
		super();
		this.jogadores = jogadores;
		this.tabuleiro = new Tabuleiro(image_selected, false);
		this.cronometro = new Cronometro((Tabuleiro) this.tabuleiro);
	}
}
