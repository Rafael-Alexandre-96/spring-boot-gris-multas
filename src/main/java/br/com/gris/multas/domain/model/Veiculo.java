package br.com.gris.multas.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("veiculo")
public class Veiculo {

  @Id private String id;
  private String placa;
  private String frota;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY) private RegistroStatus registroStatus = new RegistroStatus();

  public void setPlaca(String placa) {
    this.placa = placa.toUpperCase();
  }

  public void setFrota(String frota) {
    this.frota = frota.toUpperCase();
  }
}
