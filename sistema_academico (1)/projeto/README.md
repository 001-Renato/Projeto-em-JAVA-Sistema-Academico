# Sistema Acadêmico — POO em Java + Design Patterns

## Como Rodar o Projeto

### Pré-requisito
- Java 11 ou superior (`java -version` para verificar)

### Compilar
```bash
javac -d out -sourcepath src src/br/edu/instituicao/main/Main.java
```

### Executar
```bash
java -cp out br.edu.instituicao.main.Main
```

---

## Por que `Pessoa` foi definida como Abstrata?

A classe `Pessoa` foi definida como abstrata porque ela representa um **conceito genérico** que nunca existirá de forma isolada no sistema. No contexto acadêmico, nenhum indivíduo é simplesmente uma "pessoa" — ele é sempre um *Aluno*, um *Professor* ou um *Coordenador*. Definir `Pessoa` como abstrata impede que o sistema instancie objetos sem identidade real (`new Pessoa()` gera erro de compilação), forçando que toda entidade tenha um papel concreto. Além disso, ela serve como **contrato estrutural**, garantindo que todos os membros possuam obrigatoriamente nome, CPF e e-mail, sem duplicar esse código nas subclasses.

---

## Design Patterns Implementados

### 1. Singleton — `Secretaria`
**Problema resolvido:** múltiplas instâncias de `Secretaria` causariam listas de membros separadas e inconsistência de estado.

**Como foi implementado:**
- Construtor tornado `private`
- Atributo estático `private static Secretaria instancia`
- Método `public static Secretaria getInstance()` com lazy initialization
- `Main` usa `Secretaria.getInstance()` em vez de `new Secretaria()`

```java
// Antes
static Secretaria secretaria = new Secretaria();

// Depois
static Secretaria secretaria = Secretaria.getInstance();
```

### 2. Factory Method — pacote `br.edu.instituicao.factory`
**Problema resolvido:** a lógica de criação de objetos estava espalhada no `Main` com blocos `if/else` que violavam o princípio de responsabilidade única.

**Como foi implementado:**
- Interface `PessoaFactory` com método `Pessoa criar(String[] dados)`
- Três fábricas concretas: `AlunoFactory`, `ProfessorFactory`, `CoordenadorFactory`
- `Main` seleciona a fábrica e recebe a abstração `Pessoa`

```java
// Antes
if (tipo == 2) {
    Coordenador coord = new Coordenador(nome, cpf, email, siape, senha);
} else {
    Professor prof = new Professor(nome, cpf, email, siape, senha);
}

// Depois
PessoaFactory factory = (tipo == 2) ? new CoordenadorFactory() : new ProfessorFactory();
Professor professor = (Professor) factory.criar(dados);
```

---

## Estrutura do Projeto

```
src/
└── br/edu/instituicao/
    ├── interfaces/
    │   ├── Avaliavel.java
    │   └── Autenticavel.java
    ├── model/
    │   ├── Pessoa.java        (abstrata)
    │   ├── Aluno.java
    │   ├── Professor.java
    │   └── Coordenador.java
    ├── factory/               ← NOVO (Factory Method)
    │   ├── PessoaFactory.java
    │   ├── AlunoFactory.java
    │   ├── ProfessorFactory.java
    │   └── CoordenadorFactory.java
    ├── service/
    │   ├── Secretaria.java    ← REFATORADO (Singleton)
    │   └── RelatorioAcademico.java
    └── main/
        └── Main.java          ← REFATORADO (usa Singleton + Factory)
```

## Exemplo de Saída do Console

```
╔══════════════════════════════════════╗
║   SISTEMA ACADEMICO - POO em Java    ║
╚══════════════════════════════════════╝

----------------------------------------
  1. Cadastrar Aluno
  2. Cadastrar Professor / Coordenador
  3. Lancar Notas de Aluno
  4. Listar Comunidade Academica
  5. Exibir Estatisticas (Media Geral)
  6. Acesso Administrativo (Login)
  7. Sair
----------------------------------------
Escolha uma opcao: 1

--- CADASTRAR ALUNO ---
  Nome      : Maria Silva
  CPF       : 111.222.333-44
  Email     : maria@email.com
  Matricula : 2024001
  Aluno "Maria Silva" cadastrado com sucesso!
```

## Conceitos Avaliados

| Conceito | Onde está |
|---|---|
| Abstração | `Pessoa.java` — não pode ser instanciada |
| Herança | `Aluno`, `Professor`, `Coordenador` herdam de `Pessoa` |
| Encapsulamento | Todos os atributos são `private` com getters/setters |
| Polimorfismo | `RelatorioAcademico` usa `Avaliavel`; `getHorasComBonus()` em `Coordenador` |
| Interfaces | `Avaliavel`, `Autenticavel`, `PessoaFactory` |
| Estruturas Dinâmicas | `ArrayList<Pessoa>` na `Secretaria` |
| **Singleton** | `Secretaria.getInstance()` — instância única garantida |
| **Factory Method** | `AlunoFactory`, `ProfessorFactory`, `CoordenadorFactory` |
| Bônus | `Coordenador` sobrescreve `getHorasComBonus()` com +20h |
