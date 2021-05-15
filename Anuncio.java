import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Anuncio extends Calculadora {
	Scanner entrada = new Scanner(System.in);
	ArrayList<Anuncio> clientes = new ArrayList<Anuncio>();

	private String nomeAnuncio;
	private String cliente;
	private String dataInicio;
	private String dataTermino;
	private String investDiario;
	private String escCadastro;
	private int resultDias;
	public float invesTotal;

	public String toString() {
		return "Anúncio: " + this.getNomeAnuncio() + "\nCliente: " + this.getCliente() + "\nData Início: "
				+ this.getDataInicio() + "\nData Término: " + this.getDataTermino() + "\nInvestimento Diário R$: "
				+ this.getInvestDiario();
	}

	public void cadastrarAnuncio() {
		do {
			System.out.println("");
			System.out.print("Nome do anúncio: ");
			this.setNomeAnuncio(entrada.next());
			System.out.print("Nome do Cliete: ");
			this.setCliente(entrada.next());
			System.out.print("Inicio do anúncio (DD/MM/AAAA): ");
			this.setDataInicio(entrada.next());
			System.out.print("Término do anúncio (DD/MM/AAA): ");
			this.setDataTermino(entrada.next());

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataInicio = LocalDate.parse(getDataInicio(), formato);
			LocalDate dataTermino = LocalDate.parse(getDataTermino(), formato);

			Period periodo = Period.between(dataInicio, dataTermino);
			this.setResultDias(periodo.getDays() + 1);
			System.out.print("Investimento por dia R$: ");
			this.setInvestDiario(entrada.next());
			if (Float.valueOf(this.getInvestDiario()) % 1 == 0) {

				clientes.add(new Anuncio(this.getNomeAnuncio(), this.getCliente(), this.getDataInicio(),
						this.getDataTermino(), this.getInvestDiario()));
				System.out.println("Cadastro de Anúncio realizado com Sucesso!!!");
				System.out.println("");
				System.out.println("Deseja realizar mais um cadastro?");
				System.out.println("[S] SIM || [N] NÃO");
				System.out.print("==> ");
				this.setEscCadastro(entrada.next());
			} else {
				System.out.println("Valor digitado não é multiplo de R$1,00, TENTE NOVAMENTE!!!");
			}
		} while (this.getEscCadastro().equalsIgnoreCase("S"));

	}

	public void informacoes() {
		System.out.println("");
		System.out.println("================================================");
		System.out.println("Anúncio: " + this.getNomeAnuncio());
		System.out.println("Cliente: " + this.getCliente());
		System.out.println("Início do Anúncio: " + this.getDataInicio());
		System.out.println("Término do Anúncio: " + this.getDataTermino());
		System.out.println("Investimento Diário: " + this.getInvestDiario());
	}

	public void listarCadastrados() {
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println("");
			System.out.println("---------------------------------------------");
			System.out.println("-> " + clientes.get(i));
		}
		if (clientes.size() == 0) {
			System.out.println("Ainda não há anuncios cadastrados.");
		}
	}

	public void pesquisarCliente() {
		System.out.print("Digite o nome do Cliente: ");
		String busca = entrada.next();
		boolean achou = false;

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCliente().contains(busca)) {
				clientes.get(i).informacoes();
				System.out.println("");
				investimentoTotal();
				maxVisualizacoes();
				maxCliques();
				maxCompartilhamentos();
				System.out.println("");
				achou = true;
			} else if (!achou) {
				System.out.println("Cliente não encontrado!");
				System.out.println("");
			}
		}
	}

	public void pesquisarData() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.print("");
		System.out.print("Digite uma data: ");
		String busca1 = entrada.next();

		LocalDate dt1 = LocalDate.parse(busca1, formato);
		boolean achou = false;

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getDataInicio().contains(busca1)) {
				clientes.get(i).informacoes();
				System.out.println("");
				achou = true;
			} else if (!achou) {
				System.out.println("Cliente não encontrado!");
				System.out.println("");
				investimentoTotal();
				maxVisualizacoes();
				maxCliques();
				maxCompartilhamentos();
				System.out.println("");
			}
		}
	}

	public void investimentoTotal() {
		this.setInvesTotal(Float.valueOf(this.getInvestDiario()) * this.getResultDias());
		System.out.println("Valor de Investimento Total: " + this.getInvesTotal());
	}

	public void maxVisualizacoes() {
		this.setVisuTot((int) (this.getInvesTotal() * this.getVisOriginal()));
		if (this.getVisuTot() <= 100) {
			this.setGeraClique(this.getVisuTot() / 8);
			this.setGeraCompart(0);
			this.setResCompart(0);
			this.setResultadoFinal((this.getVisuTot() + this.getResCompart()));

		} else if (this.getVisuTot() > 100) {
			this.setClique(8);
			this.setGeraClique((this.getVisuTot() / getClique()));
		}
		if (this.getGeraClique() < 20) {
			this.setGeraCompart(0);
			this.setResCompart(0);
		} else if (this.getGeraClique() > 20) {
			this.setGeraCompart((this.getGeraClique() / 6));
			this.setResCompart((this.getGeraCompart() * this.getVisuCompart()));
			this.setResultadoFinal((this.getVisuTot() + this.getResCompart()));
		}
		System.out.println("Pojeção máxima de Visualizações: " + this.getResultadoFinal());

	}

	public void maxCliques() {
		this.setVisuTot((int) (this.getInvesTotal() * this.getVisOriginal()));
		if (this.getVisuTot() <= 100) {
			this.setGeraClique(this.getVisuTot() / 8);
			this.setGeraCompart(0);
			this.setResCompart(0);
			this.setResultadoFinal((this.getVisuTot() + this.getResCompart()));

		} else if (this.getVisuTot() > 100) {
			this.setClique(8);
			this.setGeraClique((this.getVisuTot() / getClique()));
		}
		System.out.println("Pojeção máxima de Cliques: " + this.getGeraClique());
	}

	public void maxCompartilhamentos() {
		this.setVisuTot((int) (this.getInvesTotal() * this.getVisOriginal()));
		if (this.getVisuTot() <= 100) {
			this.setGeraClique(this.getVisuTot() / 8);
			this.setGeraCompart(0);
			this.setResCompart(0);
			this.setResultadoFinal((this.getVisuTot() + this.getResCompart()));

		} else if (this.getVisuTot() > 100) {
			this.setClique(8);
			this.setGeraClique((this.getVisuTot() / getClique()));
		}
		if (this.getGeraClique() < 20) {
			this.setGeraCompart(0);
			this.setResCompart(0);
		} else if (this.getGeraClique() > 20) {
			this.setGeraCompart((this.getGeraClique() / 6));
		}
		System.out.println("Pojeção máxima de Compartilhamentos: " + this.getGeraCompart());

	}

	public Anuncio(String nomeAnuncio, String cliente, String dataInicio, String dataTermino, String investDiario) {
		this.nomeAnuncio = nomeAnuncio;
		this.cliente = cliente;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.investDiario = investDiario;
	}

	public String getNomeAnuncio() {
		return nomeAnuncio;
	}

	public void setNomeAnuncio(String nomeAnuncio) {
		this.nomeAnuncio = nomeAnuncio;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getInvestDiario() {
		return investDiario;
	}

	public void setInvestDiario(String investDiario) {
		this.investDiario = investDiario;
	}

	public String getEscCadastro() {
		return escCadastro;
	}

	public void setEscCadastro(String escCadastro) {
		this.escCadastro = escCadastro;
	}

	public float getInvesTotal() {
		return invesTotal;
	}

	public void setInvesTotal(float invesTotal) {
		this.invesTotal = invesTotal;
	}

	public int getResultDias() {
		return resultDias;
	}

	public void setResultDias(int resultDias) {
		this.resultDias = resultDias;
	}

}
