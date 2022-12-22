package negocio;

import ui.Tabuleiro;

public class QuebraCabeca {
	int tempo;
	float pontuacao;
	Tabuleiro jogo;
	
	void calcularPontuacao(int tempo) {
		if(tempo <= 60) {
			this.pontuacao = 1000;
		}
		else if(tempo < 600) {
			this.pontuacao = 100;
		}
		else if(tempo < 6000) {
			this.pontuacao = 10;
		}
	}
	
	

}
