package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(
        int vehicleType,
        String price,
        String brand,
        String model,
        int modelYear,
        String fuel,
        String codeFipe,
        String referenceMonth,
        String fuelAcronym
) {
}
