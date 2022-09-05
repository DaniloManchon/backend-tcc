# backend-tcc

Projeto de TCC do curso de Sistemas de Informação da FIAP.  

Nosso projeto tem o intuito de acompanhar, facilitar e humanizar o atendimento em unidades de pronto-socorro(PS).  

O projeto é um dos serviços oferecidos pela Curare, uma healthtech com foco no mercado B2B que oferece serviços para hospitais, visando facilitar e inovar a saúde de forma simples e barata.  

Para contato (ou apenas spam), envie um e-mail para: <a href="mailto:jonataslima@curaresaude.com"> Jônatas </a>

---

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
Permite que a sala cujo numero seja {numero} chame o proximo paciente para um atendimento, respeitando qual a especialidade da sala