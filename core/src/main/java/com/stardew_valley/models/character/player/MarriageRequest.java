package com.stardew_valley.models.character.player;

import com.stardew_valley.models.data.User;

public class MarriageRequest {
    private User from;

    public MarriageRequest(User from) {
        this.from = from;
    }

    public User getFrom() {
        return from;
    }
}
