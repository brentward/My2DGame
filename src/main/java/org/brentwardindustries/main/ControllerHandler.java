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
    public boolean l,r, l3, r3;
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
                controller = potentialController;
                break;
            }
        }
    }

    public void pollController() {
        if (controller != null) {
            controller.poll();
            EventQueue queue = controller.getEventQueue();
            Event event = new Event();

            while (queue.getNextEvent(event)) {
                Component comp = event.getComponent();
                String id = comp.getIdentifier().getName();
                float value = event.getValue();
                switch (id) {
                    case "0", "B" -> { // A
                        if (value == 1f) {
                            gp.keyHandler.inputPressed(KeyEvent.VK_ENTER);
                        }
                        if (value == 0f) {
                            gp.keyHandler.inputReleased(KeyEvent.VK_ENTER);
                        }
                    }
                    case "1", "A" -> {
                    } // B
                    case "2", "Y" -> {
                        if (value == 1f) {
                            gp.keyHandler.inputPressed(KeyEvent.VK_F);
                        }
                        if (value == 0f) {
                            gp.keyHandler.inputReleased(KeyEvent.VK_F);
                        }
                    } // X
                    case "3", "X" -> {
                        if (value == 1f) {
                            gp.keyHandler.inputPressed(KeyEvent.VK_C);
                        }
                        if (value == 0f) {
                            gp.keyHandler.inputReleased(KeyEvent.VK_C);
                        }
                    } // Y
                    case "4", "Right Thumb" ->{
                        if (value == 1f) {
                            gp.keyHandler.inputPressed(KeyEvent.VK_SPACE);
                        }
                        if (value == 0f) {
                            gp.keyHandler.inputReleased(KeyEvent.VK_SPACE);
                        }
                    } //l = (value == 1f); // L
                    case "5", "Left Thumb" -> {} //r = (value == 1f); // R
                    case "6", "Select" -> { // SELECT
                        if (value == 1f) {
                            gp.keyHandler.inputPressed(KeyEvent.VK_ESCAPE);
                        }
                        if (value == 0f) {
                            gp.keyHandler.inputReleased(KeyEvent.VK_ESCAPE);
                        }
                    }
                    case "7", "Start" -> { //START
                        if (value == 1f) {
                            gp.keyHandler.inputPressed(KeyEvent.VK_P);
                        }
                        if (value == 0f) {
                            gp.keyHandler.inputReleased(KeyEvent.VK_P);
                        }
                    }
                    case "8", "Left Thumb 3" -> l3 = (value == 1f); // L3
                    case "9", "Right Thumb 3" -> r3 = (value == 1f); // R3
                    case "pov" -> {
                        if (value == Component.POV.CENTER) { // NONE
                            gp.keyHandler.upPressed = false;
                            gp.keyHandler.downPressed = false;
                            gp.keyHandler.leftPressed = false;
                            gp.keyHandler.rightPressed = false;
                        } else if (value == Component.POV.UP_LEFT) { // UP LEFT
                            if (gp.keyHandler.upPressed) {
                                gp.keyHandler.upPressed = false;
                                gp.keyHandler.downPressed = false;
                                gp.keyHandler.rightPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_A);
                            } else {
                                gp.keyHandler.downPressed = false;
                                gp.keyHandler.leftPressed = false;
                                gp.keyHandler.rightPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_W);
                            }
                        } else if (value == Component.POV.UP) { // UP
                            gp.keyHandler.downPressed = false;
                            gp.keyHandler.leftPressed = false;
                            gp.keyHandler.rightPressed = false;
                            gp.keyHandler.inputPressed(KeyEvent.VK_W);
                        } else if (value == Component.POV.UP_RIGHT) { // UP RIGHT
                            if (gp.keyHandler.upPressed) {
                                gp.keyHandler.upPressed = false;
                                gp.keyHandler.downPressed = false;
                                gp.keyHandler.leftPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_D);
                            } else {
                                gp.keyHandler.downPressed = false;
                                gp.keyHandler.leftPressed = false;
                                gp.keyHandler.rightPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_W);
                            }
                        } else if (value == Component.POV.RIGHT) { // RIGHT
                            gp.keyHandler.upPressed = false;
                            gp.keyHandler.downPressed = false;
                            gp.keyHandler.leftPressed = false;
                            gp.keyHandler.inputPressed(KeyEvent.VK_D);
                        } else if (value == Component.POV.DOWN_RIGHT) { // DOWN RIGHT
                            if (gp.keyHandler.downPressed) {
                                gp.keyHandler.upPressed = false;
                                gp.keyHandler.downPressed = false;
                                gp.keyHandler.leftPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_D);
                            } else {
                                gp.keyHandler.upPressed = false;
                                gp.keyHandler.leftPressed = false;
                                gp.keyHandler.rightPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_S);
                            }
                        } else if (value == Component.POV.DOWN) { // DOWN
                            gp.keyHandler.upPressed = false;
                            gp.keyHandler.leftPressed = false;
                            gp.keyHandler.rightPressed = false;
                            gp.keyHandler.inputPressed(KeyEvent.VK_S);
                        } else if (value == Component.POV.DOWN_LEFT) { // DOWN LEFT
                            if (gp.keyHandler.downPressed) {
                                gp.keyHandler.upPressed = false;
                                gp.keyHandler.downPressed = false;
                                gp.keyHandler.rightPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_A);
                            } else {
                                gp.keyHandler.upPressed = false;
                                gp.keyHandler.leftPressed = false;
                                gp.keyHandler.rightPressed = false;
                                gp.keyHandler.inputPressed(KeyEvent.VK_S);
                            }
                        } else if (value == Component.POV.LEFT) { // LEFT
                            gp.keyHandler.upPressed = false;
                            gp.keyHandler.downPressed = false;
                            gp.keyHandler.rightPressed = false;
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
                    case "ry" -> {
                    } // RY
                    case "rx" -> {
                    } // RX
                    case "z" -> {
                    } // L2 and R2
                }
            }
        }
    }
}
