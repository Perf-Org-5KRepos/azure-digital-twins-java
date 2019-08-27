/**
 * Copyright (c) Microsoft Corporation. Licensed under the MIT License.
 */
package com.microsoft.twins.reflector.model;

import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngressMessage {
  @JsonProperty(value = "id", required = true)
  @NotBlank
  @Size(min = 3, max = 64)
  private String id;

  @JsonProperty("entity-type")
  private String entityType;

  // Supported ADT V1 attributes
  public static final String ATTRIBUTE_V1_STATUS = "status";
  public static final String ATTRIBUTE_V1_TYPE = "type";
  public static final String ATTRIBUTE_V1_SUB_TYPE = "subType";
  public static final String ATTRIBUTE_V1_FRIENDLY_NAME = "friendlyName";
  public static final String ATTRIBUTE_V1_DESCRIPTION = "description";

  @JsonProperty("attributes")
  private Map<String, String> attributes;

  @JsonProperty("properties")
  private List<Property> properties;

  @JsonProperty("relationships")
  private List<Relationship> relationships;

  @JsonProperty(value = "telemetry")
  private Object telemetry;

}
