package br.edu.instituicao.service;

import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * [PADRAO] Singleton
 *
 * Motivacao tecnica: A Secretaria representa o registro central unico da
 * instituicao. Permitir multiplas instancias causaria inconsistencia de estado
 * (cada instancia teria sua propria lista de membros) e desperdicio de memoria.
 * O Singleton garante que exista exatamente uma instancia compartilhada por
 * toda a aplicacao, acessivel via ponto de acesso global getInstance().
 */
public class Secretaria {

    /** Instancia unica — lazy initialization (criada apenas quando necessaria). */
    private static Secretaria instancia;

    private List<Pessoa> membros;

    /**
     * Construtor privado — impede instanciacao externa via operador new.
     * [PADRAO] Singleton: restricao de instanciacao.
     */
    private Secretaria() {
        this.membros = new ArrayList<>();
    }

    /**
     * Ponto de acesso global a instancia unica da Secretaria.
     * [PADRAO] Singleton: lazy initialization — a instancia e criada
     * somente na primeira chamada, economizando recursos.
     *
     * @return a unica instancia de Secretaria
     */
    public static Secretaria getInstance() {
        if (instancia == null) {
            instancia = new Secretaria();
        }
        return instancia;
    }

    public void cadastrarAluno(Aluno aluno) {
        membros.add(aluno);
        System.out.println("  Aluno \"" + aluno.getNome() + "\" cadastrado com sucesso!");
    }

    public void cadastrarProfessor(Professor professor) {
        membros.add(professor);
        System.out.println("  Professor \"" + professor.getNome() + "\" cadastrado com sucesso!");
    }

    public void listarTodos() {
        System.out.println("\n========================================");
        System.out.println("      COMUNIDADE ACADEMICA              ");
        System.out.println("========================================");
        if (membros.isEmpty()) {
            System.out.println("  Nenhum membro cadastrado ainda.");
        } else {
            for (Pessoa p : membros) {
                System.out.println("  " + p);
            }
        }
        System.out.println("========================================\n");
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Pessoa p : membros) {
            if (p instanceof Aluno) {
                Aluno aluno = (Aluno) p;
                if (aluno.getMatricula().equalsIgnoreCase(matricula)) {
                    return aluno;
                }
            }
        }
        return null;
    }

    public Aluno buscarAlunoPorNome(String nome) {
        for (Pessoa p : membros) {
            if (p instanceof Aluno) {
                Aluno aluno = (Aluno) p;
                if (aluno.getNome().equalsIgnoreCase(nome)) {
                    return aluno;
                }
            }
        }
        return null;
    }

    public Professor buscarProfessorPorSiape(String siape) {
        for (Pessoa p : membros) {
            if (p instanceof Professor) {
                Professor prof = (Professor) p;
                if (prof.getSiape().equalsIgnoreCase(siape)) {
                    return prof;
                }
            }
        }
        return null;
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        for (Pessoa p : membros) {
            if (p instanceof Aluno) alunos.add((Aluno) p);
        }
        return alunos;
    }
}
