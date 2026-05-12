package br.edu.instituicao.interfaces;

// [AULA] Interface: Isola o comportamento de "Autenticação".
// Isso permite que no futuro até mesmo um "Aluno" possa fazer login se quisermos, bastando implementar esta interface.
public interface Autenticavel {
    boolean login(String senha);
}
