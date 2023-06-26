

package com.mita.security.api;

import com.mita.model.AgentType;
import com.mita.model.AuthenticationType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class APIToken {
  private final String subject;
  private final AgentType agentType;
  private final String serverUuid;
  private AuthenticationType authenticationType;
}
