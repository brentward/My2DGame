package org.brentwardindustries.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            String filePath = "config.txt";
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            // FULL SCREEN
            if (gp.fullScreenOn) {
                bufferedWriter.write("On");
            } else {
                bufferedWriter.write("Off");
            }
            bufferedWriter.newLine();

            // MUSIC VOLUME
            bufferedWriter.write(String.valueOf(gp.music.volumeScale));
            bufferedWriter.newLine();

            // SE VOLUME
            bufferedWriter.write(String.valueOf(gp.se.volumeScale));
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig()  {
        try {
            String filePath = "config.txt";
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String string = bufferedReader.readLine();

            // FULL SCREEN
            if (string != null && string.equals("On")) {
                gp.fullScreenOn = true;
            }
            if (string != null && string.equals("Off")) {
                gp.fullScreenOn = false;
            }

            // MUSIC VOLUME
            string = bufferedReader.readLine();
            if (string != null) {
                gp.music.volumeScale = Integer.parseInt(string);
            }

            // SE VOLUME
            string = bufferedReader.readLine();
            if (string != null) {
                gp.se.volumeScale = Integer.parseInt(string);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

