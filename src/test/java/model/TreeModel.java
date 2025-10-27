package model;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeModel {

    @Expose
    private String nomePopular;

    @Expose
    private String nomeCientifico;

    @Expose
    private String descricao;

    @Expose
    private String formulaCarbono;

    @Expose
    private String tipo;
}
