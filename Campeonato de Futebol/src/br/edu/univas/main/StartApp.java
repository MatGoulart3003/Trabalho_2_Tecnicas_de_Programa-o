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
			deleteTeam(equipe, indice);
				
		}
		
		// cria jogo
		
		else if (option == 4) {
			
			int indiceMand = cadastraMandante(equipe,jogo);
			int indiceVisit = cadastraVisitante(equipe,jogo);
			int indice = addGameArray(partida,jogo);
			logicScore(equipe,jogo,golponto,indiceMand,indiceVisit,indice);
			
			
		}
		
		// edita jogo
		
		else if (option == 5) {
			
			System.out.println("Como você quer editar a partida?");
			System.out.println("\n1 - editar resultado: ");
			System.out.println("2 - editar times e resultado: ");
			int idx = scan.nextInt();
			scan.nextLine();
			logicEditGameScore(idx, partida,jogo,golponto,equipe);
					
		}
		
		else if (option == 6) {
			
			System.out.println("Qual jogo vc quer excluir?\n");
			searchGame(partida);
			int indice = scan.nextInt();
			partida[indice].mandante.pontos = partida[indice].mandante.pontos - golponto[indice].pontosMand;
			partida[indice].visitante.pontos = partida[indice].visitante.pontos - golponto[indice].pontosVisit;
			partida[indice].mandante.saldoGols = partida[indice].mandante.saldoGols - golponto[indice].saldoGolsMand;
			partida[indice].visitante.saldoGols = partida[indice].visitante.saldoGols - golponto[indice].saldoGolsVisit;
			
			partida[indice] = null;
			
			
			
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
	
	public static void deleteTeam (Equipe equipe [],int indice) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (i == indice) {
				
				equipe[i] = null;
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

	public static void logicScore(Equipe equipe[],Partida jogo, GolsPontos golponto[] , int indiceMand, int indiceVisit, int indice) {
		
		if(jogo.golMand > jogo.golVisit) {
			
			golponto[indice].pontosMand = 3;
			golponto[indice].pontosVisit = 0;
			golponto[indice].saldoGolsMand = jogo.golMand - jogo.golVisit;
			golponto[indice].pontosVisit = jogo.golVisit - jogo.golMand;
			
			equipe[indiceMand].pontos = equipe[indiceMand].pontos + golponto[indice].pontosMand;
			
			
			equipe[indiceMand].saldoGols = equipe[indiceMand].saldoGols + (golponto[indice].saldoGolsMand);
			equipe[indiceVisit].saldoGols = equipe[indiceVisit].saldoGols +(golponto[indice].saldoGolsVisit);
			
		
		}else if (jogo.golVisit > jogo.golMand) {
			
			golponto[indice].pontosMand = 0;
			golponto[indice].pontosVisit = 3;
			golponto[indice].saldoGolsMand = jogo.golMand - jogo.golVisit;
			golponto[indice].pontosVisit = jogo.golVisit - jogo.golMand;
			
			
			equipe[indiceVisit].pontos = equipe[indiceVisit].pontos + golponto[indice].pontosVisit ;
			equipe[indiceVisit].saldoGols = equipe[indiceVisit].saldoGols + (golponto[indice].pontosVisit);
			equipe[indiceMand].saldoGols = equipe[indiceMand].saldoGols + (golponto[indice].saldoGolsMand);
			
		}else {
			
			golponto[indice].pontosMand = 1;
			golponto[indice].pontosVisit = 1;
			equipe[indiceMand].pontos++;
			equipe[indiceVisit].pontos++;
			
			
		}
		
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
	
	public static void editArrayGame (int idx, Partida partida[], Partida jogo, GolsPontos golponto[], Equipe equipe []) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (i == idx) {
				
				int indiceMand = cadastraMandante(equipe,jogo);
				int indiceVisit = cadastraVisitante(equipe,jogo);
				logicScore(equipe,jogo, golponto, indiceMand,indiceVisit, idx);
				partida[i] = jogo;
				break;
			
			}
			
		}

		
	}
	
	public static void logicEditGameScore (int idx, Partida partida[], Partida jogo,GolsPontos golponto [], Equipe equipe []) {
		
		if (idx == 2) {
			
			System.out.println("Qual jogo você quer editar?\n");
			searchGame(partida);
			idx = scanIdx();
			editArrayGame(idx, partida, jogo, golponto, equipe);
		
		}else if (idx == 1) {
		
			System.out.println("Qual jogo você quer editar?\n");
			searchGame(partida);
			idx = scanIdx();
			
			System.out.println("Digite os gols do " + partida[idx].mandante.nome);
			partida[idx].golMand = scan.nextInt();
			scan.nextLine();
			
			System.out.println("Digite os gols do " + partida[idx].visitante.nome);
			partida[idx].golVisit = scan.nextInt();
			scan.nextLine();
		
		}
		
	}
	
	public static void deleteGame (int indice, Partida partida []) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (i == indice) {
				
				partida[i] = null;
				
				
				
				break;
			
			}
			
		}
		
	}
	
}
