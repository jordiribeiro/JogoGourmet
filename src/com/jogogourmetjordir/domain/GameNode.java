package com.jogogourmetjordir.domain;


public class GameNode {

	public int elemento;
	public GameNode noEsquerdo;
	public GameNode noDireito;
	public String valor;

	public GameNode(int elemento, String valor) {
		this.elemento = elemento;
		this.valor = valor;
	}

	public GameNode() {

	}

}
