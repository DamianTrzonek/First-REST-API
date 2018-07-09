package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Trello {
    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trello trello = (Trello) o;
        return board == trello.board &&
                card == trello.card;
    }

    @Override
    public int hashCode() {

        return Objects.hash(board, card);
    }
}
