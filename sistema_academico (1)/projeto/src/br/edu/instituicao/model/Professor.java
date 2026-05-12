package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Autenticavel;

public class Professor extends Pessoa implements Autenticavel {

    private String siape;
    private String senha;
    private int horasAtividade;

    public Professor(String nome, String cpf, String email, String siape, String senha) {
        super(nome, cpf, email);
        this.siape = siape;
        this.senha = senha;
        this.horasAtividade = 40; // carga horária padrão
    }

    @Override
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }

    // Bônus do desafio — Professor comum sem bônus
    public int getHorasComBonus() {
        return horasAtividade;
    }

    // Getters e Setters
    public String getSiape() { return siape; }
    public void setSiape(String siape) { this.siape = siape; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public int getHorasAtividade() { return horasAtividade; }
    public void setHorasAtividade(int horasAtividade) { this.horasAtividade = horasAtividade; }

    @Override
    public String toString() {
        return "[PROFESSOR] " + super.toString()
                + " | SIAPE: " + siape
                + " | Horas: " + getHorasComBonus();
    }
}
