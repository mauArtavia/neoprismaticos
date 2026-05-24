package com.martaviadev.neoprismaticos.gui;

import com.martaviadev.neoprismaticos.classes.RPGClass;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

public class ClassSelectionScreen extends Screen {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int PADDING = 4;

    private final Consumer<RPGClass> onClassSelected;
    private RPGClass hoveredClass = null;

    public ClassSelectionScreen(Consumer<RPGClass> onClassSelected) {
        super(Component.literal("Elige tu personaje"));
        this.onClassSelected = onClassSelected;
    }

    @Override
    protected void init() {
        int totalClasses = RPGClass.values().length - 1; // excluye NONE
        int startX = (this.width - BUTTON_WIDTH) / 2;
        int startY = (this.height - (totalClasses * (BUTTON_HEIGHT + PADDING))) / 2;

        int index = 0;
        for (RPGClass rpgClass : RPGClass.values()) {
            if (rpgClass == RPGClass.NONE) continue;

            final RPGClass currentClass = rpgClass;
            int y = startY + index * (BUTTON_HEIGHT + PADDING);

            this.addRenderableWidget(Button.builder(
                            Component.literal(rpgClass.getDisplayName()),
                            btn -> selectClass(currentClass)
                    )
                    .pos(startX, y)
                    .size(BUTTON_WIDTH, BUTTON_HEIGHT)
                    .build());

            index++;
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        // Título
        graphics.drawCenteredString(
                this.font,
                "¿Quién eres?",
                this.width / 2,
                20,
                0xFFFFFF
        );

        // Descripción de la clase bajo el cursor
        if (hoveredClass != null) {
            graphics.drawCenteredString(
                    this.font,
                    hoveredClass.getDescription(),
                    this.width / 2,
                    this.height - 30,
                    0xAAAAAA
            );
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private void selectClass(RPGClass rpgClass) {
        this.onClassSelected.accept(rpgClass);
        this.onClose();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        // El jugador no puede cerrar la pantalla sin elegir
        return false;
    }
}