package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Avaliavel;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa implements Avaliavel {

    private String matricula;
    private List<Double> notas;

    public Aluno(String nome, String cpf, String email, String matricula) {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.notas = new ArrayList<>();
    }

    public void adicionarNota(double nota) {
        if (nota < 0 || nota > 10) {
            System.out.println("  [ERRO] Nota invalida! Deve ser entre 0 e 10.");
            return;
        }
        notas.add(nota);
        System.out.println("  Nota " + nota + " adicionada com sucesso!");
    }

    @Override
    public double getMediaFinal() {
        if (notas.isEmpty()) return 0.0;
        double soma = 0;
        for (double nota : notas) soma += nota;
        return soma / notas.size();
    }

    // Getters e Setters
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public List<Double> getNotas() { return notas; }

    @Override
    public String toString() {
        return "[ALUNO] " + super.toString()
                + " | Matricula: " + matricula
                + " | Media: " + String.format("%.2f", getMediaFinal());
    }
}
