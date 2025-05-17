package models.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum BanSellItem {
    AXE, PICKAXE, HOE, WATERING_CAN, SCYTHE,
    COPPER_TRASH_CAN, STEEL_TRASH_CAN, GOLD_TRASH_CAN, IRIDIUM_TRASH_CAN,
    FISHING_ROD, RETURN_SCEPTER, PAN,
    SKULL_KEY, RUSTY_KEY, CLUB_CARD, DWARVISH_TRANSLATION_GUIDE,
    SPECIAL_CHARM, GOLDEN_SCYTHE,
    WEDDING_RING, IRIDIUM_BAND, GLOW_RING, MAGNET_RING,
    JOURNAL_SCRAPS, SECRET_NOTES, MILK_PAIL, SHEARS, MAGNIFYING_GLASS;

    private static final Set<String> bannedNames = Arrays.stream(values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    public static boolean isBanned(String name) {
        return bannedNames.contains(name.toUpperCase().replace(" ", "_"));
    }
}
