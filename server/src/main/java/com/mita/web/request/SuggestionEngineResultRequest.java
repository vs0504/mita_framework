

package com.mita.web.request;

import com.mita.model.SuggestionResultMetaData;
import com.mita.model.SuggestionResultStatus;
import lombok.Data;

@Data
public class SuggestionEngineResultRequest {
  private Integer id;
  private Integer suggestionId;
  private SuggestionResultStatus result;
  private String frame;
  private String message;
  private SuggestionResultMetaData metaData;
}
