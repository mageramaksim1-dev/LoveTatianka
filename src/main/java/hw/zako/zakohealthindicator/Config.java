package hw.zako.zakohealthindicator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Config {
    private static Config instance;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir();
    private static final Path CONFIG_FILE = CONFIG_DIR.resolve("lovetatianka.json");

    private boolean crosshair = true;

    public boolean isCrosshair() {
        return crosshair;
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public Config() {
        loadConfig();
    }

    public void setCrosshair(boolean crosshair) {
        this.crosshair = crosshair;
        saveConfig();
    }

    public void loadConfig() {
        if (!CONFIG_FILE.toFile().exists()) {
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE.toFile())) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            if (json != null && json.has("crosshair")) {
                crosshair = json.get("crosshair").getAsBoolean();
            }
        } catch (IOException | JsonParseException ignored) {
        }
    }

    public void saveConfig() {
        try {
            java.nio.file.Files.createDirectories(CONFIG_DIR);
            JsonObject json = new JsonObject();
            json.addProperty("crosshair", crosshair);
            try (FileWriter writer = new FileWriter(CONFIG_FILE.toFile())) {
                GSON.toJson(json, writer);
            }
        } catch (IOException ignored) {
        }
    }
}
