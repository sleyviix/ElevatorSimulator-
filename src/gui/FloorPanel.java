/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author User1
 */
public class FloorPanel extends javax.swing.JPanel {

    /**
     * Creates new form FloorPanel
     * @param floorNum
     */
    public FloorPanel( int floorNum ) {
        initComponents();
        ((TitledBorder) super.getBorder()).setTitle( floorNum == 0? "Ground Floor" : "Floor " + floorNum);
    }
    
    public void setIdleList( java.util.List list ) {
        idleList.setListData( list.toArray() );
        idleLbl.setText( "Idle Users (" + list.size() + ")" );
    }
    
    public void setGoingUpList( java.util.List list ) {
        goingUpList.setListData( list.toArray() );
        goingUpLbl.setText( "Going Up (" + list.size() + ")" );
    }
    
    public void setGoingDownList( java.util.List list ) {
        goingDown.setListData( list.toArray() );
        goingDownLbl.setText( "Going Down (" + list.size() + ")" );
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idleLbl = new javax.swing.JLabel();
        idleScrollPane = new javax.swing.JScrollPane();
        idleList = new javax.swing.JList<>();
        goingUpLbl = new javax.swing.JLabel();
        goingUpScrollPane = new javax.swing.JScrollPane();
        goingUpList = new javax.swing.JList<>();
        goingDownLbl = new javax.swing.JLabel();
        goingDownScrollPane = new javax.swing.JScrollPane();
        goingDown = new javax.swing.JList<>();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Floor X"));

        idleLbl.setText("Idle Users (0)");

        idleScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        idleList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        idleScrollPane.setViewportView(idleList);

        goingUpLbl.setText("Going Up (0)");

        goingUpScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        goingUpList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        goingUpScrollPane.setViewportView(goingUpList);

        goingDownLbl.setText("Going Down (0)");

        goingDownScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        goingDown.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        goingDownScrollPane.setViewportView(goingDown);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(idleLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goingUpScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(goingUpLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goingDownLbl)
                    .addComponent(goingDownScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idleLbl)
                    .addComponent(goingUpLbl)
                    .addComponent(goingDownLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(goingUpScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(goingDownScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Object> goingDown;
    private javax.swing.JLabel goingDownLbl;
    private javax.swing.JScrollPane goingDownScrollPane;
    private javax.swing.JLabel goingUpLbl;
    private javax.swing.JList<Object> goingUpList;
    private javax.swing.JScrollPane goingUpScrollPane;
    private javax.swing.JLabel idleLbl;
    private javax.swing.JList<Object> idleList;
    private javax.swing.JScrollPane idleScrollPane;
    // End of variables declaration//GEN-END:variables
}