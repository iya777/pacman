package game;

import javax.swing.*;
import java.io.*;

public class MainClass {
    // Deklarasi variabel untuk menyimpan UI Panel
    // yang terletak di sebelah kanan dan menyimpan
    // semua yang berkaitan dengan ui di luar window game
    // cth: nilai skor, status apakah game kalah, menang, dsb.
    private static JFrame window;
    private static MainMenu MAIN_MENU;
    private static UIPanel UI_PANEL;
    private static JPanel PANEL_UTAMA;
    private static GameMechanic gameMechanic;
    public static String[] MAP_RED_URL;
    public static String[] MAP_BLUE_URL;
    public static int RED_COLOR = 0xe01d1d;
    public static int BLUE_COLOR = 0x2651d4;
    public static int currentMapType = 0;
    public static int currentLevel = 0;
    public static int bestTimeAtCurrentLevel = -1;
    public static void main(String[] args) {
        MAP_RED_URL = new String[3];
        MAP_RED_URL[0] = "resources/level/red1.csv";
        MAP_RED_URL[1] = "resources/level/red2.csv";
        MAP_RED_URL[2] = "resources/level/red3.csv";
        MAP_BLUE_URL = new String[3];
        MAP_BLUE_URL[0] = "resources/level/blue1.csv";
        MAP_BLUE_URL[1] = "resources/level/blue2.csv";
        MAP_BLUE_URL[2] = "resources/level/blue3.csv";
        // Instantiate atau bikin sebuah frame baru
        // yang merupakan layar utama yang menampung
        // game window dan ui panel.
        window = new JFrame();
        
        // Set judul dari aplikasi menjadi "Pacman"
        // dan ini terletak pada sebelah game icon di window utama
        window.setTitle("Pacman"); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        // Buat sebuah Game Panel
        PANEL_UTAMA = new JPanel();
        try {
            // Tambahkan window game pacman di PANEL_UTAMA
            gameMechanic = new GameMechanic();
            PANEL_UTAMA.add(gameMechanic,0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Bikin UI Panel
        UI_PANEL = new UIPanel();
        PANEL_UTAMA.add(UI_PANEL,1);
        gameMechanic.setUiPanel(UI_PANEL);
        gameMechanic.LoadMap(MAP_RED_URL[0],RED_COLOR); // inisialisasi saja
        UI_PANEL.setGameMechanic(gameMechanic);
        
        gameMechanic.setVisible(false);
        UI_PANEL.setVisible(false);
        
        MAIN_MENU = new MainMenu();
        PANEL_UTAMA.add(MAIN_MENU);
        
        window.setContentPane(PANEL_UTAMA);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        RefreshWindow();
    }
    public static void StartGame(String URL, int color){
        MAIN_MENU.setVisible(false);
        UI_PANEL.setVisible(true);
        gameMechanic.setVisible(true);
        gameMechanic.ChangeMap(URL, color);
        gameMechanic.requestFocus();
        window.pack();
        RefreshWindow();
    }
    public static void GoBackToMenu(){
        gameMechanic.restart();
        GameMechanic.setGameStatus(1);
        UI_PANEL.setVisible(false);
        gameMechanic.setVisible(false);
        MAIN_MENU.setVisible(true);
        MAIN_MENU.requestFocus();
        MAIN_MENU.setFocusable(true);
        window.pack();
        RefreshWindow();
    }
    public static void RefreshWindow(){
        window.revalidate();
        window.repaint();
    }
    // Getters
    public static UIPanel getUIPanel() {
        return UI_PANEL;
    }
    public static JPanel getGameWindow(){
        return PANEL_UTAMA;
    }
    // Setters
    public static void saveBestTimeScore(int bestTime, int currentLevel, int mapType) {
        String PATH = "";
        if (mapType == 0){
            PATH = "red" + Integer.toString(currentLevel) + ".txt";
        }else if (mapType == 1){
            PATH = "blue" + Integer.toString(currentLevel) + ".txt";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH))) {
            writer.write(Integer.toString(bestTime));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveCurrentLevel(int mapType) {
        String PATH = "";
        if (mapType == 0){
            PATH = "saved_level_red.txt";
        }else if (mapType == 1){
            PATH = "saved_level_blue.txt";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH))) {
            writer.write(Integer.toString(MainClass.currentLevel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int loadBestTimeScore(int currentLevel, int mapType) {
        String PATH = "";
        if (mapType == 0){
            PATH = "red" + Integer.toString(currentLevel) + ".txt";
        }else if (mapType == 1){
            PATH = "blue" + Integer.toString(currentLevel) + ".txt";
        }
        File file = new File(PATH);
        if (!file.exists()) {
            return -1;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    public static int loadCurrentLevel(int mapType) {
        String PATH = "";
        if (mapType == 0){
            PATH = "saved_level_red.txt";
        }else if (mapType == 1){
            PATH = "saved_level_blue.txt";
        }
        File file = new File(PATH);
        if (!file.exists()) {
            return -1;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
}
