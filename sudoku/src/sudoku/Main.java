package sudoku;

import javax.swing.JOptionPane;

public class Main {
	
	Quadro quadro = new Quadro();
	
	public void menu() {
		
		
		
		int menu = Integer.parseInt(JOptionPane.showInputDialog(null,"SUDOKU \n\n"
				+"1 - Iniciar jogo\n"+"2 - Estado atual do jogo"));
		
		if (menu == 1) {
			quadro.iniciarJogo();
			quadro.quadroMontado();			
			menu();	
			
		}else if(menu == 2) {
			quadro.quadroMontado();
			menu();
		}else if(menu == 3 ) {
			quadro.jogar();
			menu();
		}
		
	
	}
	

	public static void main(String[] args) {
		
		Main m = new Main();
		m.menu();;
	}
}
		

		
				
		
	
            	
            	  
            	  
            	  
    
