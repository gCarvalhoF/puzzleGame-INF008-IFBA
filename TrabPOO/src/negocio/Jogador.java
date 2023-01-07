package negocio;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
  private String nome;
  private int pontuacao;
  
  List<Jogador> jogadores = new ArrayList<>();

  public Jogador(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void calcularPontuacao(int tempo) {
	  
	 if (tempo <= 60) {
		this.setPontuacao(tempo * (50 / 3));
	 }
	 else if (tempo <= 600) {
		 this.setPontuacao(tempo * 6);
	 }
	 else {
		 this.setPontuacao(tempo * (1 / 600));
	 }
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }
}
