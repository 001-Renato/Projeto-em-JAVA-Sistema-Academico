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
 * [AULA] Ponto de Entrada (Entry Point): A classe que contém o método 'main' é por onde a execução do programa começa.
 *
 * [PADRAO] Singleton aplicado: Secretaria.getInstance() substitui 'new Secretaria()'.
 * [PADRAO] Factory Method aplicado: AlunoFactory, ProfessorFactory e CoordenadorFactory
 *          substituem os blocos 'new Aluno()', 'new Professor()' e 'new Coordenador()' diretos.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /**
         * [PADRAO] Singleton: obtemos a instância única da Secretaria via ponto de acesso global.
         * Antes: Secretaria secretaria = new Secretaria();  ← criava uma nova instância a cada execução
         * Agora: Secretaria.getInstance()                   ← sempre retorna o mesmo objeto central
         * Isso garante que não existam dois registros paralelos com dados diferentes.
         */
        Secretaria secretaria = Secretaria.getInstance();
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("\n===== SISTEMA ACADEMICO =====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor/Coordenador");
            System.out.println("3. Lancar Notas de Aluno");
            System.out.println("4. Listar Comunidade Academica");
            System.out.println("5. Exibir Estatisticas (Media Geral)");
            System.out.println("6. Acesso Administrativo (Testar Login)");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opcao: ");

            // [AULA] Tratamento de Exceções (Try/Catch): Prevenimos que o programa encerre de forma "feia" (quebre)
            // caso o usuário digite uma letra (ex: 'A') ao invés de um número.
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um numero valido.");
                continue; // [AULA] Volta para o início do laço 'while' em caso de erro.
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpfAluno = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String emailAluno = scanner.nextLine();
                    System.out.print("Matricula: ");
                    String matricula = scanner.nextLine();

                    /**
                     * [PADRAO] Factory Method: a criação do Aluno é delegada à AlunoFactory.
                     * Antes: secretaria.cadastrarPessoa(new Aluno(nomeAluno, cpfAluno, emailAluno, matricula));
                     * Agora: o Main não sabe como Aluno é construído — apenas entrega os dados à fábrica.
                     */
                    PessoaFactory fabricaAluno = new AlunoFactory();
                    Pessoa novoAluno = fabricaAluno.criar(new String[]{nomeAluno, cpfAluno, emailAluno, matricula});
                    secretaria.cadastrarPessoa(novoAluno);
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String nomeProf = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpfProf = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String emailProf = scanner.nextLine();
                    System.out.print("SIAPE: ");
                    String siape = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("E coordenador? (S/N): ");
                    String isCoord = scanner.nextLine();

                    String[] dadosProf = {nomeProf, cpfProf, emailProf, siape, senha};

                    /**
                     * [PADRAO] Factory Method: a escolha da fábrica substitui o bloco if/else com 'new'.
                     * Antes: if (isCoord.equals("S")) { new Coordenador(...) } else { new Professor(...) }
                     * Agora: selecionamos a fábrica correta e ela decide o que instanciar.
                     * Para adicionar um novo tipo (ex: Professor Substituto), basta criar uma nova fábrica
                     * sem alterar este switch.
                     */
                    PessoaFactory fabricaProf = isCoord.equalsIgnoreCase("S")
                            ? new CoordenadorFactory()
                            : new ProfessorFactory();
                    secretaria.cadastrarPessoa(fabricaProf.criar(dadosProf));
                    break;

                case 3:
                    System.out.print("Digite a matricula ou nome do aluno: ");
                    String busca = scanner.nextLine();
                    Aluno alunoEncontrado = secretaria.localizarAluno(busca);

                    // [AULA] Verificação de Nulo: Protege contra o 'NullPointerException' ao tentar acessar métodos num objeto inexistente.
                    if (alunoEncontrado != null) {
                        System.out.print("Digite a nota a ser lancada: ");
                        try {
                            double nota = Double.parseDouble(scanner.nextLine().replace(",", "."));
                            alunoEncontrado.adicionarNota(nota);
                            System.out.println("Nota adicionada com sucesso!");
                        } catch (NumberFormatException e) {
                            System.out.println("Nota invalida. Digite um numero (ex: 8.5).");
                        }
                    } else {
                        System.out.println("Aluno nao localizado.");
                    }
                    break;

                case 4:
                    secretaria.listarComunidade();
                    break;

                case 5:
                    RelatorioAcademico relatorio = new RelatorioAcademico();
                    for (Pessoa p : secretaria.getCadastros()) {
                        // [AULA] Validação de Tipos: Filtramos apenas os Alunos da lista geral, pois
                        // Relatório Acadêmico só aceita classes que implementem 'Avaliavel'.
                        if (p instanceof Aluno) {
                            relatorio.adicionarDados((Aluno) p);
                        }
                    }
                    relatorio.exibirMediaGeral();
                    break;

                case 6:
                    System.out.print("Digite o SIAPE do servidor: ");
                    String siapeBusca = scanner.nextLine();
                    Professor prof = secretaria.localizarAutenticavel(siapeBusca);

                    if (prof != null) {
                        System.out.print("Digite a senha: ");
                        String senhaDigitada = scanner.nextLine();
                        if (prof.login(senhaDigitada)) {
                            System.out.println("Acesso concedido! Bem-vindo, " + prof.getNome());
                            // [AULA] Polimorfismo Dinâmico em Ação: Se a referência 'prof' apontar para um Professor comum,
                            // o cálculo de horas devolve 40. Mas se for um Coordenador, ele aplicará a regra bônus e devolverá 60!
                            if (prof instanceof Coordenador) {
                                System.out.println("Carga horaria com bonus de gestao: " + prof.calcularHorasAtividade(40) + "h");
                            } else {
                                System.out.println("Carga horaria padrao: " + prof.calcularHorasAtividade(40) + "h");
                            }
                        } else {
                            System.out.println("Acesso negado. Senha incorreta.");
                        }
                    } else {
                        System.out.println("Servidor nao localizado.");
                    }
                    break;

                case 7:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
        scanner.close(); // [AULA] Gerenciamento de Recursos: Importante fechar o Scanner para liberar recursos da memória/S.O.
    }
}
