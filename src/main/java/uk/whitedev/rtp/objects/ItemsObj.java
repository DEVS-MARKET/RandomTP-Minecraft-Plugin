package uk.whitedev.rtp.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemsObj {
    private final int slot;
    private final int minX;
    private final int maxX;
    private final int minZ;
    private final int maxZ;
    private final String permissions;
    public static List<ItemsObj> ALLITEMS = new ArrayList<>();

    public ItemsObj(int minX, int maxX, int minZ, int maxZ, int slot, String permissions){
        this.minX = minX;
        this.maxX = maxX;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.slot = slot;
        this.permissions = permissions;
    }

    public int getSlot() {
        return slot;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public String getPermissions() {
        return permissions;
    }
}
