package com.stardew_valley.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.stardew_valley.Main;
import com.stardew_valley.controllers.DateTimeController;

public class DateTimeView {
    private final DateTimeController controller;
    private final Batch batch;

    public DateTimeView(DateTimeController controller) {
        this.controller = controller;
        this.batch = Main.getBatch();
    }
}
