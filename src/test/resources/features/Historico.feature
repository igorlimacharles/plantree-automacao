# language: pt

Funcionalidade: Gerenciamento de Histórico de Atividades
  Como usuário autenticado do sistema
  Eu quero poder visualizar, adicionar e remover registros do meu histórico
  Para manter o controle das minhas atividades

  Cenário: Listar histórico por ID de usuário com sucesso
    Dado que eu tenha um ID de usuário válido
    Quando eu enviar uma requisição GET para "/history/{id}"
    Então o status da resposta deve ser 200
    E o corpo da resposta não deve ser nulo
    E o corpo da resposta deve corresponder ao schema "historico-array-schema.json"

  Cenário: Tentar listar histórico com ID de usuário inválido
    Dado que eu tenha um ID de usuário inválido
    Quando eu enviar uma requisição GET para "/history/{id}"
    Então o status da resposta deve ser 400
    E a resposta deve conter a mensagem "Id inválido"
    E o corpo da resposta deve corresponder ao schema "error-message-only-schema.json"

  Cenário: Criar um novo registro de histórico com sucesso
    Dado que eu tenha os dados para um novo registro de histórico
    Quando eu enviar uma requisição POST para "/history/add" com os dados
    Então o status da resposta deve ser 201
    E a resposta deve conter a mensagem "Histórico criado com sucesso!"
    E o corpo da resposta deve corresponder ao schema "message-response-schema.json"

  Cenário: Tentar criar um registro de histórico com dados incompletos
    Dado que eu tenha dados incompletos para um novo registro de histórico
    Quando eu enviar uma requisição POST para "/history/add" com os dados
    Então o status da resposta deve ser 400
    E a resposta deve conter a mensagem "Preencha todos os campos"
    E o corpo da resposta deve corresponder ao schema "message-response-schema.json"

  Cenário: Deletar um registro de histórico com sucesso
    Dado que eu tenha um ID de histórico válido para exclusão
    Quando eu enviar uma requisição DELETE para "/history/delete/{id}"
    Então o status da resposta deve ser 200
    E a resposta deve conter a mensagem "Histórico deletado com sucesso"
    E o corpo da resposta deve corresponder ao schema "message-response-schema.json"

  Cenário: Tentar deletar um registro de histórico com ID inválido
    Dado que eu tenha um ID de histórico inválido para exclusão
    Quando eu enviar uma requisição DELETE para "/history/delete/{id}"
    Então o status da resposta deve ser 400
    E a resposta deve conter a mensagem "ID inválido para exclusão"
    E o corpo da resposta deve corresponder ao schema "message-response-schema.json"