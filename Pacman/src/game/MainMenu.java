package game;
public class MainMenu extends javax.swing.JPanel {
    
    public MainMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LABEL_TITLE = new javax.swing.JLabel();
        LABEL_LOGO = new javax.swing.JLabel();
        LABEL_SELECT_MAP = new javax.swing.JLabel();
        BUTTON_BLUE_CONTINUE = new javax.swing.JButton();
        BUTTON_BLUE_NEW = new javax.swing.JButton();
        BUTTON_RED_CONTINUE = new javax.swing.JButton();
        BUTTON_RED_NEW = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(700, 496));

        LABEL_TITLE.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        LABEL_TITLE.setForeground(new java.awt.Color(255, 102, 102));
        LABEL_TITLE.setText("WELCOME TO PACMAN");

        LABEL_LOGO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/cover.jpg"))); // NOI18N
        LABEL_LOGO.setText("jLabel3");

        LABEL_SELECT_MAP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LABEL_SELECT_MAP.setForeground(new java.awt.Color(255, 102, 102));
        LABEL_SELECT_MAP.setText("Select Map");

        BUTTON_BLUE_CONTINUE.setBackground(new java.awt.Color(51, 153, 255));
        BUTTON_BLUE_CONTINUE.setText("CONTINUE LAST GAME");
        BUTTON_BLUE_CONTINUE.setAlignmentY(0.0F);
        BUTTON_BLUE_CONTINUE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BUTTON_BLUE_CONTINUE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_BLUE_CONTINUEActionPerformed(evt);
            }
        });

        BUTTON_BLUE_NEW.setBackground(new java.awt.Color(51, 153, 255));
        BUTTON_BLUE_NEW.setText("START NEW GAME");
        BUTTON_BLUE_NEW.setAlignmentY(0.0F);
        BUTTON_BLUE_NEW.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BUTTON_BLUE_NEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_BLUE_NEWActionPerformed(evt);
            }
        });

        BUTTON_RED_CONTINUE.setBackground(new java.awt.Color(255, 153, 153));
        BUTTON_RED_CONTINUE.setText("CONTINUE LAST GAME");
        BUTTON_RED_CONTINUE.setAlignmentY(0.0F);
        BUTTON_RED_CONTINUE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BUTTON_RED_CONTINUE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_RED_CONTINUEActionPerformed(evt);
            }
        });

        BUTTON_RED_NEW.setBackground(new java.awt.Color(255, 153, 153));
        BUTTON_RED_NEW.setText("START NEW GAME");
        BUTTON_RED_NEW.setAlignmentY(0.0F);
        BUTTON_RED_NEW.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BUTTON_RED_NEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUTTON_RED_NEWActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/map_red.jpg"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/map_blue.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(LABEL_LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(LABEL_SELECT_MAP))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(BUTTON_RED_CONTINUE)
                                        .addComponent(BUTTON_RED_NEW, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(BUTTON_BLUE_CONTINUE)
                                        .addComponent(BUTTON_BLUE_NEW, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(9, 9, 9))
                                .addComponent(LABEL_TITLE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2))))))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(LABEL_LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LABEL_TITLE)
                .addGap(12, 12, 12)
                .addComponent(LABEL_SELECT_MAP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BUTTON_RED_CONTINUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BUTTON_RED_NEW))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(BUTTON_BLUE_CONTINUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BUTTON_BLUE_NEW)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BUTTON_BLUE_CONTINUEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_BLUE_CONTINUEActionPerformed
        // TODO add your handling code here:
        MainClass.currentMapType = 1;
        MainClass.currentLevel = MainClass.loadCurrentLevel(1);
        MainClass.StartGame(MainClass.MAP_BLUE_URL[MainClass.currentLevel], MainClass.BLUE_COLOR);
    }//GEN-LAST:event_BUTTON_BLUE_CONTINUEActionPerformed

    private void BUTTON_RED_CONTINUEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_RED_CONTINUEActionPerformed
        // TODO add your handling code here:
        MainClass.currentMapType = 0;
        MainClass.currentLevel = MainClass.loadCurrentLevel(0);
        MainClass.StartGame(MainClass.MAP_RED_URL[MainClass.currentLevel], MainClass.RED_COLOR);
    }//GEN-LAST:event_BUTTON_RED_CONTINUEActionPerformed

    private void BUTTON_RED_NEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_RED_NEWActionPerformed
        // TODO add your handling code here:
        MainClass.currentMapType = 0;
        MainClass.currentLevel = 0;
        MainClass.StartGame(MainClass.MAP_RED_URL[0], MainClass.RED_COLOR);
    }//GEN-LAST:event_BUTTON_RED_NEWActionPerformed

    private void BUTTON_BLUE_NEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUTTON_BLUE_NEWActionPerformed
        // TODO add your handling code here:
        MainClass.currentMapType = 1;
        MainClass.currentLevel = 0;
        MainClass.StartGame(MainClass.MAP_BLUE_URL[0], MainClass.BLUE_COLOR);
    }//GEN-LAST:event_BUTTON_BLUE_NEWActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BUTTON_BLUE_CONTINUE;
    private javax.swing.JButton BUTTON_BLUE_NEW;
    private javax.swing.JButton BUTTON_RED_CONTINUE;
    private javax.swing.JButton BUTTON_RED_NEW;
    private javax.swing.JLabel LABEL_LOGO;
    private javax.swing.JLabel LABEL_SELECT_MAP;
    private javax.swing.JLabel LABEL_TITLE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
