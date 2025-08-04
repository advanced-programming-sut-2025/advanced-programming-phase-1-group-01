package com.stardew_valley.models;

import com.stardew_valley.models.enums.AreaType;

public record Area(int row, int col, int height, int width, AreaType type) {
}

