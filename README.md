🍔 GetFood – Sistema de Pedidos e Pagamentos (Arquitetura de Microsserviços)
📌 Sobre o Projeto

  O GetFood é um sistema desenvolvido com Spring Boot que simula uma plataforma de pedidos e pagamentos em um modelo de arquitetura de   microsserviços.
  O projeto foi construído com foco em boas práticas de arquitetura, escalabilidade e integração entre serviços independentes.
  
  Este repositório foi criado como estudo prático de Spring Cloud, Eureka Service Discovery e Spring Cloud Gateway, além de práticas modernas de desenvolvimento backend.

⚙️ Arquitetura do Sistema

O sistema é composto pelos seguintes microsserviços:

  📦 Pedidos-MS → Gerencia os pedidos feitos no sistema.
  
  💳 Pagamentos-MS → Responsável pelo processamento dos pagamentos.
  
  🌐 Gateway → API Gateway que centraliza as requisições externas e redireciona para os serviços corretos.
  
  🔍 Eureka Server → Serviço de Service Discovery, permitindo que os microsserviços se registrem e sejam descobertos dinamicamente.


Fluxo simplificado:

  Cliente → Gateway → Eureka → [Pedidos-MS | Pagamentos-MS]

🚀 Tecnologias Utilizadas

  Java 17
  
  Spring Boot 3.4.x
  
  Spring Cloud 2024.0.x
  
  Spring Cloud Gateway (MVC)
  
  Spring Cloud Netflix Eureka
  
  Spring Data JPA + Hibernate
  
  MySQL
  
  Maven
  

⚡ Como Executar o Projeto
Pré-requisitos

  JDK 17+
  
  Maven 3.8+
  
  MySQL rodando localmente
  
  Passos
  

Clone o repositório:

  git clone https://github.com/DantasJulio/Projeto_GetFood.git
  

Configure o banco de dados MySQL para os microsserviços:

  pedidos-ms
  
  pagamentos-ms
  

Ajuste as credenciais em cada application.properties.

Inicie os serviços na seguinte ordem:

  Eureka Server
  
  Gateway
  
  Pedidos-MS
  
  Pagamentos-MS
  
  Acesse o Eureka Dashboard:
  
  http://localhost:8081


Acesse as rotas via Gateway (porta 8082):

  http://localhost:8082/pedidos-ms/pedidos
  
  http://localhost:8082/pagamentos-ms/pagamentos
  

✅ Funcionalidades Implementadas

  Registro e descoberta automática de microsserviços no Eureka
  
  Roteamento dinâmico de requisições via API Gateway
  
  CRUD de Pedidos
  
  CRUD de Pagamentos
  
  Integração entre serviços
  

📈 Próximos Passos

  Implementar autenticação e autorização com Spring Security + JWT
  
  Observabilidade com Spring Boot Actuator + Micrometer
  
  Resiliência de microsserviços com Spring Cloud Circuit Breaker
  
  Containerização com Docker
  

👨‍💻 Autor

Julio Dantas
📍 Desenvolvedor Backend
💼 Foco em Java, Spring Boot e Arquitetura de Microsserviços
