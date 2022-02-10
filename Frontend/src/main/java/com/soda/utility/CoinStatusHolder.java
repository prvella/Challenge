package com.soda.utility;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
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
@Component
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CoinStatusHolder {

  private Boolean status;

}
