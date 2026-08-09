# LoveTatianka - Minecraft 26.2 port

This version ports the old 1.x-style Minecraft/Fabric API usage to the Minecraft 26.2 official mappings/API.

Key changes:
- Client command API migrated to `net.fabricmc.fabric.api.client.command.v2`.
- Text API migrated to `Component` / `ChatFormatting`.
- HUD rendering migrated from the old InGameHud mixin/MatrixStack path to Fabric's 26.2 Hud API and `GuiGraphicsExtractor`.
- Config storage uses FabricLoader's config directory.
- Legacy rendering mixins are no longer used.
- Minecraft 26.2 / Java 25 / Loader 0.19.3 / Loom 1.17.11 / Fabric API 0.154.1+26.2 are retained.

The build was not executed to completion in this environment because external Gradle/Maven downloads are unavailable here. GitHub Actions should perform the real build.
