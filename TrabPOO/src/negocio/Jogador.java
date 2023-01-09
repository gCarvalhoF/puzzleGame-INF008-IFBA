package negocio;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	private String nome;
	
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

}
