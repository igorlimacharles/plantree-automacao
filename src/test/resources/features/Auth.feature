#language: pt

Funcionalidade: Operações Essenciais de Conta de Usuário
  Como um usuário da API, eu quero garantir que as operações
  críticas de criação, login e edição de contas funcionam corretamente.

  Contexto:
    Dado que a API de contas está operacional

  @create
  Esquema do Cenário: Tentativas de criação de usuário
    Quando eu envio uma requisição POST para "/account/create" com o corpo:
      """
      {
        "name": "<name>",
        "email": "<email>",
        "password": "<password>",
        "confirmPassword": "<confirmPassword>"
      }
      """
    Então eu devo receber um status code <statusCode>
    E o corpo da resposta deve conter a mensagem "<message>"
    E o contrato da resposta deve ser válido para "<schema>"

    Exemplos:
      | name           | email          | password       | confirmPassword | statusCode | message                    | schema                  |
      | Usuario Valido | email_dinamico | Senha@Forte123 | Senha@Forte123  | 200        | Usuario criado com sucesso | create-user-schema.json |
      | Usuario Senha  | email_dinamico | Senha@Forte123 | Senha@Invalida  | 400        | Senhas não conferem        |                         |
      | Usuario Email  | email-invalido | Senha@Forte123 | Senha@Forte123  | 400        | Email inválido             |                         |


  @login
  Cenário: Login com sucesso
    Dado que um usuário existe com o username "usuario.valido" e senha "Senha@Forte123"
    Quando eu envio uma requisição POST para "/account/login" com o corpo:
      """
      {
        "username": "teste1761509442117@exemplo.com",
        "password": "Senha@Forte123"
      }
      """
    Então eu devo receber um status code 200
    E o corpo da resposta deve conter a mensagem "Login realizado com sucesso"
    E o contrato da resposta deve ser válido para "login-schema.json"

  @edit
  Cenário: Editar um usuário que não existe
    Dado que um usuário com o ID 123 não existe
    Quando eu envio uma requisição POST para "/account/edit/123" com o corpo:
      """
      {
        "id": 123,
        "name": "Nome Atualizado",
        "email": "email.novo@exemplo.com",
        "password": "Nova@Senha123"
      }
      """
    Então eu devo receber um status code 404
    E o corpo da resposta deve conter a mensagem "Usuário não encontrado"