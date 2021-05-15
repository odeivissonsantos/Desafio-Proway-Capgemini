import java.util.Scanner;

public class AppDivulgaTudo {
	public static void main(String[] args) {
		try (Scanner entrada = new Scanner(System.in)) {
			int escSwitch;
			Calculadora cotacao = new Calculadora();
			Anuncio visualiza = new Anuncio(null, null, null, null, null);

			do {
				System.out.println("");
				System.out.println("==========[AGÊNCIA DIVULGA TUDO]==========");
				System.out.println("");
				System.out.println("==> O que deseja fazer?");
				System.out.println("[1] Cadastrar Anúncio");
				System.out.println("[2] Visualizar Anúncios Cadastrados");
				System.out.println("[3] Cotação de Visualizações");
				System.out.println("[4] Relatório por Cliente");
				System.out.println("[5] Relatório por Intervalo de Tempo");
				System.out.println("[0] Sair");
				System.out.print("===> ");
				escSwitch = entrada.nextInt();
				switch (escSwitch) {
				case 1:
					visualiza.cadastrarAnuncio();
					break;
				case 2:
					visualiza.listarCadastrados();
					break;
				case 3:
					cotacao.calcula();
					break;
				case 4:
					visualiza.pesquisarCliente();
					break;
				case 5:
					visualiza.pesquisarData();
					break;
				case 0:
					System.out.println("");
					System.out.println("A Agência Divulga Tudo agradece, tenha um ótimo dia.");
					break;
				default:
					System.out.println("");
					System.out.println("DIGITE UMA OPÇÃO VÁLIDA, TENTE NOVAMENTE!");
					break;
				}

			} while (escSwitch != 0);

		}
	}

}
