package com.example.uli.mygammelgame.model;

public class GameEntity {

    // generated automatically
    protected final String gameId;

    //
    protected final String type;
    protected final String displayName;

    public GameEntity (String type, String displayName) {
        this.gameId = GameIdGenerator.getInstance().generateId(type);
        this.type = type;
        this.displayName = displayName;
    }

    // GETTERS
    public String getGameId() {
        return gameId;
    }
    public String getType() {
        return type;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String toString() { return "GameEntity id: "+gameId+", type:"+type+", displayname:"+displayName; }

}
