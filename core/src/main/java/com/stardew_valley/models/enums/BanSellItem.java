package com.stardew_valley.models.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum BanSellItem {
    AXE("axe"), PICKAXE("pickaxe"), HOE("hoe"), WATERING_CAN("watering can"), SCYTHE("scythe"),
    COPPER_TRASH_CAN("copper trash can"), STEEL_TRASH_CAN("steel trash can"),
    GOLD_TRASH_CAN("gold trash can"), IRIDIUM_TRASH_CAN("iridium trash can"),
    FISHING_ROD("fishing rod"), RETURN_SCEPTER("return scepter"), PAN("pan"),
    SKULL_KEY("skull key"), RUSTY_KEY("rusty key"), CLUB_CARD("club card"),
    DWARVISH_TRANSLATION_GUIDE("dwarvish translation guide"),
    SPECIAL_CHARM("special charm"), GOLDEN_SCYTHE("golden scythe"),
    WEDDING_RING("wedding ring"), IRIDIUM_BAND("iridium band"),
    GLOW_RING("glow ring"), MAGNET_RING("magnet ring"),
    JOURNAL_SCRAPS("journal scraps"), SECRET_NOTES("secret notes"),
    MILK_PAIL("milk pail"), SHEARS("shears"), MAGNIFYING_GLASS("magnifying glass");

    private final String displayName;

    BanSellItem(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    private static final Set<String> bannedNames = Arrays.stream(values())
            .map(item -> item.displayName.toLowerCase())
            .collect(Collectors.toSet());

    public static boolean isBanned(String name) {
        return bannedNames.contains(name.trim().toLowerCase());
    }
}
