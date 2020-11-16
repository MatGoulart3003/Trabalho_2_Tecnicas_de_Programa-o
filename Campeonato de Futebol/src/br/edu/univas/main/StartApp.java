package br.edu.univas.main;

import java.util.Scanner;

import br.edu.univas.vo.Equipe;
import br.edu.univas.vo.Partida;

public class StartApp {
	
	public static Scanner scan = new Scanner(System.in);
	public static int rangeArray = 50;
	
	
	public static void main(String[] args) {
		
		Partida partida [] = new Partida [rangeArray];
		Equipe equipe [] = new Equipe [rangeArray];
		
		do {
			
			System.out.println("\n\nBem vindo ao sistema de CAMPEONATO DE FUTEBOL: ");
			screenOptions();
			
			int option = scan.nextInt();
			scan.nextLine();
			
			selectOption(option, equipe, partida);
			
			if (option == 9) {
				break;
			
			} else if (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7) {
				
				System.out.println("Opção inválida!!");
				
			}
			
			
		}while (true);
		
		scan.close();
		
	}
	
	public static void selectOption(int option, Equipe equipe [], Partida partida []) {
		
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
			
			int indice = cadastraMandante(equipe,jogo);
			int indice2 = cadastraVisitante(equipe,jogo);
			logicScore(equipe,jogo,indice,indice2);
			addGameArray(partida,jogo);
			
		}
		
		// edita jogo
		
		else if (option == 5) {
			
			System.out.println("Qual jogo você quer editar?\n");
			searchGame(partida);
			int indice = scanIdx();
			editArrayGame(indice, partida, jogo, equipe);
			
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
		
		System.out.println("Digite o indice do time desejado: ");
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

	public static void logicScore(Equipe equipe[],Partida jogo, int indice, int indice2) {
		
		if(jogo.golMand > jogo.golVisit) {
			
			equipe[indice].pontos = equipe[indice].pontos + 3;
			equipe[indice].saldoGols = jogo.golMand - jogo.golVisit;
			equipe[indice2].saldoGols = jogo.golVisit - jogo.golMand;
			
		
		}else if (jogo.golVisit > jogo.golMand) {
			
			equipe[indice2].pontos = equipe[indice2].pontos + 3;
			equipe[indice2].saldoGols = jogo.golVisit - jogo.golVisit;
			equipe[indice].saldoGols = jogo.golMand - jogo.golVisit;
			
		}
		
	}
	
	public static void addGameArray (Partida partida [], Partida jogo) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (partida[i] == null) {
				
				partida[i] = jogo;
				break;
			}
			
		}
		
	}
	
	public static void searchGame (Partida partida []) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (partida[i] != null) {
				
				System.out.println( i + " - " + partida[i].mandante.nome + " X " + partida[i].visitante.nome);
				
			}
			
		}
		
	}
	
	public static void editArrayGame (int idx, Partida partida[], Partida jogo, Equipe equipe []) {
		
		for (int i = 0; i < rangeArray; i++) {
			
			if (i == idx) {
				
				int indice = cadastraMandante(equipe,jogo);
				int indice2 = cadastraVisitante(equipe,jogo);
				logicScore(equipe,jogo,indice,indice2);
				addGameArray(partida,jogo);
				partida[i] = jogo;
				break;
			
			}
			
		}

		
	}
	
}
