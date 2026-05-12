package br.edu.instituicao.service;

import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * [PADRAO] Singleton — Secretaria como registro central único.
 *
 * Motivação técnica: A Secretaria representa o registro central da instituição.
 * Permitir múltiplas instâncias causaria inconsistência de estado — cada instância
 * teria sua própria lista de membros separada, e um cadastro feito em um lugar
 * não seria visível em outro. O Singleton garante que exista exatamente uma
 * instância compartilhada por toda a aplicação.
 *
 * O segredo do padrão está em três pontos:
 *   1. Construtor privado — ninguém faz 'new Secretaria()' de fora.
 *   2. Atributo estático privado — guarda a única instância.
 *   3. getInstance() — único ponto de acesso, com lazy initialization.
 */
public class Secretaria {

    /**
     * [PADRAO] Singleton: instância única armazenada como atributo estático privado.
     * É 'static' porque pertence à classe, não a um objeto. É criada apenas uma vez.
     */
    private static Secretaria instancia;

    // [AULA] Polimorfismo com Coleções: Graças à herança, uma lista do tipo 'Pessoa'
    // consegue armazenar 'Alunos', 'Professores' e 'Coordenadores' ao mesmo tempo!
    private List<Pessoa> cadastros;

    /**
     * [PADRAO] Singleton: construtor privado — impede que qualquer código externo
     * crie uma nova Secretaria com 'new Secretaria()'. Essa é a chave do padrão.
     */
    private Secretaria() {
        this.cadastros = new ArrayList<>();
    }

    /**
     * [PADRAO] Singleton: ponto de acesso global à instância única.
     * Lazy initialization — a instância só é criada na primeira chamada,
     * economizando memória caso a classe nunca seja utilizada.
     *
     * @return a única instância de Secretaria existente na aplicação
     */
    public static Secretaria getInstance() {
        if (instancia == null) {
            instancia = new Secretaria();
        }
        return instancia;
    }

    public void cadastrarPessoa(Pessoa pessoa) {
        this.cadastros.add(pessoa);
        System.out.println("Cadastro realizado com sucesso: " + pessoa.getNome());
    }

    public void listarComunidade() {
        if (cadastros.isEmpty()) {
            System.out.println("Nenhum membro cadastrado.");
            return;
        }
        System.out.println("\n--- Membros da Comunidade Academica ---");
        // [AULA] For-Each (Laço): Itera sobre toda a lista de pessoas cadastrada.
        for (Pessoa pessoa : cadastros) {
            // [AULA] Late Binding (Ligação Tardia): O Java sabe qual 'exibirDetalhes()' chamar
            // no momento da execução, dependendo se o objeto é Aluno, Professor ou Coordenador.
            System.out.println(pessoa.exibirDetalhes());
        }
    }

    public Aluno localizarAluno(String busca) {
        for (Pessoa pessoa : cadastros) {
            // [AULA] Operador 'instanceof': Verifica se o objeto atual da iteração é de fato um Aluno.
            if (pessoa instanceof Aluno) {
                // [AULA] Downcasting (Conversão explícita): Dizemos ao compilador: "Eu tenho certeza que essa Pessoa é um Aluno".
                Aluno aluno = (Aluno) pessoa;
                if (aluno.getMatricula().equals(busca) || aluno.getNome().equalsIgnoreCase(busca)) {
                    return aluno;
                }
            }
        }
        return null;
    }

    public Professor localizarAutenticavel(String siape) {
        for (Pessoa pessoa : cadastros) {
            if (pessoa instanceof Professor) {
                Professor prof = (Professor) pessoa;
                if (prof.getSiape().equals(siape)) {
                    return prof;
                }
            }
        }
        return null;
    }

    public List<Pessoa> getCadastros() {
        return cadastros;
    }
}
