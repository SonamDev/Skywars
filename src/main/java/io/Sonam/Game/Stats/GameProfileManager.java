package io.Sonam.Game.Stats;

import java.util.HashSet;
import java.util.UUID;

public class GameProfileManager {

    private HashSet<GameProfile> gameProfiles = new HashSet<GameProfile>();

    public HashSet<GameProfile> getGameProfiles() {
        return gameProfiles;
    }

    public GameProfile getGameProfile(UUID uuid) {
        for(GameProfile gameProfile : gameProfiles) {
            if(gameProfile.getUuid().equals(uuid)) {
                return gameProfile;
            }
        }
        return null;
    }

    public void createGameProfile(UUID uuid) {
        GameProfile gameProfile = new GameProfile(uuid);
        gameProfiles.add(gameProfile);
    }

}
