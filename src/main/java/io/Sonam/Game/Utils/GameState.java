package io.Sonam.Game.Utils;

public enum GameState {

    PRE_GAME(0, true),
    STARTING(1, false),
    IN_GAME(2, false),
    REBOOTING(3, false);

    int id;
    boolean joinable;

    GameState(int id, boolean joinable) {
        this.id = id;
        this.joinable = joinable;
    }

    public int getId() {
        return id;
    }

    public boolean isJoinable() {
        return joinable;
    }

}
