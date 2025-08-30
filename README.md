# PLENUS AGENDA MÉDICA

**Transforme a Gestão do seu Negócio e Foque no que Realmente Importa: Seus Pacientes.**

## Introdução

Apresentamos uma plataforma de gestão completa, projetada para profissionais autônomos e MEI que buscam máxima eficiência e organização. Nossa solução centraliza, em um único ambiente digital, todas as ferramentas essenciais para o seu negócio: do agendamento e histórico de atendimentos ao controle financeiro detalhado. O objetivo é unificar sua operação, permitindo que você administre seus clientes, serviços e finanças de forma integrada, liberando seu tempo para focar no crescimento da sua atividade e na qualidade do seu atendimento.

## Modelo Conceitual do Banco de Dados

![Modelo conceitual do banco de dados](resources/ModeloConceitual.drawio.svg)

## Requisitos Funcionais

### Módulo de Agendamento e Consultas

- **RF001:** O sistema deve permitir que o **Administrador** crie, visualize, edite e cancele agendamentos em um calendário.
- **RF002:** O sistema deve permitir que o **Cliente** visualize os horários disponíveis e solicite um novo agendamento.
- **RF003:** O sistema deve permitir que o **Cliente** visualize e cancele seus próprios agendamentos (sujeito a regras de negócio, como antecedência mínima) - **LIMITE DE 1 REAGENDAMENTO GRATUITO**.
- **RF004:** O sistema deve enviar notificações (e-mail ou outro canal) para o **Cliente**, para o **Prestador** e para o **Administrador** na criação, alteração e cancelamento de um agendamento.
- **RF005:** O sistema deve exibir o status de cada agendamento (ex: **Solicitado, Aguardando sinal, Aprovado, Cancelado, Em Andamento, Aguardando Pagamento, Pago**).
- **RF006:** O sistema deve permitir que o **Prestador** visualize e gerencie apenas os agendamentos relacionados à sua própria agenda.
- **RF007:** O sistema deve permitir que o **Prestador** crie, visualize e aprove solicitações de agendamento em sua própria agenda.

### Módulo de Procedimentos e Serviços

- **RF008:** O sistema deve permitir que o **Administrador** cadastre novos procedimentos, definindo nome, descrição, duração padrão e valor.
- **RF009:** O sistema deve permitir que o **Administrador** edite e desative procedimentos existentes.
- **RF010:** O sistema deve associar um ou mais procedimentos a um agendamento no momento da sua criação.

### Módulo de Pacientes/Clientes e Anamnese

- **RF011:** O sistema deve permitir o cadastro de novos pacientes/clientes com informações básicas (nome, contato, etc.).
- **RF012:** O sistema deve armazenar e exibir um histórico de todos os atendimentos realizados para cada paciente.
- **RF013:** O sistema deve permitir ao **Administrador** criar e gerenciar campos customizados para a ficha de anamnese.
- **RF014:** O sistema deve permitir associar um conjunto específico de campos de anamnese a cada tipo de procedimento cadastrado.
- **RF015:** Ao iniciar um atendimento, o sistema deve gerar uma ficha de anamnese dinâmica, contendo os campos definidos para o procedimento selecionado.
- **RF016:** O sistema deve salvar as informações preenchidas na ficha de anamnese e vinculá-las ao histórico do paciente.
- **RF017:** O sistema deve emitir um Termo de consentimento livre e esclarecido para assinatura pelo paciente.
- **RF018:** O sistema deve emitir um Contrato de Prestação de Serviços formalizando a venda do procedimento.

### Módulo Financeiro

- **RF019:** O sistema deve permitir o registro de pagamentos para cada consulta/atendimento realizado.
- **RF020:** O sistema deve permitir a seleção de diferentes formas de pagamento (ex: Dinheiro, Cartão de Crédito, PIX).
- **RF021:** O sistema deve permitir o controle de status de pagamento (ex: Pago, Pendente).
- **RF022:** O sistema deve permitir a inserção de pagamentos parciais para um atendimento.

### Módulo de Relatórios e Análises

- **RF023:** O sistema deve gerar um relatório financeiro com filtros por período (diário, semanal, mensal), exibindo o faturamento total, valores recebidos e valores pendentes.
- **RF024:** O sistema deve gerar um relatório de popularidade de procedimentos, mostrando a quantidade de vezes que cada serviço foi realizado em um determinado período.
- **RF025:** O sistema deve permitir a exportação dos relatórios (ex: para PDF ou CSV).

### Módulo de Acesso e Usuários

- **RF026:** O sistema deve ter um sistema de autenticação seguro (login e senha) para **Administradores**, **Prestadores** e **Clientes**.
- **RF027:** O sistema deve diferenciar os níveis de permissão:
  - **Administrador:** acesso total.
  - **Prestador:** acesso restrito à sua própria agenda (criação/aprovação de agendamentos) e sem acesso a relatórios.
  - **Cliente:** acesso restrito aos seus próprios dados e agendamentos.
- **RF028:** O sistema deve permitir que o **Administrador** crie, edite e gerencie usuários do tipo **Prestador**.

## Sprint Backlog - Módulo de Agendamento (Sprint 1)

**Sprint Goal:** Desenvolver as funcionalidades essenciais para a gestão e visualização de agendamentos, permitindo que administradores criem e gerenciem horários e que clientes possam solicitar, visualizar, cancelar e **reagendar** suas consultas.

### Itens do Backlog da Sprint:

#### História de Usuário 1: Gestão de Agendamentos pelo Administrador

- **Título:** Como **Administrador**, eu quero criar, visualizar, editar e cancelar agendamentos em um calendário para poder gerenciar a agenda de forma completa.
- **Tarefas:**
  - Desenvolver a interface do calendário (visualização diária, semanal, mensal).
  - Implementar o formulário para criação/edição de um novo agendamento (cliente, data, hora, serviço, **status**).
  - Desenvolver a funcionalidade para salvar, atualizar e excluir agendamentos no banco de dados.
  - Exibir os agendamentos no calendário com as informações principais.
- **Critérios de Aceite:**
  - O Administrador deve conseguir adicionar um novo agendamento a um dia e hora específicos.
  - O Administrador deve conseguir abrir um agendamento existente para editar suas informações.
  - O Administrador deve conseguir remover um agendamento do calendário.
  - O Administrador deve poder alterar o status de um agendamento manualmente (ex: de 'Aguardando sinal' para 'Aprovado').

#### História de Usuário 2: Solicitação de Agendamento pelo Cliente

- **Título:** Como **Cliente**, eu quero visualizar os horários disponíveis e solicitar um novo agendamento para marcar uma consulta de forma autônoma.
- **Tarefas:**
  - Criar uma visualização de calendário para o cliente, mostrando apenas os slots de tempo disponíveis.
  - Desenvolver o formulário de solicitação de agendamento.
  - Implementar a lógica para enviar a solicitação para aprovação.
- **Critérios de Aceite:**
  - O Cliente deve poder navegar pelo calendário e ver os horários marcados como "disponíveis".
  - Ao clicar em um horário disponível, o Cliente deve conseguir preencher e enviar uma solicitação de agendamento.
  - Após o envio, o agendamento deve ser criado com o status inicial **"Solicitado"**.

#### História de Usuário 3: Gestão de Agendamentos pelo Cliente

- **Título:** Como **Cliente**, eu quero visualizar, cancelar e **reagendar** meus próprios agendamentos para ter controle sobre minhas consultas.
- **Tarefas:**
  - Criar uma página ou seção "Meus Agendamentos" para o cliente logado.
  - Listar todos os agendamentos futuros e passados do cliente.
  - Implementar o botão de "Cancelar" e a lógica de negócio associada (ex: verificar antecedência mínima).
  - Implementar o botão de "Reagendar" e a lógica para controlar o limite de 1 reagendamento gratuito.
- **Critérios de Aceite:**
  - O Cliente deve conseguir ver uma lista de todos os seus agendamentos com seus respectivos status.
  - O Cliente deve poder cancelar um agendamento futuro, de acordo com as regras de antecedência.
  - Agendamentos cancelados devem ter seu status atualizado para **"Cancelado"**.
  - O Cliente deve poder reagendar um agendamento futuro uma única vez gratuitamente.
  - O sistema deve impedir um segundo reagendamento gratuito ou informar sobre a política de cobrança.
  - O agendamento deve ter um controle (ex: um campo booleano `reagendado`) que é atualizado após o primeiro reagendamento.

#### História de Usuário 4: Exibição de Status do Agendamento

- **Título:** Como **Cliente** e **Administrador**, eu quero visualizar o status de cada agendamento para entender em que etapa ele se encontra.
- **Tarefas:**
  - Definir os diferentes status possíveis no sistema: **`Solicitado`, `Aguardando sinal`, `Aprovado`, `Cancelado`, `Em Andamento`, `Aguardando Pagamento`, `Pago`**.
  - Implementar a lógica para atualizar o status de um agendamento conforme as ações são realizadas.
  - Exibir o status de forma clara nas visualizações de calendário e listas de agendamento.
- **Critérios de Aceite:**
  - O status deve ser visível para o Administrador no calendário de gestão.
  - O status deve ser visível para o Cliente na sua lista de "Meus Agendamentos".
  - O status deve mudar corretamente quando um agendamento é solicitado, aprovado, cancelado, etc.

## Itens para Sprints Futuras (Sugestão de Planejamento)

Os novos requisitos **RF017** e **RF018** são funcionalidades importantes, mas que envolvem uma complexidade maior (geração de documentos, armazenamento, assinatura). Sugere-se criar histórias de usuário específicas para eles e planejá-los para uma Sprint futura, após a conclusão do fluxo principal de agendamento.

- **História de Usuário Sugerida:** *Como Administrador, eu quero que o sistema gere automaticamente um Termo de Consentimento e um Contrato de Serviços quando um agendamento for aprovado, para que eu possa formalizar o atendimento com o paciente.*
- **História de Usuário Sugerida:** *Como Paciente, eu quero visualizar e assinar digitalmente o Termo de Consentimento e o Contrato de Serviços para ter segurança e clareza sobre o procedimento contratado.*