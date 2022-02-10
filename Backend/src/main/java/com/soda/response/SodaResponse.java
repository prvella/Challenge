package com.soda.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SodaResponse {
  private String name;
  private Integer quantity;
  private List<DespenceResponse> despences;

}
