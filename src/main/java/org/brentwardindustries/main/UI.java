package org.brentwardindustries.main;

import org.brentwardindustries.entity.Entity;
import org.brentwardindustries.object.CoinBronzeObject;
import org.brentwardindustries.object.HeartObject;
import org.brentwardindustries.object.MagicCrystalObject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2D;
    public Font font;
    BufferedImage heartFull, heartHalf, heartBlank, crystalFull, crystalBlank, coin;
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;
    int charIndex = 0;
    String combinedText = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/font/maru_monica.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity heart = new HeartObject(gp);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartBlank = heart.image3;
        Entity crystal = new MagicCrystalObject(gp);
        crystalFull = crystal.image;
        crystalBlank = crystal.image2;
        Entity coinBronzeObject = new CoinBronzeObject(gp);
        coin = coinBronzeObject.down1;
    }

    public void addMessage(String text) {
        messages.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2D) {
        this.g2D = g2D;

        g2D.setFont(font);
//        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogState) {
            drawDialogueScreen();
        }
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gp.halfTileSize;
        int y = gp.halfTileSize;
        int i = 0;

        // DRAW MAX LIFE
        while (i < gp.player.maxLife / 2) {
            g2D.drawImage(heartBlank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESET
        x = gp.halfTileSize;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2D.drawImage(heartHalf, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2D.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

        // DRAW MAX MAGIC
        x = gp.halfTileSize - 5;
        y = gp.halfTileSize * 3;
        i = 0;
        while (i < gp.player.maxMagic) {
            g2D.drawImage(crystalBlank, x, y, null);
            i++;
            x += 35;
        }

        // RESET
        x = gp.halfTileSize - 5;
        y = gp.halfTileSize * 3;
        i = 0;
        while (i < gp.player.magic) {
            g2D.drawImage(crystalFull, x, y, null);
            i++;
            x += 35;
        }


    }

    public void drawMessage() {
        int messageX = gp.tileSize;
        int messageY= gp.tileSize * 4;
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 32f));

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i) != null) {
                g2D.setColor(Color.BLACK);
                g2D.drawString(messages.get(i), messageX + 2, messageY + 2);
                g2D.setColor(Color.WHITE);
                g2D.drawString(messages.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (counter > 180) {
                    messages.remove(i);
                    messageCounter.remove(i--);
                }
            }
        }
    }

    public void drawTitleScreen() {
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 96f));
        String text = "Blue Boy Adventure";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;

        // SHADOW
        g2D.setColor(Color.GRAY);
        g2D.drawString(text, x + 5, y + 5);
        // MAIN COLOR
        g2D.setColor(Color.WHITE);
        g2D.drawString(text, x, y);

        // BLUE BOY IMAGE
        x = gp.screenWidth / 2 - gp.tileSize;
        y += gp.tileSize * 2;
        g2D.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // MENU
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 48f));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3.5;
        g2D.drawString(text, x, y);
        if (commandNum == 0) {
            g2D.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 1;
        g2D.drawString(text, x, y);
        if (commandNum == 1) {
            g2D.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2D.drawString(text, x, y);
        if (commandNum == 2) {
            g2D.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen() {
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 80f));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2D.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 3;
        int y = gp.halfTileSize;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN, 28f));
        x += gp.tileSize;
        y += gp.tileSize;

        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex]!= null) {
//            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            char[] characters = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();

            if (charIndex < characters.length) {
                gp.playSE(17);
                String s = String.valueOf(characters[charIndex]);
                combinedText += s;
                currentDialogue = combinedText;
                charIndex++;
            }


            if (gp.keyHandler.enterPressed) {
                charIndex = 0;
                combinedText = "";
                if (gp.gameState == gp.dialogState) {
                    npc.dialogueIndex++;
                    gp.keyHandler.enterPressed = false;
                }
            }
        } else {
            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogState) {
                gp.gameState = gp.playState;
            }
        }

        for (String line : currentDialogue.split("\n")) {
            g2D.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawCharacterScreen() {
        // CREATE A FRAME
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2D.setColor(Color.WHITE);
        g2D.setFont(g2D.getFont().deriveFont(32f));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        // NAMES
        g2D.drawString("Level", textX, textY);
        textY += lineHeight;
        g2D.drawString("Life", textX, textY);
        textY += lineHeight;
        g2D.drawString("Magic", textX, textY);
        textY += lineHeight;
        g2D.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2D.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2D.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2D.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2D.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2D.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2D.drawString("Coin", textX, textY);
        textY += lineHeight + 10;
        g2D.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2D.drawString("Shield", textX, textY);

        // VALUES
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = gp.player.life + " / " + gp.player.maxLife;
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = gp.player.magic + " / " + gp.player.maxMagic;
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2D.drawString(value, textX, textY);
        textY += lineHeight;
        g2D.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - gp.halfTileSize, null);
        textY += gp.tileSize;
        g2D.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - gp.halfTileSize, null);
    }

    public void drawInventory(Entity entity, boolean cursor) {
        int frameX;
        int frameY;
        int frameWidth;
        int frameHeight;
        int slotCol;
        int slotRow;

        // FRAME
        if (entity == gp.player) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gp.tileSize + 3;

        // DRAW PLAYER"S ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {
            // EQUIP CURSOR
            if (entity.inventory.get(i) == entity.currentWeapon
                    || entity.inventory.get(i) == entity.currentShield
                    || entity.inventory.get(i) == entity.currentLight) {
                g2D.setColor(new Color(240, 190, 90));
                g2D.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2D.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            if (entity.inventory.get(i).amount > 1) {
                g2D.setFont(g2D.getFont().deriveFont(32f));
                int amountX;
                int amountY;
                String s = "" + entity.inventory.get(i).amount;
                amountX = getXForAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                g2D.setColor(new Color(60, 60, 60));
                g2D.drawString(s, amountX, amountY);
                g2D.setColor(Color.WHITE);
                g2D.drawString(s, amountX - 2, amountY - 2);
            }
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }

        // CURSOR
        if (cursor) {
            int cursorX = slotXStart + (slotSize * slotCol);
            int cursorY = slotYStart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
            // DRAW CURSOR
            g2D.setColor(Color.WHITE);
            g2D.setStroke(new BasicStroke(3));
            g2D.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;
            // DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2D.setFont(g2D.getFont().deriveFont(28f));

            int itemIndex = getItemIndexInSlot(slotCol, slotRow);
            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                for (String line : entity.inventory.get(itemIndex).description.split("\n")) {
                    g2D.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }

    public void drawGameOverScreen() {
        g2D.setColor(new Color(0, 0 , 0, 150));
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // SHADOW
        g2D.setColor(Color.BLACK);
        x = getXForCenteredText(text);
        y = gp.tileSize * 5;
        g2D.drawString(text, x, y);
        // MAIN
        g2D.setColor(Color.WHITE);
        g2D.drawString(text, x - 4, y - 4);

        // RETRY
        g2D.setFont(g2D.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize * 4;
        g2D.drawString(text, x, y);
        if (commandNum == 0) {
            g2D.drawString(">", x - 25, y);
        }

        // BACK
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2D.drawString(text, x, y);
        if (commandNum == 1) {
            g2D.drawString(">", x - 25, y);
        }

    }

    public void drawOptionsScreen() {
        // TEXT
        g2D.setColor(Color.WHITE);
        g2D.setFont(g2D.getFont().deriveFont(32f));

        final int frameX = gp.tileSize * 6;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 8;
        final int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0 -> optionsTop(frameX, frameY);
            case 1 -> optionsFullScreenNotification(frameX, frameY);
            case 2 -> optionsControls(frameX, frameY);
            case 3 -> optionsEndGameConfirmation(frameX, frameY);
        }
        gp.keyHandler.enterPressed = false;
    }

    public void optionsTop(int frameX, int frameY) {
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2D.drawString(text, textX, textY);

        // FULL SCREEN
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2D.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                gp.fullScreenOn = !gp.fullScreenOn;
                subState = 1;
            }
        }

        // MUSIC
        textY += gp.tileSize;
        g2D.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2D.drawString(">", textX - 25, textY);
        }

        // SE
        textY += gp.tileSize;
        g2D.drawString("Sound FX", textX, textY);
        if (commandNum == 2) {
            g2D.drawString(">", textX - 25, textY);
        }

        // CONTROLS
        textY += gp.tileSize;
        g2D.drawString("Controls", textX, textY);
        if (commandNum == 3) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize;
        g2D.drawString("Exit", textX, textY);
        if (commandNum == 4) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        textY += gp.tileSize * 2;
        g2D.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + gp.tileSize * 4 + gp.halfTileSize;
        textY = frameY + gp.tileSize * 2 + gp.halfTileSize;
        g2D.setStroke(new BasicStroke(3));
        g2D.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn) {
            g2D.fillRect(textX, textY, 24, 24);
        }

        // MUSIC VOLUME SLIDER
        textY += gp.tileSize;
        g2D.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2D.fillRect(textX, textY, volumeWidth, 24);


        // SE VOLUME SLIDER
        textY += gp.tileSize;
        g2D.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2D.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig();
    }

    public void optionsFullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "The change will take\neffect after restarting\nthe game.";

        for (String line : currentDialogue.split("\n")) {
            g2D.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY = frameY + gp.tileSize * 9;
        g2D.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                subState = 0;
            }
        }
    }

    public void optionsControls(int frameX, int frameY) {
        int textX;
        int textY;

        String text = "Controls";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2D.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2D.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("Confirm/Attack", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("Shoot/Cast", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("Character Screen", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("Pause", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("Options", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("Map", textX, textY);

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2D.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("ESC", textX, textY);
        textY += gp.tileSize;
        g2D.drawString("M", textX, textY);

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2D.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }

    }

    public  void optionsEndGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;
        currentDialogue = "Quit the game and\nreturn to the title screen?";

        for (String line : currentDialogue.split("\n")) {
            g2D.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gp.tileSize * 3;
        g2D.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                subState = 0;
                commandNum = 2;
                gp.stopMusic();
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }

        text = "No";
        textX = getXForCenteredText(text);
        textY += gp.tileSize;
        g2D.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2D.drawString(">", textX - 25, textY);
            if (gp.keyHandler.enterPressed) {
                subState = 0;
                commandNum = 4;
            }
        }

        // NO
    }

    public void drawTransition() {
        counter ++;
        g2D.setColor(new Color(0, 0, 0, counter * 5));
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eventHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eventHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eventHandler.tempRow;
            gp.eventHandler.previousEventX = gp.player.worldX;
            gp.eventHandler.previousEventY = gp.player.worldY;
            gp.changeArea();
        }
    }

    public void drawTradeScreen() {
        switch (subState) {
            case 0 -> tradeSelect();
            case 1 -> tradeBuy();
            case 2 -> tradeSell();
        }
        gp.keyHandler.enterPressed = false;
    }

    public void tradeSelect() {
        npc.dialogueSet = 0;
        drawDialogueScreen();

        // DRAW WINDOW
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = gp.tileSize * 3 + gp.halfTileSize;
        drawSubWindow(x, y, width, height);

        // DRAW TEXTS
        x += gp.tileSize;
        y += gp.tileSize;
        g2D.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2D.drawString(gp.cursor, x - gp.halfTileSize, y);
            if (gp.keyHandler.enterPressed) {
                subState = 1;
            }
        }
        y += gp.tileSize;
        g2D.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2D.drawString(gp.cursor, x - gp.halfTileSize, y);
            if (gp.keyHandler.enterPressed) {
                subState = 2;
            }
        }
        y += gp.tileSize;
        g2D.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2D.drawString(gp.cursor, x - gp.halfTileSize, y);
            if (gp.keyHandler.enterPressed) {
                commandNum = 0;
                npc.startDialogue(npc, 1);
            }
        }
    }

    public void tradeBuy() {
        drawInventory(gp.player, false);
        drawInventory(npc, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2D.drawString("[ESC] Back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 12;
        drawSubWindow(x, y, width, height);
        g2D.drawString("Coins: " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE
        int itemIndex = getItemIndexInSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < npc.inventory.size()) {
            x = gp.tileSize * 5 + gp.halfTileSize;
            y = gp.tileSize * 5 + gp.halfTileSize;
            width = gp.tileSize * 2 + gp.halfTileSize;
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2D.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize * 8 - 20);
            g2D.drawString(text, x, y + 34);

            // BUY
            if (gp.keyHandler.enterPressed) {
                if (npc.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    npc.startDialogue(npc, 2);
//                    drawDialogueScreen();
                } else if (gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
                    gp.player.coin -= npc.inventory.get(itemIndex).price; // INFINITE NPC INVENTORY
//                    gp.player.coin -= npc.inventory.remove(itemIndex).price; // FINITE NPC PER OBJECT
                } else {
                    subState = 0;
                    npc.startDialogue(npc, 3);
                }
            }
        }
    }

    public void tradeSell() {
        // DRAW PLAYER INVENTORY
        drawInventory(gp.player, true);

        // DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2D.drawString("[ESC] Back", x + 24, y + 60);

        // DRAW PLAYER COIN WINDOW
        x = gp.tileSize * 12;
        drawSubWindow(x, y, width, height);
        g2D.drawString("Coins: " + gp.player.coin, x + 24, y + 60);

        // DRAW PRICE
        int itemIndex = getItemIndexInSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {
            x = gp.tileSize * 15 + gp.halfTileSize;
            y = gp.tileSize * 5 + gp.halfTileSize;
            width = gp.tileSize * 2 + gp.halfTileSize;
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2D.drawImage(coin, x + 10, y + 8, 32, 32, null);
            int price = gp.player.inventory.get(itemIndex).price / 2;
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize * 18 - 20);
            g2D.drawString(text, x, y + 34);

            // SELL
            if (gp.keyHandler.enterPressed) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon
                        || gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
                    commandNum = 0;
                    npc.startDialogue(npc, 4);
                } else {
                    if (gp.player.inventory.get(itemIndex).amount > 1) {
                        gp.player.inventory.get(itemIndex).amount--;
                    } else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }
            }
        }
    }

    public void drawSleepScreen() {
        counter++;

        if (counter < 120) {
            gp.environmentManager.lighting.filterAlpha += 0.01f;
            if (gp.environmentManager.lighting.filterAlpha > 1f) {
                gp.environmentManager.lighting.filterAlpha = 1f;
            }
        }
        if (counter >= 120) {
            gp.environmentManager.lighting.filterAlpha -= 0.01f;
            if (gp.environmentManager.lighting.filterAlpha < 0f) {
                gp.environmentManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.environmentManager.lighting.resetDay();
                gp.gameState = gp.playState;
                gp.player.setImage();
            }
        }
    }

    public int getItemIndexInSlot(int slotCol, int slotRow) {
        return slotCol + (slotRow * 5);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 211);
        g2D.setColor(color);
        g2D.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2D.setColor(color);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXForCenteredText(String text) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int length = (int) g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        int x = tailX - length;
        return x;
    }
}
