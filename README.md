# backend-tcc

Projeto de TCC do curso de Sistemas de Informação da FIAP.  

Nosso projeto tem o intuito de acompanhar, facilitar e humanizar o atendimento em unidades de pronto-socorro(PS).  

O projeto é um dos serviços oferecidos pela Curare, uma healthtech com foco no mercado B2B que oferece serviços para hospitais, visando facilitar e inovar a saúde de forma simples e barata.  

Para contato (ou apenas spam), envie um e-mail para: <a href="mailto:jonataslima@curaresaude.com"> Jônatas </a>

## Funcionamento

A solução proposta se baseia na ideia em que salas de atendimento chamam os pacientes organizados de acordo com a prioridade, criticidade e pelo tempo em que estão na fila.
 
A criação de um paciente na API é feita usando o minimo de informações possiveis para garantir a privacidade de quem será atendido, precisamos somente: do nome, CPF, se tem prioritaridade, e sua criticidade (caso essa ultima não seja passada, será atribuido automaticamente como baixa prioridade).
Com o intuito de maximar a velocidade de atendimento e registrar quanto tempo cada paciente levou para ser atendido, quando um paciente é criado ou atendido, ela salva a data e hora à fim de gerarmos metrícas de tempo de atendimento.

Para o hospital, ao criar uma sala é necessario apenas passar parametros como: numero da sala, responsavel e especialidade (triagem ou atendimento). O _endpoint_ de atendimento se encarregará de chamar o paciente levando em conta a especialidade da sala.


## Endpoints da aplicação  
Atualmente os endpoints que a aplicação dispõe são:

### Exames:
- POST /new/exame/{cpf}  
Para criar novos exames em um paciente cujo CPF seja {cpf}
- PATCH /exame/{cpf}  
Para substituir os exames em um paciente cujo CPF seja {cpf}

### Medicamentos:
- POST /new/medicamento/{cpf}  
  Para criar novos medicamentos em um paciente cujo CPF seja {cpf}
- PATCH /medicamento/{cpf}  
  Para substituir os medicamentos em um paciente cujo CPF seja {cpf}

### Pacientes:
- POST /new/paciente  
Para criar um novo paciente
- GET /paciente/{cpf}  
Para buscar todas as informaçoes referentes ao paciente cujo CPF seja {cpf}
- PATCH /paciente/{cpf}/criticidade  
Para atualizar a criticidade de um paciente cujo CPF seja {cpf}  
- DELETE /paciente/{id}
Para deletar um paciente cujo ID seja {id}

### Salas:
- POST /new/sala  
Para criar uma nova sala
- GET /salas  
Para buscar informações sobre todas as salas cadastradas no banco de dados
- GET /sala/{numero}  
Para buscar informações referentes a sala cujo numero seja {numero}
- PATCH /sala/{numero}/update  
Para atualizar as informações referentes a sala cujo numero seja {numero}
- DELETE /sala/{numero}  
Para deletar a sala cujo numero seja {numero} do bando de dados
- GET /sala/{numero}/atendimento  
Permite que a sala cujo numero seja {numero} chame o proximo paciente para um atendimento, respeitando a especialidade da sala

### HealthCheck  
- GET /health  
Retorna um HTTP Code 200, se a API estiver Online

---

## Testando Local  

Para fazer os testes em ambiente local:  
- Em ./src/main/resources/application.properties des-comentar tudo o que estiver abaixo de "Local H2" e comentar o que estiver abaixo de "Posgres";  
  (Dessa forma o sistema usará um banco de dados H2 em memória ao invés de tentar se conectar à um Postgres)
- É possivel rodar a aplicação como um container usando o comando "docker-compose up", ou simplesmente executar a aplicação usando a propria IDE.
