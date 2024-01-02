package game;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameMechanic extends javax.swing.JPanel implements Runnable, IObserver {
    /*Game Mechanic*/ 
    // Deklarasi list untuk menyimpan semua jenis entitas
    // baik itu object yang dinamis maupun statis.
    // gameObjects = wall, pacgum, superpacgum, ghosthouse, pacman, ghosts
    private java.util.List<AEntity> gameObjects;
    // Variabel untuk menyimpan semua ghosts.
    // Dan variabel ini akan ditaruh ke dalam variabel
    // di atas yaitu gameObjects.
    private java.util.List<AGhost> ghosts;
    private static java.util.List<Wall> walls;

    private static Pacman pacman;
    private static Blinky blinky;

    private static boolean firstInput = false;
    private static int gameStatus = 0;
    
    // pacgum eaten counter
    private int totalPacgums = -1;
    private int restoreTotalPacgums = -1;
    
    // Timer
    private long startTime;
    private long elapsedTime;
    private Timer timer;
    
    /* Gameplay UI */
    public static int width;
    public static int height;
    
    public static int MAP_WIDTH = 56;
    public static int MAP_HEIGHT = 62;
    public static int MAP_CELL_SIZE = 8;
    
    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;
    private Image gambarMap;
    private BufferedImage bufferedGambarMap;

    private KeyHandler key;

    private UIPanel uiPanel;
    public GameMechanic() throws IOException{
        initComponents();
        this.width = this.getPreferredSize().width;
        this.height = this.getPreferredSize().height;
        //setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        gambarMap = ImageIO.read(getClass().getClassLoader().getResource("resources/img/background.jpg"));
        bufferedGambarMap = (BufferedImage) gambarMap;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
    }
    
    /* Timer System */
    private void startTimer() {
        startTime = System.currentTimeMillis();
        timer.start();
    }
    private void stopTimer() {
        timer.stop();
        updateTimer();
    }
    private void updateTimer() {
        elapsedTime = System.currentTimeMillis() - startTime;
        this.uiPanel.updateTimerText(elapsedTime/1000 + "");
    }
    
    public void setUiPanel(UIPanel uiPanel) {
        this.uiPanel = uiPanel;
    }
    public UIPanel getUiPanel() {
        return uiPanel;
    }

    // method ini dipanggil ketika JPanel telah dibuat
    @Override
    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    // GameMechanic initialization
    public void init() {
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();

        key = new KeyHandler(this);
    }

    // GameMechanic update
    public void update() {
        if (totalPacgums <= 0 && gameStatus == 0){
            gameWin();
        }
        if (gameStatus != 2 && gameStatus != 1){
            for (AEntity o: gameObjects) {
                    if (!o.isDestroyed()) o.update();
            }
        }
        if (getFirstInput() && !timer.isRunning()){
            startTimer();
        }
    }
    public int getGameStatus(){
        return gameStatus;
    }
    // Input manager
    public void input(KeyHandler key) {
        pacman.input(key);
    }

    // Menyiapkan resources yang akan ditampilkan (background, entity)
    public void render() {
        if (g != null && gameStatus != 2 && gameStatus != 1) {
            g.drawImage(gambarMap, 0, 0, width, height, null);
            render(g);
        }
    }
    
    // Draw map
    public void setMapImage(int x, int y, int rgb){
        for (int xx = 0; xx < MAP_CELL_SIZE; xx++) {
            for (int yy = 0; yy < MAP_CELL_SIZE; yy++) {
                bufferedGambarMap.setRGB((x*MAP_CELL_SIZE)+xx, (y*MAP_CELL_SIZE)+yy, rgb);
            }
        }
    }


    // Draw the image
    public void draw() {
        if (gameStatus != 1){
            Graphics g2 = this.getGraphics();
            g2.drawImage(img, 0, 0, width, height, null);
            g2.dispose();
        }
    }

    @Override
    public void run() {
        init();

        // Pembuatan game loop
        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; //Time before update

        final int MUBR = 5; // Must update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        double TARGET_FPS = 60.0;
        double TTBR = 1000000000 / TARGET_FPS; //Total time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;
            while ((now - lastUpdateTime) > TBU && (updateCount < MUBR)) {
                input(key);
                update();
                lastUpdateTime += TBU;
                updateCount++;
            }

            if (now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }

            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while ((now - lastRenderTime < TTBR) && (now - lastUpdateTime < TBU)) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.err.println("ERROR yielding thread");
                }

                now = System.nanoTime();
            }
        }
    }

    /*Game Mechanic*/
    private List<List<String>> parseCsv(URI file) {
        List<List<String>> data = new ArrayList<>();
        try {
            InputStreamReader reader =  new InputStreamReader(file.toURL().openStream());
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();
            while(line != null) {
                List<String> lineData = Arrays.asList(line.split(";"));
                data.add(lineData);
                line = br.readLine();
            }
            br.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    public void LoadMap(String url, int wallColor){
        MainClass.bestTimeAtCurrentLevel = MainClass.loadBestTimeScore(MainClass.currentLevel,MainClass.currentMapType);
        uiPanel.setBestTimeScore(MainClass.bestTimeAtCurrentLevel);
        ghosts = new ArrayList();
        walls = new ArrayList();
        gameObjects = new ArrayList();
        List<List<String>> data = null;
        try {
            data = parseCsv(getClass().getClassLoader().getResource(url).toURI());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        CollisionDetector collisionDetector = new CollisionDetector(this);
        AGhostSpawner ghostSpawner = null;
        
        totalPacgums = 0;
        restoreTotalPacgums = 0;
        for(int xx = 0 ; xx < GameMechanic.MAP_WIDTH ; xx++) {
            for(int yy = 0 ; yy < GameMechanic.MAP_HEIGHT ; yy++) {
                setMapImage(xx, yy, 0x000000);
                String dataChar = data.get(yy).get(xx);
                if (dataChar.equals("x")) {
                    gameObjects.add(new Wall(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                    setMapImage(xx, yy, wallColor);
                }else if (dataChar.equals("P")) {
                    pacman = new Pacman(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE);
                    pacman.setCollisionDetector(collisionDetector);

                    pacman.registerObserver(uiPanel);
                    pacman.registerObserver(this);
                }else if (dataChar.equals("b") || dataChar.equals("p") || dataChar.equals("i") || dataChar.equals("c")) {
                    switch (dataChar) {
                        case "b":
                            ghostSpawner = new BlinkySpawner();
                            break;
                        case "p":
                            ghostSpawner = new PinkySpawner();
                            break;
                        case "i":
                            ghostSpawner = new InkySpawner();
                            break;
                        case "c":
                            ghostSpawner = new ClydeSpawner();
                            break;
                    }

                    AGhost ghost = ghostSpawner.SummonGhost(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE);
                    ghosts.add(ghost);
                    if (dataChar.equals("b")) {
                        blinky = (Blinky) ghost;
                    }
                }else if (dataChar.equals("*")) {
                    gameObjects.add(new PacGum(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                    totalPacgums += 1;
                }else if (dataChar.equals("o")) {
                    gameObjects.add(new SuperPacGum(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                }else if (dataChar.equals("-")) {
                    gameObjects.add(new GhostHouse(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                }
            }
        }
        restoreTotalPacgums = totalPacgums;
        gameObjects.add(pacman);
        gameObjects.addAll(ghosts);

        for (AEntity o : gameObjects) {
            if (o instanceof Wall) {
                walls.add((Wall) o);
            }
        }
    }
    public void LoadNextMap(String url, int wallColor){
        MainClass.bestTimeAtCurrentLevel = MainClass.loadBestTimeScore(MainClass.currentLevel,MainClass.currentMapType);
        uiPanel.setBestTimeScore(MainClass.bestTimeAtCurrentLevel);
        ghosts.clear();
        walls.clear();
        gameObjects.clear();
        ghosts = new ArrayList();
        walls = new ArrayList();
        gameObjects = new ArrayList();
        List<List<String>> data = null;
        try {
            data = parseCsv(getClass().getClassLoader().getResource(url).toURI());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        CollisionDetector collisionDetector = new CollisionDetector(this);
        AGhostSpawner ghostSpawner = null;
        
        totalPacgums = 0;
        restoreTotalPacgums = 0;
        for(int xx = 0 ; xx < GameMechanic.MAP_WIDTH ; xx++) {
            for(int yy = 0 ; yy < GameMechanic.MAP_HEIGHT ; yy++) {
                setMapImage(xx, yy, 0x000000);
                String dataChar = data.get(yy).get(xx);
                if (dataChar.equals("x")) {
                    gameObjects.add(new Wall(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                    setMapImage(xx, yy, wallColor);
                }else if (dataChar.equals("P")) {
                    pacman = new Pacman(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE);
                    pacman.setCollisionDetector(collisionDetector);

                    pacman.registerObserver(uiPanel);
                    pacman.registerObserver(this);
                }else if (dataChar.equals("b") || dataChar.equals("p") || dataChar.equals("i") || dataChar.equals("c")) {
                    switch (dataChar) {
                        case "b":
                            ghostSpawner = new BlinkySpawner();
                            break;
                        case "p":
                            ghostSpawner = new PinkySpawner();
                            break;
                        case "i":
                            ghostSpawner = new InkySpawner();
                            break;
                        case "c":
                            ghostSpawner = new ClydeSpawner();
                            break;
                    }

                    AGhost ghost = ghostSpawner.SummonGhost(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE);
                    ghosts.add(ghost);
                    if (dataChar.equals("b")) {
                        blinky = (Blinky) ghost;
                    }
                }else if (dataChar.equals("*")) {
                    gameObjects.add(new PacGum(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                    totalPacgums += 1;
                }else if (dataChar.equals("o")) {
                    gameObjects.add(new SuperPacGum(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                }else if (dataChar.equals("-")) {
                    gameObjects.add(new GhostHouse(xx * GameMechanic.MAP_CELL_SIZE, yy * GameMechanic.MAP_CELL_SIZE));
                }
            }
        }
        restoreTotalPacgums = totalPacgums;
        gameObjects.add(pacman);
        gameObjects.addAll(ghosts);

        for (AEntity o : gameObjects) {
            if (o instanceof Wall) {
                walls.add((Wall) o);
            }
        }
        setGameStatus(3);
        MainClass.RefreshWindow();
    }
    public static List<Wall> getWalls() {
        return walls;
    }
    public List<AEntity> getEntities() {
        return gameObjects;
    }
    
    public void render(Graphics2D g) {
        if (gameStatus != 2 && gameStatus != 1){
            for (AEntity o: gameObjects) {
                if (!o.isDestroyed()) o.render(g);
            }
        }
    }

    public static Pacman getPacman() {
        return pacman;
    }
    public static Blinky getBlinky() {
        return blinky;
    }

    @Override
    public void updatePacGumEaten(PacGum pg) {
        pg.destroy();
        totalPacgums -= 1;
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
        spg.destroy();
        for (AGhost gh : ghosts) {
            gh.getState().superPacGumEaten();
        }
    }

    @Override
    public void updateGhostCollision(AGhost gh) {
        if (gh.getState() instanceof FrightenedMode) {
            gh.getState().eaten();
        }else if (!(gh.getState() instanceof EatenMode)) {
            gameEnd(-1);
            uiPanel.updateStatus("GAME OVER!");
            uiPanel.SetVisibleGameOverUI(true);
            uiPanel.ChangeActionButtonLabelText("RESTART");
        }else if (gameStatus == 0){
            uiPanel.updateStatus("");
        }
    }
    
    private void gameEnd(int statusCode){
        for (AEntity o : gameObjects) {
            o.destroy();
        }
        setFirstInput(false);
        setGameStatus(statusCode);
        timer.stop();
        startTime = 0;
        uiPanel.updateTimerText("0");
        key.setAllKeysPressed(false);
    }
    private void gameWin(){
        gameEnd(2);
        if ((int)(elapsedTime/1000) < MainClass.loadBestTimeScore(MainClass.currentLevel, MainClass.currentMapType)){
            MainClass.saveBestTimeScore((int)(elapsedTime/1000), MainClass.currentLevel, MainClass.currentMapType); 
        }
        MainClass.currentLevel += 1;
        MainClass.saveCurrentLevel(MainClass.currentMapType); // save setelah current level diincrement
        
        uiPanel.updateStatus("YOU WIN!");
        if (MainClass.currentLevel < 3){
            uiPanel.ChangeActionButtonLabelText("NEXT LEVEL");
        }
        else {
            uiPanel.ChangeActionButtonLabelText("START FROM LEVEL 0 AGAIN");
            MainClass.currentLevel = 0;
        }
        uiPanel.SetVisibleGameOverUI(true);
        timer.stop();
        
    }
    public void restart(){
        setFirstInput(false);
        totalPacgums = restoreTotalPacgums;
        for (AEntity o : gameObjects) {
            o.restore();
        }
        setFocusable(true);
        requestFocus();
        key.setAllKeysPressed(false);
        uiPanel.SetVisibleGameOverUI(false);
        uiPanel.updateStatus("");
    }
    public void nextLevel(){
        setFirstInput(false);
        if (MainClass.currentMapType == 0){
            LoadNextMap(MainClass.MAP_RED_URL[MainClass.currentLevel], MainClass.RED_COLOR);
        }else if (MainClass.currentMapType == 1){
            LoadNextMap(MainClass.MAP_BLUE_URL[MainClass.currentLevel], MainClass.BLUE_COLOR);
        }
        setFocusable(true);
        requestFocus();
        key.setAllKeysPressed(false);
        uiPanel.SetVisibleGameOverUI(false);
        uiPanel.updateStatus("");
    }
    public void ChangeMap(String URL, int color){
        setGameStatus(1); // 0 utk playing, -1 utk kalah, 1 utk change map, 2 utk menang, 3 utk load next level
        setFirstInput(false);
        LoadNextMap(URL, color);
        key.setAllKeysPressed(false);
        uiPanel.SetVisibleGameOverUI(false);
        uiPanel.updateStatus("");
    }
    public static void setFirstInput(boolean b) {
        firstInput = b;
        if (b){
            setGameStatus(0);
        }
    }

    public static boolean getFirstInput() {
        return firstInput;
    }
    
    public static void setGameStatus(int status){
        gameStatus = status;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(448, 496));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
