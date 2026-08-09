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


## LoveTatianka changes
- Mod ID changed from `zakohealthindicator` to `lovetatianka`.
- Added `assets/lovetatianka/icon.png` and registered it in `fabric.mod.json`.
- Health indicator is centered using the screen center even when the value is below 10 and rendered at 2x scale.
- Config filename changed to `lovetatianka.json`.
