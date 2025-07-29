package sudoku;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class Quadro {

	// Matriz com as 81 posições do jogo
	int[][] quadro = new int[9][9];

	// Pra guardar uma cópia do jogo inicial com os números FIXOS digitados
	// Uso na lógica pra NÃO permitir alteração destes números pelo usuário
	int[][] copia = new int[9][9];

	// Quantidade de linhas e colunas
	private int tamanho = 9;

	// Resetar o jogo
	public void zerarQuadro() {
		for (int i = 0; i < quadro.length; i++) {

			for (int j = 0; j < quadro[i].length; j++) {
				quadro[i][j] = 0;
			}
		}
	}

	public void quadroMontado() {

		String mostrar = "";
		for (int i = 0; i < quadro.length; i++) {
			if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8) {

				mostrar += "\n";
			}

			for (int j = 0; j < quadro[i].length; j++) {

				mostrar = mostrar + quadro[i][j] + " ";

			}
		}
		JOptionPane.showMessageDialog(null, mostrar);

	}

	// Método que monta as 81 posições com zero
	public void montarQuadro() {

		for (int i = 0; i < quadro.length; i++) {
			arrumarMatriz(i);

			for (int j = 0; j < quadro[i].length; j++) {
				System.out.print(quadro[i][j] + " ");

			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}

	// Método com a lógica pra deixar a matriz com 9 linhas e 9 colunas na tela
	public static void arrumarMatriz(int i) {

		if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8) {

			System.out.println();
		}
	}

	// Preenhe números iniciais
	public void iniciarJogo() {

		// Sempre ao iniciar jogo, o quadro será resetado
		zerarQuadro();

		int quantidadeFixos = Integer
				.parseInt(JOptionPane.showInputDialog(null, "Quantos números fixos preencherá na tabela ?"));

		for (int i = 0; i < quantidadeFixos; i++) {
			int linha = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a linha"));
			int coluna = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a coluna"));
			int fixo = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número fixo"));

			// validar entrada de dados
			if (fixo < 10 && fixo > 0) {

				// Validar se espaço é vazio
				if (quadro[linha][coluna] == 0) {

					// Validar regras do jogo
					if (validarLinha(quadro, linha, fixo) && validarColuna(quadro, coluna, fixo)
							&& validarQuadrante(quadro, linha, coluna, fixo)) {

						quadro[linha][coluna] = fixo;
						montarQuadro();

					} else {
						JOptionPane.showMessageDialog(null,
								"ERRO\n" + fixo + " Já existente" + "na linha, coluna ou quadrante");
						zerarQuadro();
						return;
					}

				} else {

					JOptionPane.showMessageDialog(null, "Você já havia preenchido este espaço");
					zerarQuadro();
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Digite números entre 1 e 9");
				zerarQuadro();

				return;
			}

		} // for

		// Cria uma cópia do jogo inicial com os números FIXOS
		for (int i = 0; i < quadro.length; i++) {

			for (int j = 0; j < quadro[i].length; j++) {
				copia[i][j] = quadro[i][j];

			}
		}

	} // fim metodo

	public void jogar() {

		int linha = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a linha"));
		int coluna = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a coluna"));
		int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número "));

		// 1 validar entrada de dados
		if (numero < 10 && numero > 0) {

			// 2 espaços não preenchidos no inicio
			if (copia[linha][coluna] == 0) {

				// 3 Validar REGRAS do jogo pra inserir o número
				if (validarLinha(quadro, linha, numero) && validarColuna(quadro, coluna, numero)
						&& validarQuadrante(quadro, linha, coluna, numero)) {

					// Verifica se usuario já tinha preenchido espaço e pergunta se quer trocar o
					// número
					if (quadro[linha][coluna] != 0) {
						int trocar = Integer
								.parseInt(JOptionPane.showInputDialog(null, "Trocar ?" + "\n1 - Sim\n2 - Não"));
						if (trocar != 1) {
							return;
						}
					}
					quadro[linha][coluna] = numero;

					// 3
				} else {
					JOptionPane.showMessageDialog(null,
							"ERRO\n" + numero + " Já existente" + "na linha, coluna ou quadrante");
				}

				// 2
			} else {

				JOptionPane.showMessageDialog(null, "Espaço FIXO, não permite alteração");
			}
			
			// 1
		} else {
			JOptionPane.showMessageDialog(null, "Digite números entre 1 e 9");
			return;
		}

	}// Método
	
	
	
	
	

	// Os métodos abaixo validam as regras do jogo
	
	
	
	
	
	

	// Método que valida se número já existe na linha
	public boolean validarLinha(int[][] quadro, int linha, int numero) {
		for (int i = 0; i < tamanho; i++) {
			if (quadro[linha][i] == numero) {
				return false;
			}
		}
		return true;
	}

	// Método pra validar se número já existe na coluna
	public boolean validarColuna(int[][] quadro, int coluna, int numero) {
		for (int i = 0; i < tamanho; i++) {
			if (quadro[i][coluna] == numero) {
				return false;
			}
		}
		return true;
	}

	// Método pra validar se número já existe no quadrante 3x3
	public boolean validarQuadrante(int[][] quadro, int linha, int coluna, int numero) {

		int localLinha = linha - linha % 3;
		int localColuna = coluna - coluna % 3;

		for (int i = localLinha; i < localLinha + 3; i++) {
			for (int j = localColuna; j < localColuna + 3; j++) {
				if (quadro[i][j] == numero) {
					return false;
				}
			}
		}
		return true;
	}

}// classe
