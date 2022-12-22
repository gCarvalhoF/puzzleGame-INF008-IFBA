package negocio;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
  private String nome;
  private int pontuacao;
  
  List<Jogador> jogadores = new ArrayList<>();

  public Jogador(String nome, int pontuacao) {
    this.nome = nome;
    this.pontuacao = pontuacao;
    jogadores.add(this);
  }

  public void adicionarJogador(Jogador jogador) {
    jogadores.add(jogador);
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }
}
