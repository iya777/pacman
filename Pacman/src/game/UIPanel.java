package game;

public class UIPanel extends javax.swing.JPanel implements IObserver {
    
    private GameMechanic gameMechanic;
    public static int width;
    public static int height;

    private int score = 0;
    
    public UIPanel() {
        initComponents();
        width = this.getPreferredSize().width;
        height = this.getPreferredSize().height;
        setFocusable(true);
        requestFocus();
        this.GAME_STATUS_X_LABEL.setText("");
        SetVisibleGameOverUI(false);
    }

    public void setGameMechanic(GameMechanic gameMechanic) {
        this.gameMechanic = gameMechanic;
    }
    
    public void updateTimerText(String txt) {
        this.TIME_X_LABEL.setText(txt + " seconds");
    }
    
    public void SetVisibleGameOverUI(boolean value){
        this.BUTTON_MENU.setVisible(true);
        this.BUTTON_ACTION.setVisible(value);
    }
    
    public void updateStatus(String status){
        this.GAME_STATUS_X_LABEL.setText(status);
    }

    public int getScore() {
        return score;
    }
    public void ChangeActionButtonLabelText(String value){
        this.BUTTON_ACTION.setText(value);
    }
    public void setBestTimeScore(int value){
        this.BEST_TIME_X_LABEL.setText(Integer.toString(value) + " seconds");
    }
    @Override
    public void updatePacGumEaten(PacGum pg) {
        
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
        
    }

    @Override
    public void updateGhostCollision(AGhost gh) {
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TIME_LABEL = new javax.swing.JLabel();
        BEST_TIME_LABEL = new javax.swing.JLabel();
        MAP_LEVEL_LABEL = new javax.swing.JLabel();
        TIME_X_LABEL = new javax.swing.JLabel();
        GAME_STATUS_X_LABEL = new javax.swing.JLabel();
        BEST_TIME_X_LABEL = new javax.swing.JLabel();
        MAP_LEVEL_X_LABEL = new javax.swing.JLabel();
        BUTTON_MENU = new javax.swing.JButton();
        BUTTON_ACTION = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(256, 496));

        TIME_LABEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TIME_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        TIME_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TIME_LABEL.setText("Time:");

        BEST_TIME_LABEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BEST_TIME_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        BEST_TIME_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BEST_TIME_LABEL.setText("Best Time:");

        MAP_LEVEL_LABEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MAP_LEVEL_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        MAP_LEVEL_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MAP_LEVEL_LABEL.setText("Map Level:");

        TIME_X_LABEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TIME_X_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        TIME_X_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TIME_X_LABEL.setText("0");

        GAME_STATUS_X_LABEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        GAME_STATUS_X_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        GAME_STATUS_X_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        GAME_STATUS_X_LABEL.setText("GAME OVER");

        BEST_TIME_X_LABEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        BEST_TIME_X_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        BEST_TIME_X_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BEST_TIME_X_LABEL.setText("0");

        MAP_LEVEL_X_LABEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MAP_LEVEL_X_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        MAP_LEVEL_X_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MAP_LEVEL_X_LABEL.setText("0");

        BUTTON_MENU.setBackground(new java.awt.Color(204, 204, 204));
        BUTTON_MENU.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        BUTTON_MENU.setText("MENU");
        BUTTON_MENU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_MENUActionPerformed(evt);
            }
        });

        BUTTON_ACTION.setBackground(new java.awt.Color(204, 204, 204));
        BUTTON_ACTION.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        BUTTON_ACTION.setText("ACTION");
        BUTTON_ACTION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_ACTIONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BEST_TIME_LABEL)
                            .addComponent(TIME_LABEL)
                            .addComponent(MAP_LEVEL_LABEL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BEST_TIME_X_LABEL)
                            .addComponent(TIME_X_LABEL)
                            .addComponent(MAP_LEVEL_X_LABEL)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(GAME_STATUS_X_LABEL))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BUTTON_ACTION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BUTTON_MENU, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(TIME_X_LABEL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BEST_TIME_X_LABEL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MAP_LEVEL_X_LABEL))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(TIME_LABEL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BEST_TIME_LABEL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MAP_LEVEL_LABEL)))
                .addGap(36, 36, 36)
                .addComponent(GAME_STATUS_X_LABEL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BUTTON_ACTION, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BUTTON_MENU, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BUTTON_MENUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_MENUActionPerformed
        // TODO add your handling code here:
        MainClass.GoBackToMenu();
    }//GEN-LAST:event_BUTTON_MENUActionPerformed

    private void BUTTON_ACTIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_ACTIONActionPerformed
        // TODO add your handling code here:
        if (gameMechanic.getGameStatus() == -1){
            gameMechanic.restart();
        }else if (gameMechanic.getGameStatus() == 2){
            gameMechanic.nextLevel();
        }
    }//GEN-LAST:event_BUTTON_ACTIONActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BEST_TIME_LABEL;
    private javax.swing.JLabel BEST_TIME_X_LABEL;
    private javax.swing.JButton BUTTON_ACTION;
    private javax.swing.JButton BUTTON_MENU;
    private javax.swing.JLabel GAME_STATUS_X_LABEL;
    private javax.swing.JLabel MAP_LEVEL_LABEL;
    private javax.swing.JLabel MAP_LEVEL_X_LABEL;
    private javax.swing.JLabel TIME_LABEL;
    private javax.swing.JLabel TIME_X_LABEL;
    // End of variables declaration//GEN-END:variables
}
