package models.character;

import models.relations.RelationshipService;

public class Character {
    protected final RelationshipService relationshipService;

    public Character() {
        this.relationshipService = new RelationshipService(this);
    }

    public RelationshipService getRelationService() {
        return relationshipService;
    }
}