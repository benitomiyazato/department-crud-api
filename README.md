# department-crud-api
Pequeno projeto de CRUD feito com o objetivo de aprender sobre Spring Framework

Esse foi o primeiro projeto que construi utilizando o ecossistema Spring e me serviu demais para fixar melhor conceitos como Inversão de Controle e Injeção de Dependências. 

Outro aprendizado interessante foi a criação de testes unitários para todas as camadas do projeto, fazendo uso do JUnit, Mockito, TestEntityManager e @DataJpaTest (para testes em repositórios sem afetar o banco de dados) e MockMvc e @WebMvcTest (para testar os endpoints feitos pela camada de controller do projeto). Também trabalhei com Lombok pra reduzir o boilerplate do projeto.

Trata-se de um simples projeto onde é possível cadastrar, consultar, modificar e deletar departamentos através de endpoints construídos com o Spring MVC, que acessei através do Postman. Inicialmente, o banco de dados utilizado foi em memória (com o h2). Mas, posteriormente fiz a conexão com o banco de dados MySQL através do Spring Data JPA.

