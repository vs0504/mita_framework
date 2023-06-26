

package com.mita.dto;

import com.mita.model.SuggestionResultMetaData;
import com.mita.model.SuggestionResultStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SuggestionResultMappingDTO {
  private Long id;
  private Long stepResultId;
  private Long suggestionId;
  private String message;
  private SuggestionResultStatus result;
  private SuggestionResultMetaData metaData;
  private Timestamp createdDate;
  private Timestamp updatedDate;

}
