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
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/sound/levelup.wav");
        soundURL[9] = getClass().getResource("/sound/cursor.wav");
        soundURL[10] = getClass().getResource("/sound/burning.wav");
        soundURL[11] = getClass().getResource("/sound/cuttree.wav");
        soundURL[12] = getClass().getResource("/sound/gameover.wav");
        soundURL[13] = getClass().getResource("/sound/stairs.wav");
        soundURL[14] = getClass().getResource("/sound/sleep.wav");
        soundURL[15] = getClass().getResource("/sound/blocked.wav");
        soundURL[16] = getClass().getResource("/sound/parry.wav");
        soundURL[17] = getClass().getResource("/sound/speak.wav");
        soundURL[18] = getClass().getResource("/sound/Merchant.wav");
        soundURL[19] = getClass().getResource("/sound/Dungeon.wav");
        soundURL[20] = getClass().getResource("/sound/chipwall.wav");
        soundURL[21] = getClass().getResource("/sound/dooropen.wav");
        soundURL[29] = getClass().getResource("/sound/sams-song.wav");
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
