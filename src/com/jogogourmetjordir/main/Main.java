package com.jogogourmetjordir.main;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.net.MalformedURLException;
import java.net.URL;

import com.jogogourmetjordir.domain.GameNode;
import com.jogogourmetjordir.interfaces.PratosInterface;



public class Main implements PratosInterface<GameNode> {

	public static int index = 1;
	public static GameNode raiz;
	private String comida;
	private String oqueFaz;
	private int qtdAcertos=0;
	


	@Override
	public GameNode getArvore() {
		return raiz;
	}


	@Override
	public void setArvore(GameNode arvore) {
		raiz = arvore;
	}
	


	@Override
	public void inserirPrato(GameNode noArvore) throws Exception {

		comida = JOptionPane.showInputDialog(null, "Qual prato voce pensou?");

		 oqueFaz = JOptionPane.showInputDialog("Um(a) " + comida + "______ mas um(a) " + noArvore.valor + " nao.");
		if(comida!=null) {
			if(oqueFaz!=null ) {
				String aux = noArvore.valor;
				noArvore.valor = oqueFaz;
				noArvore.noDireito = new GameNode(++index, comida);
				noArvore.noEsquerdo = new GameNode(++index, aux);
			}
			else {
				JOptionPane.showMessageDialog(null, "O tipo do prato não pode ser nulo!!Você perdeu :( | Começe um novo jogo!","Game Over",JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}else {
			JOptionPane.showMessageDialog(null, "O prato não pode ser nulo!! Você perdeu :( | Começe um novo jogo!","Game Over",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}


	@Override
	public void perguntar(GameNode gameNode) {
		int pergunta = JOptionPane.showConfirmDialog(null, "O prato que voce pensou e " + gameNode.valor, "Confirme",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (pergunta == 0) {
			
			if (gameNode.noDireito == null) {
				this.qtdAcertos++;
				JOptionPane.showMessageDialog(null, "Acertei novamente! Já são "+qtdAcertos+" acertos!!");
			}
			else {
			
				perguntar(gameNode.noDireito);
			}
		} else {
			if (gameNode.noEsquerdo == null)
				try {
					inserirPrato(gameNode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			else 
				perguntar(gameNode.noEsquerdo);
			
		}
	}

	public static void main(String[] args) throws MalformedURLException {
		int sair;
		Main main = new Main();
		ImageIcon icon = new ImageIcon(new URL("https://acegif.com/wp-content/gifs/coin-flip-54.gif"));
		JOptionPane.showMessageDialog(null, "Pense em um prato","JogoGoumetJRF",JOptionPane.INFORMATION_MESSAGE,icon);
		if (main.getArvore() == null) {
			main.setArvore(new GameNode(index, "É uma massa ?"));
			main.getArvore().noEsquerdo = new GameNode(++index, "Sushi");
			main.getArvore().noDireito = new GameNode(++index, "Macarrao");
		}
		sair = 1;
		while (sair == 1) {
			int resposta = JOptionPane.showConfirmDialog(null, "O prato que voce pensou e " + main.getArvore().valor,
					"Confirme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) {
				main.perguntar(main.getArvore().noDireito);
			} else {
				main.perguntar(main.getArvore().noEsquerdo);
			}
			if (resposta == JOptionPane.CLOSED_OPTION) {
				sair = 0;
			}

		} 

	}

}
