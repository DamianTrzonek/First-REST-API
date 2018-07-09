package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloBadges {
    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private TrelloAttachmentsByType attachmentsByType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrelloBadges that = (TrelloBadges) o;
        return votes == that.votes &&
                Objects.equals(attachmentsByType, that.attachmentsByType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(votes, attachmentsByType);
    }
}
