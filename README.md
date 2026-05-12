# Sistema Acadêmico — POO em Java

## Como Rodar

**Pré-requisito:** Java 11+

```bash
# Compilar
javac -d out -sourcepath src src/br/edu/instituicao/main/Main.java

# Executar
java -cp out br.edu.instituicao.main.Main
```

---

## Design Patterns

**Singleton — `Secretaria`**
Garante uma única instância da secretaria, evitando listas de membros inconsistentes.
```java
Secretaria secretaria = Secretaria.getInstance();
```

**Factory Method — pacote `factory`**
Centraliza a criação de objetos (`Aluno`, `Professor`, `Coordenador`), eliminando `if/else` no `Main`.
```java
PessoaFactory factory = new AlunoFactory();
Pessoa pessoa = factory.criar(dados);
```

---

## Estrutura
```
src/br/edu/instituicao/
├── interfaces/     # Avaliavel, Autenticavel
├── model/          # Pessoa (abstrata), Aluno, Professor, Coordenador
├── factory/        # PessoaFactory, AlunoFactory, ProfessorFactory, CoordenadorFactory
├── service/        # Secretaria (Singleton), RelatorioAcademico
└── main/           # Main.java
```

---

## Conceitos Implementados

| Conceito | Onde |
|---|---|
| Abstração | `Pessoa` — não instanciável diretamente |
| Herança | `Aluno`, `Professor`, `Coordenador` herdam de `Pessoa` |
| Encapsulamento | Atributos `private` com getters/setters |
| Polimorfismo | `RelatorioAcademico` opera sobre `Avaliavel` |
| Singleton | `Secretaria.getInstance()` |
| Factory Method | `AlunoFactory`, `ProfessorFactory`, `CoordenadorFactory` |
