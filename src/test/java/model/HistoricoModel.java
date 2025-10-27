package model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoModel {
    @Expose
    private Double latitude;

    @Expose
    private Double longitude;

    @Expose
    private Double raioPlantio;

    @Expose
    private Integer quantidade;

    @Expose
    private String arvoreId;

    @Expose
    private String userId;
}