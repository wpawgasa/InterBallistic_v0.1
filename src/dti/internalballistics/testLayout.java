/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dti.internalballistics;

/**
 *
 * @author amabird
 */
public class testLayout extends javax.swing.JFrame {

    /**
     * Creates new form testLayout
     */
    public testLayout() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        burningDistancePanel = new javax.swing.JPanel();
        addBurningRowBt = new javax.swing.JButton();
        burningDistanceScrollPanel = new javax.swing.JScrollPane();
        burningDistanceTable = new javax.swing.JTable();
        removeBurningRowBt = new javax.swing.JButton();
        loadPropDataBt = new javax.swing.JButton();
        savePropDataBt = new javax.swing.JButton();
        viewControlPanel = new javax.swing.JPanel();
        arrowUpButton = new javax.swing.JButton();
        arrowRightButton = new javax.swing.JButton();
        arrowLeftButton = new javax.swing.JButton();
        arrowDownButton = new javax.swing.JButton();
        centerButton = new javax.swing.JButton();
        zoomInPropellantBt = new javax.swing.JButton();
        zoomOutPropellantBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addBurningRowBt.setText("Add new row");
        addBurningRowBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBurningRowBtActionPerformed(evt);
            }
        });

        burningDistanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Distance (mm)", "Peripheral (mm)", "Port Area (mm^2)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        burningDistanceTable.setShowVerticalLines(false);
        burningDistanceTable.setSurrendersFocusOnKeystroke(true);
        burningDistanceScrollPanel.setViewportView(burningDistanceTable);

        removeBurningRowBt.setText("Remove row");
        removeBurningRowBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBurningRowBtActionPerformed(evt);
            }
        });

        loadPropDataBt.setText("Load propellant geometric data from file");
        loadPropDataBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPropDataBtActionPerformed(evt);
            }
        });

        savePropDataBt.setText("Save propellant geometric data");

        javax.swing.GroupLayout burningDistancePanelLayout = new javax.swing.GroupLayout(burningDistancePanel);
        burningDistancePanel.setLayout(burningDistancePanelLayout);
        burningDistancePanelLayout.setHorizontalGroup(
            burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(burningDistancePanelLayout.createSequentialGroup()
                .addGroup(burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(burningDistanceScrollPanel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(burningDistancePanelLayout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addComponent(addBurningRowBt)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(removeBurningRowBt))
                        .addGroup(burningDistancePanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(savePropDataBt)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(loadPropDataBt, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        burningDistancePanelLayout.setVerticalGroup(
            burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(burningDistancePanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savePropDataBt)
                    .addComponent(loadPropDataBt))
                .addGap(18, 18, 18)
                .addComponent(burningDistanceScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(burningDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBurningRowBt)
                    .addComponent(removeBurningRowBt))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", burningDistancePanel);

        arrowUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-up.png"))); // NOI18N
        arrowUpButton.setToolTipText("");

        arrowRightButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-right.png"))); // NOI18N
        arrowRightButton.setToolTipText("");
        arrowRightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrowRightButtonActionPerformed(evt);
            }
        });

        arrowLeftButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-left.png"))); // NOI18N
        arrowLeftButton.setToolTipText("");

        arrowDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/arrow-down.png"))); // NOI18N
        arrowDownButton.setToolTipText("");

        centerButton.setToolTipText("");

        zoomInPropellantBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/zoom_in.png"))); // NOI18N
        zoomInPropellantBt.setToolTipText("");
        zoomInPropellantBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomInPropellantBtActionPerformed(evt);
            }
        });

        zoomOutPropellantBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dti/icon/zoom_out.png"))); // NOI18N
        zoomOutPropellantBt.setToolTipText("");
        zoomOutPropellantBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomOutPropellantBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewControlPanelLayout = new javax.swing.GroupLayout(viewControlPanel);
        viewControlPanel.setLayout(viewControlPanelLayout);
        viewControlPanelLayout.setHorizontalGroup(
            viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewControlPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewControlPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(zoomInPropellantBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomOutPropellantBt)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(viewControlPanelLayout.createSequentialGroup()
                        .addComponent(arrowLeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewControlPanelLayout.createSequentialGroup()
                                .addComponent(centerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(arrowRightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                            .addGroup(viewControlPanelLayout.createSequentialGroup()
                                .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(arrowDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(arrowUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        viewControlPanelLayout.setVerticalGroup(
            viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewControlPanelLayout.createSequentialGroup()
                .addComponent(arrowUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewControlPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arrowLeftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewControlPanelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arrowRightButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(centerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addComponent(arrowDownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(viewControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zoomInPropellantBt)
                    .addComponent(zoomOutPropellantBt))
                .addContainerGap(629, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(viewControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void arrowRightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrowRightButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arrowRightButtonActionPerformed

    private void zoomInPropellantBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomInPropellantBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zoomInPropellantBtActionPerformed

    private void zoomOutPropellantBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomOutPropellantBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zoomOutPropellantBtActionPerformed

    private void loadPropDataBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPropDataBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadPropDataBtActionPerformed

    private void addBurningRowBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBurningRowBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addBurningRowBtActionPerformed

    private void removeBurningRowBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBurningRowBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeBurningRowBtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(testLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testLayout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testLayout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBurningRowBt;
    private javax.swing.JButton arrowDownButton;
    private javax.swing.JButton arrowLeftButton;
    private javax.swing.JButton arrowRightButton;
    private javax.swing.JButton arrowUpButton;
    private javax.swing.JPanel burningDistancePanel;
    private javax.swing.JScrollPane burningDistanceScrollPanel;
    private javax.swing.JTable burningDistanceTable;
    private javax.swing.JButton centerButton;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton loadPropDataBt;
    private javax.swing.JButton removeBurningRowBt;
    private javax.swing.JButton savePropDataBt;
    private javax.swing.JPanel viewControlPanel;
    private javax.swing.JButton zoomInPropellantBt;
    private javax.swing.JButton zoomOutPropellantBt;
    // End of variables declaration//GEN-END:variables
}
