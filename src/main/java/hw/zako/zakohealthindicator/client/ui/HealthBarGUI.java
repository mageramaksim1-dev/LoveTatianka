package hw.zako.zakohealthindicator.client.ui;

import hw.zako.zakohealthindicator.Config;
import hw.zako.zakohealthindicator.util.ColorUtil;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;

public final class HealthBarGUI {
    private static long lastAttack = 0L;
    private static Player player = null;
    private static boolean registered = false;

    private HealthBarGUI() {
    }

    public static void register() {
        if (registered) return;
        registered = true;

        AttackEntityCallback.EVENT.register((attacker, level, hand, entity, hitResult) -> {
            if (!(entity instanceof Player target)) {
                player = null;
                lastAttack = 0L;
                return net.minecraft.world.InteractionResult.PASS;
            }

            player = target;
            lastAttack = System.currentTimeMillis();
            return net.minecraft.world.InteractionResult.PASS;
        });

        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath("lovetatianka", "health_indicator"),
                HealthBarGUI::extract
        );
    }

    private static void extract(GuiGraphicsExtractor graphics, net.minecraft.client.DeltaTracker deltaTracker) {
        Config config = Config.getInstance();
        if (!config.isCrosshair()) return;
        if (player == null) return;
        if (System.currentTimeMillis() - lastAttack > 10_000L) return;

        float health = player.getHealth();
        int healthInt = Math.max(0, (int) health);
        String text = healthInt + "♥";
        int textWidth = Minecraft.getInstance().font.width(text);

        int centerX = graphics.guiWidth() / 2;
        int centerY = graphics.guiHeight() / 2 + 4;
        int scale = health < 10.0f ? 2 : 1;

        var matrices = graphics.pose();
        matrices.pushMatrix();
        matrices.translate(centerX, centerY);
        matrices.scale(scale, scale);

        graphics.text(
                Minecraft.getInstance().font,
                text,
                -textWidth / 2,
                0,
                ColorUtil.getColor(health),
                true
        );

        matrices.popMatrix();
    }
}
