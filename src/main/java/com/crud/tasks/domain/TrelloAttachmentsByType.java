package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloAttachmentsByType {
    @JsonProperty("trello")
    private Trello trello;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrelloAttachmentsByType that = (TrelloAttachmentsByType) o;
        return Objects.equals(trello, that.trello);
    }

    @Override
    public int hashCode() {

        return Objects.hash(trello);
    }
}
