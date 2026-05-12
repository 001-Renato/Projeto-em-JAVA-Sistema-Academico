package br.edu.instituicao.factory;

import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Pessoa;

/**
 * [PADRAO] Factory Method — Fabricante concreto de Aluno.
 *
 * Motivacao tecnica: Centraliza e encapsula a logica de instanciacao da classe
 * Aluno. O cliente (Main) nao precisa conhecer os detalhes do construtor nem
 * realizar validacoes de tipo — basta invocar criar() nesta fabrica.
 * Caso o construtor de Aluno mude, apenas esta classe precisa ser alterada.
 */
public class AlunoFactory implements PessoaFactory {

    /**
     * Cria uma instancia de Aluno a partir dos dados fornecidos.
     * [PADRAO] Factory Method: implementacao concreta do metodo fabrica.
     *
     * @param dados String[] com: [0]=nome, [1]=cpf, [2]=email, [3]=matricula
     * @return nova instancia de Aluno
     */
    @Override
    public Pessoa criar(String[] dados) {
        return new Aluno(dados[0], dados[1], dados[2], dados[3]);
    }
}
