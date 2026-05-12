package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Autenticavel;

// [AULA] Herança + Interface: Java não tem herança múltipla de classes,
// mas permite estender UMA classe e implementar VÁRIAS interfaces.
public class Professor extends Pessoa implements Autenticavel {

    private String siape;
    private String senha;

    public Professor(String nome, String cpf, String email, String siape, String senha) {
        super(nome, cpf, email);
        this.siape = siape;
        this.senha = senha;
    }

    public String getSiape() { return siape; }
    public void setSiape(String siape) { this.siape = siape; }

    public void setSenha(String senha) { this.senha = senha; }

    // [AULA] Polimorfismo e Contrato: Cumprindo a regra exigida pela interface 'Autenticavel'.
    @Override
    public boolean login(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    // [AULA] Método da classe: Comportamento específico de professores.
    // Professor comum não tem bônus — retorna as horas exatamente como recebidas.
    public int calcularHorasAtividade(int horasBase) {
        return horasBase;
    }

    // [AULA] Sobrescrita: Implementando o método abstrato herdado de 'Pessoa'.
    @Override
    public String exibirDetalhes() {
        return String.format("[PROFESSOR] %s | SIAPE: %s", getNome(), siape);
    }
}
