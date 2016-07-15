package io.Sonam.Game.Stats;

import java.util.UUID;

public class GameProfile {

    private UUID uuid;
    private int kills = 0;
    private int coins = 0;

    public GameProfile(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        kills++;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins = this.coins + coins;
    }

}
