package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Avaliavel;
import java.util.ArrayList;
import java.util.List;

// [AULA] Herança (extends) e Interfaces (implements):
// 'Aluno' herda atributos e métodos de 'Pessoa' e assina um contrato com 'Avaliavel'.
public class Aluno extends Pessoa implements Avaliavel {

    private String matricula;
    private List<Double> notas;

    public Aluno(String nome, String cpf, String email, String matricula) {
        // [AULA] super(): Chama o construtor da classe Pai (Pessoa) para reaproveitar a inicialização.
        super(nome, cpf, email);
        this.matricula = matricula;
        // [AULA] Instanciação de Coleção: Previne o famoso "NullPointerException" ao adicionar notas depois.
        this.notas = new ArrayList<>();
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public void adicionarNota(double nota) {
        // [AULA] Regra de Negócio: Aqui vemos a vantagem do Encapsulamento. A lista de notas é privada,
        // e nós controlamos as regras de adição de dados através de métodos públicos.
        if (nota >= 0 && nota <= 10) {
            this.notas.add(nota);
        } else {
            System.out.println("Nota invalida. Insira um valor entre 0 e 10.");
        }
    }

    // [AULA] @Override (Sobrescrita): Implementação obrigatória do contrato assinado com 'Avaliavel'.
    @Override
    public double getMediaFinal() {
        if (notas.isEmpty()) return 0.0;
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    // [AULA] @Override: Implementação obrigatória do método abstrato exigido pela classe Pai (Pessoa).
    @Override
    public String exibirDetalhes() {
        return String.format("[ALUNO] %s | Matricula: %s | Media Atual: %.2f",
                getNome(), matricula, getMediaFinal());
    }
}
