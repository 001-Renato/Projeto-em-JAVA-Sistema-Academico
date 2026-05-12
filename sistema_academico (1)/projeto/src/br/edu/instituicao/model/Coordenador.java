package br.edu.instituicao.model;

public class Coordenador extends Professor {

    private static final int BONUS_HORAS = 20;

    public Coordenador(String nome, String cpf, String email, String siape, String senha) {
        super(nome, cpf, email, siape, senha);
    }

    // Polimorfismo: sobrescreve o método do Professor com bônus (Desafio Bônus)
    @Override
    public int getHorasComBonus() {
        return getHorasAtividade() + BONUS_HORAS;
    }

    @Override
    public String toString() {
        return "[COORDENADOR] " + getNome()
                + " | CPF: " + getCpf()
                + " | Email: " + getEmail()
                + " | SIAPE: " + getSiape()
                + " | Horas (com bonus): " + getHorasComBonus();
    }
}
