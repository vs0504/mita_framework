

package com.mita.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mita.converter.JSONObjectConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

@Data
@Entity
@Table(name = "default_data_generators")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultDataGenerator {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "function_name")
  private String functionName;

  @Column(name = "file_id")
  private Long fileId;

  @Column(name = "display_name")
  private String displayName;

  @Column(name = "description")
  private String description;

  @Column(name = "arguments")
  private String arguments;

  @ManyToOne
  @Fetch(value = FetchMode.SELECT)
  @JoinColumn(name = "file_id", referencedColumnName = "id", insertable = false, updatable = false)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private DefaultDataGeneratorFile file;

  @Column(name = "created_date")
  @CreationTimestamp
  private Timestamp createdDate;

  @Column(name = "updated_date")
  @UpdateTimestamp
  private Timestamp updatedDate;

  public Map<String, Object> getArguments() {
    if (arguments == null)
      return null;
    return JSONObjectConverter.toJsonObject(arguments).toMap();
  }

//  public JSONObject getMetaData() {
//    return JSONObjectConverter.toJsonObject(metaData);
//  }
//
//  public void setMetaData(JSONObject metaData) {
//    this.metaData = metaData == null ? null : metaData.toString();
//  }
}
