/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.border.TitledBorder;

/**
 *
 * @author User1
 */
public class ElevatorPanel extends javax.swing.JPanel {
    private int floor = 0;
    /**
     * Creates new form ElevatorPanel
     */
    public ElevatorPanel() {
        initComponents();
    }
    
    public void updateInfo( int newFloor, boolean goingUp, boolean doorsOpen ) { 
        floor = newFloor;
        String title = "Elevator (" + (newFloor == 0? "Ground Floor" : "Floor " + newFloor) + " | Going " + (goingUp? "Up" : "Down") + " | Doors " + (doorsOpen? "Open" : "Shut") + ")";
        ((TitledBorder) super.getBorder()).setTitle( title );
    }
    
    public int getFloor() { 
        return floor;
    }
    
    public void setPassengerList( java.util.List list ) { 
        usersList.setListData( list.toArray() );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usersLbl = new javax.swing.JLabel();
        idleScrollPane = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Elevator (Floor 0)"));

        usersLbl.setText("Passengers");

        usersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        idleScrollPane.setViewportView(usersList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usersLbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(idleScrollPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usersLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane idleScrollPane;
    private javax.swing.JLabel usersLbl;
    private javax.swing.JList<Object> usersList;
    // End of variables declaration//GEN-END:variables
}
