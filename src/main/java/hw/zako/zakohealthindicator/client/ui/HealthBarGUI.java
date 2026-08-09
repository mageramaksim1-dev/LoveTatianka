package hw.zako.zakohealthindicator.client.ui;

import hw.zako.zakohealthindicator.Config;
import hw.zako.zakohealthindicator.util.ColorUtil;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;

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
            if (entity.getType() != EntityType.PLAYER || !(entity instanceof Player target)) {
                player = null;
                lastAttack = 0L;
                return net.minecraft.world.InteractionResult.PASS;
            }

            player = target;
            lastAttack = System.currentTimeMillis();
            return net.minecraft.world.InteractionResult.PASS;
        });

        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath("zakohealthindicator", "health_indicator"),
                HealthBarGUI::extract
        );
    }

    private static void extract(GuiGraphicsExtractor graphics, net.minecraft.client.DeltaTracker deltaTracker) {
        Config config = Config.getInstance();
        if (!config.isCrosshair()) return;
        if (player == null) return;
        if (System.currentTimeMillis() - lastAttack > 10_000L) return;

        float health = player.getHealth();
        int healthInt = (int) health;
        String text = healthInt + "";
        int textWidth = Minecraft.getInstance().font.width(text);

        int scale = health < 10.0f ? 2 : 1;
        int x = graphics.guiWidth() / (scale * 2) - textWidth / 2;
        int y = graphics.guiHeight() / (scale * 2) + 4;

        if (scale == 2) {
            var matrices = graphics.pose();
            matrices.pushMatrix();
            matrices.scale(2.0f, 2.0f);
            graphics.text(Minecraft.getInstance().font, text, x / 2, y / 2, ColorUtil.getColor(health), true);
            matrices.popMatrix();
        } else {
            graphics.text(Minecraft.getInstance().font, text, x, y, ColorUtil.getColor(health), true);
        }
    }
}
