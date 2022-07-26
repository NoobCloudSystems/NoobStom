package xyz.luccboy.noobstom.config;

import xyz.luccboy.noobstom.NoobStom;
import lombok.Getter;

import java.io.*;

public class ConfigManager {

    @Getter
    private Config config;

    public ConfigManager() {
        final File configFile = new File("config.json");
        if (!configFile.exists()) writeConfig();

        try (final Reader reader = new FileReader("config.json")) {
            this.config = NoobStom.getInstance().getGson().fromJson(reader, Config.class);
        } catch (final  IOException exception) {
            exception.printStackTrace();
        }
    }

    private void writeConfig() {
        try (final FileWriter fileWriter = new FileWriter("config.json")) {
            NoobStom.getInstance().getGson().toJson(new Config(true, ""), fileWriter);
        } catch (final IOException exception) {
            exception.printStackTrace();
        }
    }

}
