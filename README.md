# loan-shark-service
## Tecnologidas utilizadas:
 - spring 3
 - java 17
 - resilience4j
 - argocd
 - elk
 - istio
 - gifthub actions
 - redis

## Arquitetura
 - praticas clean code
 - arquitetura clean
  
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
```

