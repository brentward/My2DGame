package org.brentwardindustries.main;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.awt.event.KeyEvent;

public class ControllerHandler {
    GamePanel gp;
    boolean controllerConnected = false;
    boolean xReleased, yReleased;
    Controller controller;

    public ControllerHandler(GamePanel gp) {
        this.gp = gp;

        Controller[] controllers = ControllerEnvironment
                .getDefaultEnvironment().getControllers();

        if (controllers.length > 0) {
            controllerConnected = true;
        }

        for (Controller potentialController : controllers) {
            if (potentialController.getType() == Controller.Type.GAMEPAD) {
                /* Remember to poll each one */
                controller = potentialController;
                break;
            }
        }
    }


    public void pollController() {
        controller.poll();
        EventQueue queue = controller.getEventQueue();
        Event event = new Event();

        while (queue.getNextEvent(event)) {
            Component comp = event.getComponent();
            String id = comp.getIdentifier().getName();
            float value = event.getValue();
            switch (id) {
                case "0" -> { // A
                    if (value == 1f) {
                        gp.keyHandler.inputPressed(KeyEvent.VK_ENTER);
                    }
                    if (value == 0f) {
                        gp.keyHandler.inputReleased(KeyEvent.VK_ENTER);
                    }
                }
                case "1" -> {} // B
                case "2" -> {} // X
                case "3" -> {} // Y
                case "4" -> {} // L
                case "5" -> {} // R
                case "6" -> { // SELECT
                    if (value == 1f) {
                        gp.keyHandler.inputPressed(KeyEvent.VK_C);
                    }
                    if (value == 0f) {
                        gp.keyHandler.inputReleased(KeyEvent.VK_C);
                    }
                }
                case "7" -> { //START
                    if (value == 1f) {
                        gp.keyHandler.inputPressed(KeyEvent.VK_P);
                    }
                    if (value == 0f) {
                        gp.keyHandler.inputReleased(KeyEvent.VK_P);
                    }
                }
                case "8" -> {} // L3
                case "9" -> {} // R3
                case "pov" -> {
                    if (value == 0.0F) { // NONE
                        gp.keyHandler.upPressed = false;
                        gp.keyHandler.downPressed = false;
                        gp.keyHandler.leftPressed = false;
                        gp.keyHandler.rightPressed = false;
                    } else if (value == 0.125F) { // UP LEFT
                        gp.keyHandler.inputPressed(KeyEvent.VK_W);
                    } else if (value == 0.25F) { // UP
                        gp.keyHandler.inputPressed(KeyEvent.VK_W);
                    } else if (value == 0.375F) { // UP RIGHT
                        gp.keyHandler.inputPressed(KeyEvent.VK_D);
                    } else if (value == 0.5F) { // RIGHT
                        gp.keyHandler.inputPressed(KeyEvent.VK_D);
                    } else if (value == 0.625F) { // DOWN LEFT
                        gp.keyHandler.inputPressed(KeyEvent.VK_S);
                    } else if (value == 0.75F) { // DOWN
                        gp.keyHandler.inputPressed(KeyEvent.VK_S);
                    } else if (value == 0.875F) { // UP LEFT
                        gp.keyHandler.inputPressed(KeyEvent.VK_A);
                    } else if (value == 1.0F) { // LEFT
                        gp.keyHandler.inputPressed(KeyEvent.VK_A);
                    }
                }
                case "y" -> { // LY
                    if (value < 0.4f && value > -0.4f) {
                        gp.keyHandler.upPressed = false;
                        gp.keyHandler.downPressed = false;
                        yReleased = true;
                    } else if (value >= 0.4f && yReleased) {
                        yReleased = false;
                        gp.keyHandler.upPressed = false;
                        gp.keyHandler.inputPressed(KeyEvent.VK_S);
                    } else if (value <= -0.4f && yReleased) {
                        yReleased = false;
                        gp.keyHandler.downPressed = false;
                        gp.keyHandler.inputPressed(KeyEvent.VK_W);
                    }
                }
                case "x" -> { // LX
                    if (value < 0.4f && value > -0.4f) {
                        gp.keyHandler.leftPressed = false;
                        gp.keyHandler.rightPressed = false;
                        xReleased = true;
                    } else if (value >= 0.4f && xReleased) {
                        xReleased = false;
                        gp.keyHandler.leftPressed = false;
                        gp.keyHandler.inputPressed(KeyEvent.VK_D);
                    } else if (value <= -0.4f && xReleased) {
                        xReleased = false;
                        gp.keyHandler.rightPressed = false;
                        gp.keyHandler.inputPressed(KeyEvent.VK_A);
                    }
                }
                case "ry" -> {} // RY
                case "rx" -> {} // RX
                case "z" -> {} // L2 and R2
            }
        }
    }
}
