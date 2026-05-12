package br.edu.instituicao.factory;

import br.edu.instituicao.model.Coordenador;
import br.edu.instituicao.model.Pessoa;

/**
 * [PADRAO] Factory Method — Fabricante concreto de Coordenador.
 *
 * Motivacao tecnica: Encapsula a criacao de Coordenador, um subtipo de
 * Professor com regras especificas (bonus de horas). O cliente nao precisa
 * conhecer a hierarquia de herança para instanciar o tipo correto —
 * basta selecionar a fabrica adequada.
 */
public class CoordenadorFactory implements PessoaFactory {

    /**
     * Cria uma instancia de Coordenador a partir dos dados fornecidos.
     * [PADRAO] Factory Method: implementacao concreta do metodo fabrica.
     *
     * @param dados String[] com: [0]=nome, [1]=cpf, [2]=email, [3]=siape, [4]=senha
     * @return nova instancia de Coordenador
     */
    @Override
    public Pessoa criar(String[] dados) {
        return new Coordenador(dados[0], dados[1], dados[2], dados[3], dados[4]);
    }
}
