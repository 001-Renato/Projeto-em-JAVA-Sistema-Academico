package br.edu.instituicao.service;

import br.edu.instituicao.interfaces.Avaliavel;
import java.util.ArrayList;
import java.util.List;

// [AULA] Baixo Acoplamento: Esta classe não depende diretamente da classe 'Aluno'.
// Ela depende apenas da abstração 'Avaliavel'.
public class RelatorioAcademico {

    // [AULA] Programação Orientada a Interfaces: Recebemos qualquer coisa que seja 'Avaliavel'.
    private List<Avaliavel> avaliaveis;

    public RelatorioAcademico() {
        this.avaliaveis = new ArrayList<>();
    }

    public void adicionarDados(Avaliavel objeto) {
        this.avaliaveis.add(objeto);
    }

    public void exibirMediaGeral() {
        if (avaliaveis.isEmpty()) {
            System.out.println("Nao ha dados suficientes para calcular a media geral.");
            return;
        }

        double somaGeral = 0;
        for (Avaliavel avaliavel : avaliaveis) {
            // [AULA] Polimorfismo via Interface: O método 'getMediaFinal' será chamado
            // na instância concreta (ex: classe Aluno) em tempo de execução.
            somaGeral += avaliavel.getMediaFinal();
        }

        double mediaGeral = somaGeral / avaliaveis.size();
        System.out.printf("Media Geral da Instituicao: %.2f%n", mediaGeral);
    }
}
