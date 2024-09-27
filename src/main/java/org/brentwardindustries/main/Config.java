package org.brentwardindustries.main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
    GamePanel gp;
    final String filePath = "config.txt";

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        Properties properties = new Properties();
        properties.setProperty("fullscreen", "" + gp.fullScreenOn);
        properties.setProperty("music.volume", String.valueOf(gp.music.volumeScale));
        properties.setProperty("se.volume", String.valueOf(gp.se.volumeScale));

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            properties.store(fileWriter, "My2DGame config file");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        Properties properties = new Properties();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                properties.load(fileReader);
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String string;
        string = properties.getProperty("fullscreen");
        if (string != null) {
            gp.fullScreenOn = string.equals("true");
        }
        string = properties.getProperty("music.volume");
        if (string != null) {
            gp.music.volumeScale = Integer.parseInt(string);
        }
        string = properties.getProperty("se.volume");
        if (string != null) {
            gp.se.volumeScale = Integer.parseInt(string);
        }
    }
}
