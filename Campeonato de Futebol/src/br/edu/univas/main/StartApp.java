package br.edu.univas.main;

import java.util.Scanner;

import br.edu.univas.vo.Equipe;
import br.edu.univas.vo.GolsPontos;
import br.edu.univas.vo.Partida;

public class StartApp {
	
	public static Scanner scan = new Scanner(System.in);
	public static int rangeArray = 50;
	
	
	public static void main(String[] args) {
		
		Partida partida [] = new Partida [rangeArray];
		Equipe equipe [] = new Equipe [rangeArray];
		GolsPontos golponto [] = new GolsPontos [rangeArray];
		
		do {
			
			System.out.println("\n\nBem vindo ao sistema de CAMPEONATO DE FUTEBOL: ");
			screenOptions();
			
			int option = scan.nextInt();
			scan.nextLine();
			
			selectOption(option, equipe, partida, golponto);
			
			if (option == 9) {
				break;
			
			} else if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7) {
				
				System.out.println("Opção inválida!!");
				
			}
			
			
		}while (true);
		
		scan.close();
		
	}
	
	public static void selectOption(int option, Equipe equipe [], Partida partida [], GolsPontos golponto[]) {
		
		Equipe time = new Equipe();		
		Partida jogo = new Partida();
		GolsPontos GP = new GolsPontos();
		// opção de criar equipe
		
		if (option == 1) {
			
			preencheTime(time);
			addTeamArray(equipe,time);
			
		}
		
		// edita time
		
		else if (option == 2) {
			
			System.out.println("Qual equipe você quer editar?\n");
			searchTeam(equipe);			
			int indice = scanIdx();			
			editArrayTeam(indice, equipe, time);
			
		}
		
		// exclui time
		
		else if (option == 3) {
			
			System.out.println("Qual equipe você quer excluir?\n");
			searchTeam(equipe);	
			int indice = scanIdx();				
			deleteTeam(equipe, indice , golponto , partida,  jogo, GP);
			
		}
		
		// cria jogo
		
		else if (option == 4) {
			
			int indiceMand = cadastraMandante(equipe,jogo);
			int indiceVisit = cadastraVisitante(equipe,jogo);
			int indice = addGameArray(partida,jogo);
			logicScore(equipe,jogo,golponto,indiceMand,indiceVisit,indice, GP);
			
			
		}
		
		// edita jogo
		
		else if (option == 5) {
			
			System.out.println("Como você quer editar a partida?");
			System.out.println("\n1 - editar resultado: ");
			System.out.println("2 - editar times e resultado: ");
			int idx = scan.nextInt();
			scan.nextLine();
			logicEditGameScore(idx, partida,jogo,golponto,equipe, GP);
					
		}
		
		else if (option == 6) {
			
			System.out.println("Qual jogo vc quer excluir?\n");
			searchGame(partida);
			int indice = scan.nextInt();
			deleteGame(partida, indice, golponto, jogo, GP);
			
		}
	
		else if (option == 7 ) {
			
			
			
		}
	
	}
	
	public static void preencheTime (Equipe equipe) {
		
			
		System.out.println("Digite o nome da equipe à ser cadastrada: ");
		equipe.nome = scan.nextLine();
		System.out.println("Digite agora o estado de origem da equipe: ");
		equipe.estado = scan.nextLine();
		
		
	}

	public static void screenOptions () {
		
		System.out.println("\n==========================================");
		System.out.println("Selecione uma ação à seguir!");
		System.out.println("1 - Cadastrar time");
		System.out.println("2 - Editar time");
		System.out.println("3 - Excluir Time");
		System.out.println("4 – Cadastrar Jogo");
		System.out.println("5 – Editar Jogo");
		System.out.println("6 – Excluir Jogo");
		System.out.println("7 – Listar Classificação do Campeonato");
		System.out.println("9 - Sair");
		System.out.println("==========================================");
	}
	
	public static void searchTeam(Equipe equipe []) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (equipe[i] != null) {
				
				System.out.println( i + " " + equipe[i].nome);
				
			}
			
		}
		
	}

	public static void editArrayTeam (int indice, Equipe equipe [], Equipe time) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (i == indice) {
				
				preencheTime (time);
				equipe[i] = time;
				break;
			}
			
		}
		
	}

	public static int scanIdx () {
		
		System.out.println("\nDigite o indice do time desejado: ");
		int indice = scan.nextInt();
		scan.nextLine();
		
		return indice;
	}
	
	public static void addTeamArray (Equipe equipe [], Equipe time ) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (equipe[i] == null) {
				
				equipe[i] = time;
				break;
			}
			
		}
		
	}
	
	public static void deleteTeam (Equipe equipe [],int indice, GolsPontos golponto [], Partida partida [], Partida jogo, GolsPontos GP) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (i == indice) {
				
				equipe [i] = null;
				partida[indice] = jogo;
				
				for(int j = 0; j < rangeArray; j++) {
					
					deleteGame(partida, indice, golponto, jogo, GP);
					
				}
																
				break;
			}
			
		}
		
	}
	
	public static int cadastraMandante (Equipe equipe [], Partida jogo) {
		
		System.out.println("Selecione as equipes que voce quer cadastrar a partida:\n ");
		searchTeam(equipe);	
		System.out.println("\nTIME MANDANTE\n ");
		int indice = scanIdx();
		jogo.mandante = equipe[indice];
		
		System.out.println("Digite agora os gols do time mandante: ");
		jogo.golMand = scan.nextInt();
		scan.nextLine();
		
		return indice;
		
	}
	
	public static int cadastraVisitante (Equipe equipe[], Partida jogo) {
		
		searchTeam(equipe);
		System.out.println("\nTIME VISITANTE\n ");
		int indice = scanIdx();
		jogo.visitante = equipe[indice];
		
		System.out.println("Digite agora os gols do time visitante: ");
		jogo.golVisit = scan.nextInt();
		scan.nextLine();
		
		return indice;
	}

	public static void logicScore(Equipe equipe[],Partida jogo, GolsPontos golponto[] , int indiceMand, int indiceVisit, int indice, GolsPontos GP) {
		
		
		
		if(jogo.golMand > jogo.golVisit) {
			
			GP.pontosMand = 3;
			GP.pontosVisit = 0;
			GP.saldoGolsMand = jogo.golMand - jogo.golVisit;
			GP.pontosVisit = jogo.golVisit - jogo.golMand;
			
			equipe[indiceMand].pontos = equipe[indiceMand].pontos + GP.pontosMand;
			
			
			equipe[indiceMand].saldoGols = equipe[indiceMand].saldoGols + (GP.saldoGolsMand);
			equipe[indiceVisit].saldoGols = equipe[indiceVisit].saldoGols +(GP.saldoGolsVisit);
			
		
		}else if (jogo.golVisit > jogo.golMand) {
			
			GP.pontosMand = 0;
			GP.pontosVisit = 3;
			GP.saldoGolsMand = jogo.golMand - jogo.golVisit;
			GP.pontosVisit = jogo.golVisit - jogo.golMand;
			
			
			equipe[indiceVisit].pontos = equipe[indiceVisit].pontos + GP.pontosVisit ;
			equipe[indiceVisit].saldoGols = equipe[indiceVisit].saldoGols + (GP.pontosVisit);
			equipe[indiceMand].saldoGols = equipe[indiceMand].saldoGols + (GP.saldoGolsMand);
			
		}else {
			
			GP.pontosMand = 1;
			GP.pontosVisit = 1;
			equipe[indiceMand].pontos++;
			equipe[indiceVisit].pontos++;
			
			
		}
		
		golponto[indice] = GP;
	}
	
	public static int addGameArray (Partida partida [], Partida jogo) {
		
		int i;
		for (i = 0; i < rangeArray; i++) {
			
			if (partida[i] == null) {
				partida[i] = jogo;
				break;
			}
		}
		
		return i;
	}
	
	public static void searchGame (Partida partida []) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (partida[i] != null) {
				
				System.out.println( i + " - " + partida[i].mandante.nome + " " + partida[i].golMand + " X " + partida[i].golVisit + " " + partida[i].visitante.nome);
										
			}
			
		}
		
	}
	
	public static void editArrayGame (int idx, Partida partida[], Partida jogo, GolsPontos golponto[], Equipe equipe [], GolsPontos GP) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (i == idx) {
				
				int indiceMand = cadastraMandante(equipe,jogo);
				int indiceVisit = cadastraVisitante(equipe,jogo);
				partida[idx].mandante.pontos = partida[idx].mandante.pontos - golponto[idx].pontosMand;
				partida[idx].visitante.pontos = partida[idx].visitante.pontos - golponto[idx].pontosVisit;
				partida[idx].mandante.saldoGols = partida[idx].mandante.saldoGols - golponto[idx].saldoGolsMand;
				partida[idx].visitante.saldoGols = partida[idx].visitante.saldoGols - golponto[idx].saldoGolsVisit;
								
				logicScore(equipe,jogo, golponto, indiceMand,indiceVisit, idx, GP);
				partida[i] = jogo;
				break;
			
			}
			
		}

		
	}
	
	public static void logicEditGameScore (int idx, Partida partida[], Partida jogo,GolsPontos golponto [], Equipe equipe [], GolsPontos GP) {
		
		if (idx == 2) {
			
			System.out.println("Qual jogo você quer editar?\n");
			searchGame(partida);
			idx = scanIdx();
			editArrayGame(idx, partida, jogo, golponto, equipe, GP);
		
		}else if (idx == 1) {
		
			System.out.println("Qual jogo você quer editar?\n");
			searchGame(partida);
			idx = scanIdx();
			
			
			
			
			System.out.println("Digite os gols do " + partida[idx].mandante.nome);
			int golMand = scan.nextInt();
			scan.nextLine();
			if (golMand > partida[idx].golMand) {
				int result = golMand - partida[idx].golMand;
				golponto[idx].saldoGolsMand = golponto[idx].saldoGolsMand + result;
				partida[idx].mandante.saldoGols = partida[idx].mandante.saldoGols + result; 
			}else if (golMand < partida[idx].golMand) {
				int result = partida[idx].golMand - golMand;
				golponto[idx].saldoGolsMand = golponto[idx].saldoGolsMand - result;
				partida[idx].mandante.saldoGols = partida[idx].mandante.saldoGols - result;
			}
			int golMandante = partida[idx].golMand;
			partida[idx].golMand = golMand;
			
			System.out.println("Digite os gols do " + partida[idx].visitante.nome);
			int golVisit = scan.nextInt();
			scan.nextLine();
			if (golVisit > partida[idx].golVisit) {
				int result = golVisit - partida[idx].golVisit;
				golponto[idx].saldoGolsVisit = golponto[idx].saldoGolsVisit + result;
				partida[idx].visitante.saldoGols = partida[idx].visitante.saldoGols + result;
				
			}else if (golVisit < partida[idx].golVisit) {
				int result = partida[idx].golVisit - golVisit;
				golponto[idx].saldoGolsVisit = golponto[idx].saldoGolsVisit - result;
				partida[idx].visitante.saldoGols = partida[idx].visitante.saldoGols - result;
			}
			int golVisitante = partida[idx].golVisit;
			partida[idx].golVisit = golVisit;
			
			if (golMand > golVisit) {
				
				if (golMandante < golVisitante) {
					
					golponto[idx].pontosMand = 3;
					partida[idx].mandante.pontos = partida[idx].mandante.pontos + 3; 
					golponto[idx].pontosVisit = 0;
					partida[idx].visitante.pontos = partida[idx].visitante.pontos - 3;
					
				}else if (golMandante ==  golVisitante) {
					
					golponto[idx].pontosMand = 3;
					partida[idx].mandante.pontos = partida[idx].mandante.pontos + 2; 
					golponto[idx].pontosVisit = 0;
					partida[idx].visitante.pontos = partida[idx].visitante.pontos - 1;
										
				}
				
			}else if (golVisit > golMand) {
				
				if (golVisitante < golMandante) {
					
					golponto[idx].pontosMand = 0;
					partida[idx].mandante.pontos = partida[idx].mandante.pontos -3; 
					golponto[idx].pontosVisit = 3;
					partida[idx].visitante.pontos = partida[idx].visitante.pontos + 3;
					
				}else if (golMandante ==  golVisitante) {
					
					golponto[idx].pontosMand = 0;
					partida[idx].mandante.pontos = partida[idx].mandante.pontos -1; 
					golponto[idx].pontosVisit = 3;
					partida[idx].visitante.pontos = partida[idx].visitante.pontos + 2;
					
				}
				
			}else {
				
				if (golMandante > golVisitante) {
					
					golponto[idx].pontosMand = 1;
					partida[idx].mandante.pontos = partida[idx].mandante.pontos - 2; 
					golponto[idx].pontosVisit = 1;
					partida[idx].visitante.pontos = partida[idx].visitante.pontos + 1;
					
				}else if (golMandante < golVisitante) {
					
					golponto[idx].pontosMand = 1;
					partida[idx].mandante.pontos = partida[idx].mandante.pontos + 1; 
					golponto[idx].pontosVisit = 1;
					partida[idx].visitante.pontos = partida[idx].visitante.pontos - 2;
					
				}
				
				
			}
			
			
			
		}
		
	}
	
	public static void deleteGame(Partida partida [], int indice, GolsPontos golponto [],Partida jogo, GolsPontos GP) {
		
		
		if (jogo.mandante == null) {
			
		//	jogo.visitante.saldoGols = jogo.visitante.saldoGols - GP.saldoGolsVisit;
		//	jogo.visitante.pontos = jogo.visitante.pontos - GP.pontosVisit;
			
		}else if (jogo.visitante == null){
			
		//	jogo.mandante.pontos = jogo.mandante.pontos - GP.pontosMand;
		//	jogo.mandante.saldoGols = jogo.mandante.saldoGols - GP.saldoGolsMand;
			
		}else {
			
		//	jogo.mandante.pontos = jogo.mandante.pontos - GP.pontosMand;
		//	jogo.visitante.pontos = jogo.visitante.pontos - GP.pontosVisit;
		//	jogo.mandante.saldoGols = jogo.mandante.saldoGols - GP.saldoGolsMand;
		//	jogo.visitante.saldoGols = jogo.visitante.saldoGols - GP.saldoGolsVisit;
		
		}
		golponto[indice] = GP;
		golponto[indice] = null;
		partida[indice] = jogo;
		partida[indice] = null;
		
	}
	
}
