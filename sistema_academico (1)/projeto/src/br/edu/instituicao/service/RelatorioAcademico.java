package br.edu.instituicao.service;

import br.edu.instituicao.interfaces.Avaliavel;
import java.util.ArrayList;
import java.util.List;

public class RelatorioAcademico {

    // Conhece apenas Avaliavel — polimorfismo puro
    private List<Avaliavel> dados;

    public RelatorioAcademico() {
        this.dados = new ArrayList<>();
    }

    public void adicionarDados(Avaliavel objeto) {
        dados.add(objeto);
    }

    public void exibirMediaGeral() {
        System.out.println("\n========================================");
        System.out.println("       RELATORIO ACADEMICO              ");
        System.out.println("========================================");

        if (dados.isEmpty()) {
            System.out.println("  Nenhum dado disponivel para calculo.");
            System.out.println("========================================\n");
            return;
        }

        double somaMedias = 0;
        int totalAlunos = dados.size();

        for (Avaliavel avaliavel : dados) {
            somaMedias += avaliavel.getMediaFinal();
        }

        double mediaGeral = somaMedias / totalAlunos;

        System.out.println("  Total de alunos avaliados : " + totalAlunos);
        System.out.printf("  Media Geral da Instituicao: %.2f%n", mediaGeral);
        System.out.println("========================================\n");
    }
}
