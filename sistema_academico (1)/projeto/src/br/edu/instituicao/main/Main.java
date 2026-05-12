package br.edu.instituicao.main;

import br.edu.instituicao.factory.AlunoFactory;
import br.edu.instituicao.factory.CoordenadorFactory;
import br.edu.instituicao.factory.PessoaFactory;
import br.edu.instituicao.factory.ProfessorFactory;
import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Coordenador;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;
import br.edu.instituicao.service.RelatorioAcademico;
import br.edu.instituicao.service.Secretaria;

import java.util.Scanner;

/**
 * Classe principal do sistema academico.
 *
 * [PADRAO] Singleton: A Secretaria e obtida via getInstance() — nunca via new.
 * [PADRAO] Factory Method: A criacao de Aluno, Professor e Coordenador e
 * delegada as fabricas do pacote br.edu.instituicao.factory, eliminando
 * as estruturas condicionais de instanciacao que existiam nesta classe.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    /**
     * [PADRAO] Singleton: acesso ao registro central unico da instituicao.
     * Substituicao do operador 'new Secretaria()' pelo ponto de acesso global.
     */
    static Secretaria secretaria = Secretaria.getInstance();

    public static void main(String[] args) {
        int opcao = -1;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   SISTEMA ACADEMICO - POO em Java    ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (opcao != 7) {
            exibirMenu();
            opcao = lerInt("Escolha uma opcao: ");

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarProfessorOuCoordenador();
                case 3 -> lancarNotas();
                case 4 -> secretaria.listarTodos();
                case 5 -> exibirEstatisticas();
                case 6 -> acessoAdministrativo();
                case 7 -> System.out.println("\n  Sistema encerrado. Ate logo!\n");
                default -> System.out.println("\n  [ERRO] Opcao invalida! Tente novamente.\n");
            }
        }
    }

    static void exibirMenu() {
        System.out.println("\n----------------------------------------");
        System.out.println("  1. Cadastrar Aluno");
        System.out.println("  2. Cadastrar Professor / Coordenador");
        System.out.println("  3. Lancar Notas de Aluno");
        System.out.println("  4. Listar Comunidade Academica");
        System.out.println("  5. Exibir Estatisticas (Media Geral)");
        System.out.println("  6. Acesso Administrativo (Login)");
        System.out.println("  7. Sair");
        System.out.println("----------------------------------------");
    }

    static void cadastrarAluno() {
        System.out.println("\n--- CADASTRAR ALUNO ---");
        System.out.print("  Nome      : "); String nome = sc.nextLine();
        System.out.print("  CPF       : "); String cpf = sc.nextLine();
        System.out.print("  Email     : "); String email = sc.nextLine();
        System.out.print("  Matricula : "); String matricula = sc.nextLine();

        // [PADRAO] Factory Method: criacao delegada a AlunoFactory
        PessoaFactory factory = new AlunoFactory();
        Aluno aluno = (Aluno) factory.criar(new String[]{nome, cpf, email, matricula});
        secretaria.cadastrarAluno(aluno);
    }

    static void cadastrarProfessorOuCoordenador() {
        System.out.println("\n--- CADASTRAR PROFESSOR / COORDENADOR ---");
        System.out.println("  Tipo: 1 - Professor  |  2 - Coordenador");
        int tipo = lerInt("  Escolha: ");

        System.out.print("  Nome  : "); String nome = sc.nextLine();
        System.out.print("  CPF   : "); String cpf = sc.nextLine();
        System.out.print("  Email : "); String email = sc.nextLine();
        System.out.print("  SIAPE : "); String siape = sc.nextLine();
        System.out.print("  Senha : "); String senha = sc.nextLine();

        String[] dados = {nome, cpf, email, siape, senha};

        // [PADRAO] Factory Method: selecao da fabrica concreta sem if/else extenso
        PessoaFactory factory = (tipo == 2) ? new CoordenadorFactory() : new ProfessorFactory();
        Professor professor = (Professor) factory.criar(dados);
        secretaria.cadastrarProfessor(professor);
    }

    static void lancarNotas() {
        System.out.println("\n--- LANCAR NOTAS ---");
        System.out.println("  Buscar por: 1 - Matricula  |  2 - Nome");
        int opcao = lerInt("  Escolha: ");

        Aluno aluno = null;

        if (opcao == 1) {
            System.out.print("  Matricula: "); String matricula = sc.nextLine();
            aluno = secretaria.buscarAlunoPorMatricula(matricula);
        } else {
            System.out.print("  Nome: "); String nome = sc.nextLine();
            aluno = secretaria.buscarAlunoPorNome(nome);
        }

        if (aluno == null) {
            System.out.println("  [ERRO] Aluno nao encontrado.");
            return;
        }

        System.out.println("  Aluno encontrado: " + aluno.getNome()
                + " (Media atual: " + String.format("%.2f", aluno.getMediaFinal()) + ")");

        int qtd = lerInt("  Quantas notas deseja lancar? ");
        for (int i = 1; i <= qtd; i++) {
            double nota = lerDouble("  Nota " + i + ": ");
            aluno.adicionarNota(nota);
        }

        System.out.printf("  Media final de %s: %.2f%n", aluno.getNome(), aluno.getMediaFinal());
    }

    static void exibirEstatisticas() {
        RelatorioAcademico relatorio = new RelatorioAcademico();
        for (Aluno aluno : secretaria.listarAlunos()) {
            relatorio.adicionarDados(aluno);
        }
        relatorio.exibirMediaGeral();
    }

    static void acessoAdministrativo() {
        System.out.println("\n--- ACESSO ADMINISTRATIVO ---");
        System.out.print("  SIAPE do professor: "); String siape = sc.nextLine();
        System.out.print("  Senha             : "); String senha = sc.nextLine();

        Professor prof = secretaria.buscarProfessorPorSiape(siape);

        if (prof == null) {
            System.out.println("  [ERRO] Professor nao encontrado.");
            return;
        }

        if (prof.login(senha)) {
            System.out.println("  Login realizado com sucesso! Bem-vindo(a), " + prof.getNome() + "!");
            if (prof instanceof Coordenador) {
                System.out.println("  Perfil: COORDENADOR | Horas com bonus: " + prof.getHorasComBonus());
            } else {
                System.out.println("  Perfil: PROFESSOR | Horas: " + prof.getHorasComBonus());
            }
        } else {
            System.out.println("  [ERRO] Senha incorreta. Acesso negado.");
        }
    }

    static int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("  [ERRO] Digite um numero valido.");
            }
        }
    }

    static double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  [ERRO] Digite um numero valido (ex: 8.5).");
            }
        }
    }
}
