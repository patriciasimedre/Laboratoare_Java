package ex1;

import com.fasterxml.jackson.annotation.JsonProperty;

// "record"
public record Carte(
        @JsonProperty("titlul") String titlul,
        @JsonProperty("autorul") String autorul,
        @JsonProperty("anul")    int anul
) {}
