package br.edu.instituicao.factory;

import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;

/**
 * [PADRAO] Factory Method — Fabricante concreto de Professor.
 *
 * Motivacao tecnica: Isola a criacao de Professor do codigo cliente,
 * respeitando o principio de responsabilidade unica (SRP). Se houver
 * necessidade de validacao extra ao criar um professor (ex: verificar
 * unicidade de SIAPE), a logica fica concentrada aqui.
 */
public class ProfessorFactory implements PessoaFactory {

    /**
     * Cria uma instancia de Professor a partir dos dados fornecidos.
     * [PADRAO] Factory Method: implementacao concreta do metodo fabrica.
     *
     * @param dados String[] com: [0]=nome, [1]=cpf, [2]=email, [3]=siape, [4]=senha
     * @return nova instancia de Professor
     */
    @Override
    public Pessoa criar(String[] dados) {
        return new Professor(dados[0], dados[1], dados[2], dados[3], dados[4]);
    }
}
