

package com.mita.agent.request;

import com.mita.automator.actions.mobile.MobileElement;
import lombok.Data;

@Data
public class SendKeysRequest {
  MobileElement mobileElement;
  String keys;
}
