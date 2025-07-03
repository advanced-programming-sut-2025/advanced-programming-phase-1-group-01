package com.stardew_valley.models.character;

import com.stardew_valley.models.relations.RelationshipService;

public class Character {
    protected final RelationshipService relationshipService;

    public Character() {
        this.relationshipService = new RelationshipService(this);
    }

    public RelationshipService getRelationService() {
        return relationshipService;
    }
}
