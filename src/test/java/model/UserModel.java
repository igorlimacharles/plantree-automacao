package model;

import lombok.Data;

@Data
public class UserModel {
    private String id;
    private String nome;
    private String email;
    // Adicione outros campos que sua API retorna para o usuário
}