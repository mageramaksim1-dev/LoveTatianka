package hw.zako.zakohealthindicator.client;

import hw.zako.zakohealthindicator.Config;
import hw.zako.zakohealthindicator.client.ui.HealthBarGUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

public class ZakoHealthIndicatorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Config config = Config.getInstance();

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, buildContext) -> {
            dispatcher.register(ClientCommands.literal("crosshairhealth")
                    .executes(context -> {
                        config.setCrosshair(!config.isCrosshair());
                        context.getSource().sendFeedback(
                                Component.literal("Ok: " + config.isCrosshair())
                                        .withStyle(ChatFormatting.AQUA)
                        );
                        return 1;
                    }));
        });

        HealthBarGUI.register();
    }
}
