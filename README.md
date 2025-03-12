# loan-shark-service
## Tecnologidas utilizadas:
 - spring 3
   - aop
   - spring data
   - web
   - flyway
   - circuit breaker
   - jacoco
   - checkstyle
   - test containers (https://testcontainers.com/guides/testing-spring-boot-rest-api-using-testcontainers/)
 - java 17
 - resilience4j
 - argocd 
 - elk
 - istio
 - gifthub actions
 - redis
 - openapi (/openapi/swagger-ui.html)

## Arquitetura
 - praticas clean code
   - multiplos dominios
   - comunicação entro os dominios e via eventos ou um orquestrador (que chama os 2 contextos ou usecases)
 - arquitetura clean
 - arquitetura em componentes
   - cada pacote possui uma api que expões suas funcionalidades
   - o mundo externo desconheci os detalhes de cada pacote
   - temos o pacote pai com sua api, e subpacotes com os detalhes, que implementa a interface(api) pai
   - dentro do pacote filho eles podem se comunicar, mas ninguem fora do pacote pai ou seja, subpacote de outro pacote pai, podem acessá-los
```
Arquitetura baseada em componentes, pode ser resumido em quatro regras simples:
- Um componente tem um namespace dedicado para ser endereçável.
- Um componente possui uma API dedicada e componentes internos.
- A API de um componente pode ser chamada externamente, mas seus componentes internos não.
- Um componente pode conter subcomponentes como parte de seus componentes internos.
```

## Java
- uso do java21 
- uso do zgc generational (-XX:+UseZGC -XX:+ZGenerational)
  
## Explicação de algumas propriedades:
```
server.shutdown: graceful - Define que a parada do servidor será feita de forma graceful, aguardando requisições terminarem.
server.tomcat.connection-timeout - Timeout máximo para estabelecimento de novas conexões TCP. Padrão não informado é 20s.
server.tomcat.keep-alive-timeout - Timeout máximo que uma conexão TCP permanece aberta após a requisição ser atendida. Padrão é não informado.
server.tomcat.accept-count - Quantidade máxima de conexões TCP pendentes na fila. Padrão é 100.
server.tomcat.threads.max - Número máximo de threads do servidor Tomcat. Padrão é número de processadores * 2.
server.tomcat.threads.min-spare - Número mínimo de threads Tomcat mantidos na pool mesmo sem uso. Padrão é 10.

##########

A propriedade lifecycle.timeout-per-shutdown-phase define o tempo máximo de espera para cada fase do processo de desligamento da aplicação.

Por padrão, o Spring Boot aguarda 30 segundos em cada fase de shutdown antes de prosseguir para a próxima.

Definindo esse valor como 15s, estamos dizendo que o Spring Boot deve esperar no máximo 15 segundos em cada fase do shutdown, como:

AplicaçãoContextShutdownHook
RefreshScope destruction callbacks
LiveBeansView destruction callbacks
Destruction aware beans close callbacks
ApplicationListener destruction callbacks
Se qualquer fase exceder 15s, o Spring Boot irá forçar o encerramento para aquela fase e prosseguir para a próxima.

Isso evita que o processo de shutdown como um todo demore muito por conta de alguma fase lentista ou bloqueada.

É uma forma de customizar e acelerar o shutdown graceful da aplicação Spring Boot conforme necessidade.


###########
A propriedade hikari.maximum-pool-size define o tamanho máximo do pool de conexões com o banco de dados. O padrão é 10.
A propriedade hikari.connection-timeout define o tempo máximo em milissegundos que o Hikari irá aguardar para estabelecer uma nova conexão com o banco antes de lançar erro. O padrão é 30000 (30 segundos).
Já a propriedade hikari.idle-timeout define o tempo máximo em milissegundos que uma conexão pode ficar ociosa antes de ser fechada. O padrão é 600000 (10 minutos).

spring.main.keep-alive=true faz com que o processo da JVM continue vivo após criar o contexto Spring, ao invés de finalizar logo em seguida.
```

## Ack manual
- neste projeto confirmamos o recebimento das mensagens manualmente, para isso temos que prover um ConcurrentKafkaListenerContainer
- setando ack para manual, carregando o consumidor (junto com as propriedades do application.yml)

## Métricas de Qualidade de Código

As seguintes métricas foram calculadas para avaliar a qualidade do código:

### Complexidade Ciclomática

A complexidade ciclomática mede a quantidade de caminhos independentes através do código e indica a complexidade do fluxo de controle.

| Classe                           | Complexidade Ciclomática | Interpretação |
|----------------------------------|--------------------------|---------------|
| LoanPersistProviderImpl          | 6                        | Boa           |
| Risk                             | 18                       | Média         |
| RiskAnalysisCoordinatorUseCaseImpl | 1                     | Boa           |
| LoanController                   | 1                        | Boa           |
| LoanListener                     | 3                        | Boa           |

**Média do projeto: 5,80** - Indica uma boa complexidade geral.

### Distância da Sequência Principal (DSP)

Esta métrica mede o quão longe um componente está de um equilíbrio ideal entre abstração e instabilidade.

| Classe                           | Distância da Sequência Principal | Interpretação |
|----------------------------------|----------------------------------|---------------|
| LoanPersistProviderImpl          | 0,05                             | Próximo da sequência principal |
| Risk                             | 0,18                             | Próximo da sequência principal |
| RiskAnalysisCoordinatorUseCaseImpl | 0,24                          | Distância moderada |
| LoanController                   | 0,12                             | Próximo da sequência principal |
| LoanListener                     | 0,21                             | Distância moderada |

**Média do projeto: 0,16** - Indica que, em geral, os componentes estão próximos da sequência principal.

### LCOM (Lack of Cohesion of Methods)

LCOM mede a coesão dos métodos de uma classe. Valores mais baixos indicam maior coesão.

| Classe                           | LCOM | Interpretação |
|----------------------------------|------|---------------|
| LoanPersistProviderImpl          | 1    | Alta coesão   |
| Risk                             | 26   | Baixa coesão  |
| RiskAnalysisCoordinatorUseCaseImpl | 0  | Alta coesão   |
| LoanController                   | 0    | Alta coesão   |
| LoanListener                     | 0    | Alta coesão   |

**Média do projeto: 5,40** - O valor médio é afetado pela classe Risk, que apresenta baixa coesão.

## Análise de Qualidade e Recomendações

### Pontos Fortes
- **Arquitetura Clean e Bem Definida**: O projeto demonstra uma clara separação de responsabilidades.
- **Baixa Complexidade Ciclomática**: A maioria das classes apresenta boa complexidade, facilitando testes e manutenção.
- **Alta Coesão**: A maioria das classes possui alta coesão, demonstrando um bom encapsulamento das responsabilidades.
- **Proximidade à Sequência Principal**: Os componentes estão geralmente bem equilibrados em termos de abstração e instabilidade.

### Áreas de Melhoria
- **Refatorar a classe Risk**: Esta classe apresenta uma complexidade média (18) e baixa coesão (LCOM=26). Considera-se dividir esta classe em componentes menores e mais focados.
- **Revisar Componentes com Distância Moderada da DSP**: Classes como RiskAnalysisCoordinatorUseCaseImpl e LoanListener podem se beneficiar de uma revisão em suas dependências.

## Connascência no Projeto

### O que é Connascência?

Connascência (Connascence) é um conceito em engenharia de software que mede a interdependência entre partes de um programa. O termo foi introduzido por Meilir Page-Jones e é uma forma de avaliar o acoplamento entre componentes de software.

Connascência existe quando duas entidades de software são tão interdependentes que uma mudança em uma delas necessitaria de uma mudança correspondente na outra para manter a correção do sistema. Quanto maior a connascência, mais forte é o acoplamento.

### Tipos de Connascência Encontrados no Projeto

#### 1. Connascência de Nome (CoN)

**Exemplos no projeto:**
- A classe `LoanPersistProviderImpl` deve implementar as interfaces com métodos nomeados corretamente (por exemplo, `process`).
- O projeto faz uso extensivo de injeção de dependência onde nomes de beans devem corresponder.

**Grau:** Alto, praticamente todas as classes apresentam este tipo.  
**Localidade:** Boa, geralmente entre componentes relacionados.

#### 2. Connascência de Tipo (CoT)

**Exemplos no projeto:**
- `Risk` e outras entidades do domínio que são passadas entre camadas.
- DTOs e entidades de domínio que precisam ser convertidos.

**Grau:** Médio, presente principalmente nas camadas de adaptadores.  
**Localidade:** Moderada, ocorre entre camadas diferentes.

#### 3. Connascência de Significado (CoM)

**Exemplos no projeto:**
- Enums como `StatusLoanVO` ou `StatusRiskVO` onde valores específicos têm significados compartilhados.
- Constantes como `DAYS_DEFAULT` em `Constants.java`.

**Grau:** Baixo a médio.  
**Localidade:** Variável, algumas ocorrências entre componentes distantes.

#### 4. Connascência de Valores (CoV)

**Exemplos no projeto:**
- A classe `Risk` quando calcula e avalia o score de risco.
- Propriedades como `score.approved` configuradas no `application.yml`.

**Grau:** Médio.  
**Localidade:** Média, principalmente entre componentes de um mesmo domínio.

#### 5. Connascência de Identidade (CoI)

**Exemplos no projeto:**
- Relacionamentos JPA entre entidades (por exemplo, `LoanData` e `CustomerData`).
- Código do empréstimo usado em várias partes do sistema para identificar o mesmo empréstimo.

**Grau:** Médio.  
**Localidade:** Variável, pode ocorrer entre componentes distantes (especialmente para identificadores).

### Componentes com Alta Connascência

1. **Classe Risk**
   - Alta connascência de valores (CoV) com várias outras classes.
   - Alta connascência de significado (CoM) devido às várias regras de negócio.
   - Isso está alinhado com a métrica LCOM alta que identificamos anteriormente.

2. **RiskAnalysisCoordinatorUseCaseImpl**
   - Alta connascência de execução (CoE) com os processors de risco.
   - Connascência de identidade (CoI) com a entidade Risk.

3. **LoanPersistProviderImpl**
   - Alta connascência de nome (CoN) devido à implementação de múltiplas interfaces.
   - Connascência de valores (CoV) e identidade (CoI) com outras partes do sistema.

## Análise de Qualidade e Recomendações

### Pontos Fortes
- **Arquitetura Clean e Bem Definida**: O projeto demonstra uma clara separação de responsabilidades.
- **Baixa Complexidade Ciclomática**: A maioria das classes apresenta boa complexidade, facilitando testes e manutenção.
- **Alta Coesão**: A maioria das classes possui alta coesão, demonstrando um bom encapsulamento das responsabilidades.
- **Proximidade à Sequência Principal**: Os componentes estão geralmente bem equilibrados em termos de abstração e instabilidade.

### Áreas de Melhoria

1. **Refatorar a classe Risk**
   - Dividir em classes menores com responsabilidades mais específicas.
   - Pode-se criar uma classe para avaliar o risco de idade, outra para avaliar o risco de comprometimento salarial, etc.
   - Isso reduziria a connascência de valores (CoV), de significado (CoM) e melhoraria a coesão (LCOM).

2. **Utilizar Padrões de Design**
   - O uso do padrão Strategy para as diferentes análises de risco pode reduzir a connascência de execução (CoE).
   - Considerar o uso de um Builder mais elaborado para Risk para reduzir a connascência de posição (CoP).

3. **Revisar Componentes com Distância Moderada da DSP**
   - Classes como RiskAnalysisCoordinatorUseCaseImpl e LoanListener podem se beneficiar de uma revisão em suas dependências.

4. **Aumentar o Encapsulamento**
   - Revisar a necessidade de alguns Value Objects (VOs) expostos.
   - Reduzir a exposição de atributos internos quando possível.

5. **Melhorar a Documentação**
   - Documentar melhor a connascência de significado (CoM) onde não pode ser evitada.
   - Usar anotações mais específicas para esclarecer a connascência entre componentes.

## Conclusão

No geral, o projeto apresenta boas métricas de qualidade de código, com apenas algumas áreas específicas que poderiam se beneficiar de refatoração para melhorar a manutenibilidade e testabilidade. A arquitetura limpa e baseada em componentes contribui para manter um bom nível de acoplamento e coesão na maior parte do sistema.

A connascência não é algo a ser completamente eliminado, mas sim gerenciado. Manter alta connascência dentro de um componente e baixa connascência entre componentes é o ideal, e o projeto parece seguir essa diretriz em grande parte de sua estrutura.
