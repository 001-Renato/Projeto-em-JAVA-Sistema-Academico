package br.edu.instituicao.factory;

import br.edu.instituicao.model.Pessoa;

/**
 * [PADRAO] Factory Method
 *
 * Motivacao tecnica: Define o contrato para criacao de objetos do tipo Pessoa.
 * Ao retornar a abstração (Pessoa) em vez de implementacoes concretas, esta
 * interface promove a inversao de dependencia (principio DIP do SOLID).
 * Novas categorias de membros (ex: Funcionario, Estagiario) podem ser
 * adicionadas criando novos fabricantes sem modificar o codigo cliente (Main).
 */
public interface PessoaFactory {

    /**
     * Metodo fabrica responsavel por criar e retornar uma instancia de Pessoa.
     * [PADRAO] Factory Method: a decisao de qual subclasse instanciar
     * e delegada para as implementacoes concretas desta interface,
     * desacoplando a criacao do cliente.
     *
     * @param dados array de Strings com os dados necessarios para criacao
     * @return uma instancia concreta de Pessoa
     */
    Pessoa criar(String[] dados);
}
