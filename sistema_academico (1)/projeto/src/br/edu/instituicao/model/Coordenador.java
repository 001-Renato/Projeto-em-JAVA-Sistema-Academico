package br.edu.instituicao.model;

// [AULA] Especialização: Coordenador é um tipo ainda mais específico.
// Ele herda tudo do Professor (que por sua vez já tem tudo da Pessoa).
public class Coordenador extends Professor {

    public Coordenador(String nome, String cpf, String email, String siape, String senha) {
        // [AULA] super(): Repassa os dados para o construtor do Professor.
        super(nome, cpf, email, siape, senha);
    }

    // [AULA] Sobrescrita / Polimorfismo Dinâmico: O coordenador reescreve a regra de horas do Professor.
    @Override
    public int calcularHorasAtividade(int horasBase) {
        int bonusGestao = 20;
        // [AULA] 'super.metodo()': Reutilizamos a lógica existente no Pai (Professor) e somamos nosso bônus!
        return super.calcularHorasAtividade(horasBase) + bonusGestao;
    }

    @Override
    public String exibirDetalhes() {
        return String.format("[COORDENADOR] %s | SIAPE: %s", getNome(), getSiape());
    }
}
