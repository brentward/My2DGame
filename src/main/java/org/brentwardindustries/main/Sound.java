package org.brentwardindustries.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    public int soundIndex = -1;
    FloatControl floatControl;
    int volumeScale = 3;
    float volume;

    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
        soundURL[5] = getClass().getResource("/sounds/hitmonster.wav");
        soundURL[6] = getClass().getResource("/sounds/receivedamage.wav");
        soundURL[7] = getClass().getResource("/sounds/swingweapon.wav");
        soundURL[8] = getClass().getResource("/sounds/levelup.wav");
        soundURL[9] = getClass().getResource("/sounds/cursor.wav");
        soundURL[10] = getClass().getResource("/sounds/burning.wav");
        soundURL[11] = getClass().getResource("/sounds/cuttree.wav");
        soundURL[12] = getClass().getResource("/sounds/gameover.wav");
        soundURL[13] = getClass().getResource("/sounds/stairs.wav");
        soundURL[14] = getClass().getResource("/sounds/sleep.wav");
        soundURL[15] = getClass().getResource("/sounds/blocked.wav");
        soundURL[16] = getClass().getResource("/sounds/parry.wav");
        soundURL[17] = getClass().getResource("/sounds/speak.wav");
        soundURL[18] = getClass().getResource("/sounds/Merchant.wav");
        soundURL[19] = getClass().getResource("/sounds/Dungeon.wav");
        soundURL[20] = getClass().getResource("/sounds/chipwall.wav");
        soundURL[21] = getClass().getResource("/sounds/dooropen.wav");
        soundURL[22] = getClass().getResource("/sounds/FinalBattle.wav");
        soundURL[29] = getClass().getResource("/sounds/sams-song.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        soundIndex = i;
    }

    public void play() {
        if (soundIndex >= 0) {
            clip.start();
        }
    }

    public void playLoop() {
        if (soundIndex >= 0) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (soundIndex >= 0) {
            clip.stop();
        }
    }

    public void end() {
        stop();
        soundIndex = -1;
    }

    public void checkVolue() {
        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        floatControl.setValue(volume);
    }
}
