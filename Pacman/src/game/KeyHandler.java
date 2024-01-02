package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;

public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<>();

    public class Key {
        public boolean isPressed;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if (pressed != isPressed) {
                this.isPressed = pressed;
            }
        }
        public void setIsPressed(boolean value){
            this.isPressed = value;
        }
    }

    public Key k_up = new Key();
    public Key k_down = new Key();
    public Key k_left = new Key();
    public Key k_right = new Key();
    
    public KeyHandler(GameMechanic game) {
        game.addKeyListener(this);
    }

    public void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            k_left.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            k_right.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            k_up.toggle(pressed);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            k_down.toggle(pressed);
        }
    }
    public void setAllKeysPressed(boolean value){
        for (Key k : keys) {
            k.setIsPressed(value);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
