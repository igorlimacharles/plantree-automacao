# language: pt
Funcionalidade: Gerenciamento de Árvores
  Como um usuário da API
  Eu quero criar, consultar e deletar registros de árvores
  Para manter o inventário de árvores atualizado.

  # ----------------------------------------
  # Cenários de Criação (POST /arvore/create)
  # ----------------------------------------

  Cenário: Criação de uma nova árvore com sucesso (Caminho Feliz)
    Dado que o usuário informa os seguintes dados válidos para uma nova árvore:
      | campo            | valor                |
      | nomePopular      | Ipê Amarelo          |
      | nomeCientifico   | Handroanthus albus   |
      | descricao        | Árvore de grande porte |
      | formulaCarbono   | C20H22O9             |
      | tipo             | Nativa               |
    Quando o usuário envia uma requisição POST para "/arvore/create" com esses dados
    Então o status da resposta deve ser 201
    E a resposta deve conter a mensagem "Árvore criada com sucesso"
    E a resposta deve conter o campo "success" com valor "true"

  Cenário: Tentativa de criação de árvore com campos obrigatórios vazios (Caminho de Falha)
    Dado que o usuário informa dados incompletos para uma nova árvore:
      | campo            | valor                |
      | nomePopular      |                      |
      | nomeCientifico   | Handroanthus albus   |
      | descricao        | Árvore de grande porte |
      | formulaCarbono   | C20H22O9             |
      | tipo             | Nativa               |
    Quando o usuário envia uma requisição POST para "/arvore/create" com esses dados
    Então o status da resposta deve ser 400
    E a resposta deve conter a mensagem "Preencha todos os campos"
    E a resposta deve conter o campo "success" com valor "false"

  # ----------------------------------------------------
  # Cenários de Busca por ID (GET /arvore/search/{id})
  # ----------------------------------------------------

  Cenário: Buscar uma árvore por um ID existente (Caminho Feliz)
    Dado que existe uma árvore cadastrada no sistema com o ID 5
    Quando o usuário envia uma requisição GET para "/arvore/search/5"
    Então o status da resposta deve ser 200

  Cenário: Buscar uma árvore por um ID inexistente (Caminho de Falha)
    Dado que não existe uma árvore cadastrada no sistema com o ID 999
    Quando o usuário envia uma requisição GET para "/arvore/search/999"
    Então o status da resposta deve ser 404
    E a resposta deve conter a mensagem "Árvore não encontrada"

  Cenário: Buscar uma árvore com um ID inválido (Caminho de Falha)
    Dado que o usuário informa um ID inválido, como 0
    Quando o usuário envia uma requisição GET para "/arvore/search/0"
    Então o status da resposta deve ser 400
    E a resposta deve conter a mensagem "Informe um ID válido"