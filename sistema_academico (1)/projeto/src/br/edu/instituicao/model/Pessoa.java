package br.edu.instituicao.model;

// [AULA] Classe Abstrata: Não pode ser instanciada (não podemos fazer 'new Pessoa()').
// Serve como um "molde" ou base para outras classes filhas (Aluno, Professor).
public abstract class Pessoa {

    // [AULA] Encapsulamento: Atributos 'private' só podem ser acessados diretamente de dentro desta própria classe.
    private String nome;
    private String cpf;
    private String email;

    // [AULA] Construtor: Método especial chamado no momento da criação do objeto (new).
    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // [AULA] Getters e Setters: Métodos públicos para acessar ou modificar os atributos privados com segurança.
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // [AULA] Método Abstrato: Não tem corpo (código).
    // Obriga TODAS as classes filhas a implementarem este comportamento obrigatório!
    public abstract String exibirDetalhes();
}
