package br.com.malikoski.github.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubResponse implements Serializable {
      private String  extension;
      private Integer count;
      private Long lines;
      private Long bytes;
}
