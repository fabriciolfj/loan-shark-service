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
