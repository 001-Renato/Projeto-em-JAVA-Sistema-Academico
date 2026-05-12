package br.edu.instituicao.interfaces;

// [AULA] Interface: É como um "contrato". Qualquer classe que assinar este contrato (implementar)
// será rigorosamente obrigada a fornecer uma lógica para os métodos declarados aqui.
public interface Avaliavel {
    // [AULA] Os métodos em uma interface são implicitamente 'public' e 'abstract'.
    double getMediaFinal();
}
