
package password.manager.main;

import java.sql.*;



public class LoginUI extends javax.swing.JFrame {

    

    public LoginUI() {
        initComponents();
        setLocationRelativeTo(null); //center window ui
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mlogin = new javax.swing.JButton();
        mkey = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        lprompt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        mlogin.setText("login");
        mlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mloginActionPerformed(evt);
            }
        });

        mkey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mkeyActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Login");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(429, 429, 429)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mlogin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mkey, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addComponent(lprompt, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addGap(120, 120, 120)
                .addComponent(mkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(mlogin)
                .addGap(68, 68, 68)
                .addComponent(lprompt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
 ///////////////////////////////////////////////////////////////////////////////////////////////    
    private void mloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mloginActionPerformed
    
    //get password (master key) from user input    
    String inputPassword = new String(mkey.getPassword());
    
    //if empty  input prompt user "enter pass"
    if (inputPassword.isEmpty()) {
        lprompt.setText("Please enter a master password.");
        return;
    }

    SQLITEDataBaseHandler dbHandler = new SQLITEDataBaseHandler(); //class instansiation to use within this method
//if master key doesnt exist yet set it up
    if (!dbHandler.isMasterKeySet()) {
        // First-time setup or db is deleted
        boolean success = dbHandler.setMasterKey(inputPassword); // we get users input and set it as the pass key
        if (success) { ///if success prompt user
            lprompt.setText("Master key set successfully! Please enter master key again :)");
            mkey.setText("");// clears the txtpass field
        } else { //if failed prompt
            lprompt.setText("Failed to set master key.");
            mkey.setText("");// clears the txtpass field
        }
    } else { //if there is a master key already--->
        // Validate login // validates it in the Sqllite databasehandler class then returns  either true or false 
        if (dbHandler.validateMasterKey(inputPassword)) { //validate the users input (pass key) (if true prompr user)
            lprompt.setText("Login successful."); 

            //then open Password Manager UI
            PasswordManagerUI pmUI = new PasswordManagerUI();//class instansiation
            pmUI.setVisible(true); //shows pswmanager gui window
            this.dispose(); //close the loginui
        } else { //if users input is wrong then (false) prompt user
            lprompt.setText("Invalid master key. Please try again.");
            mkey.setText("");// clears the txtpass field
        }
    } 
    }//GEN-LAST:event_mloginActionPerformed
   
    
    
    
    
    
    
    
    
 ///////////////////////////////////////////////////////////////////////////////////////////////    
    private void mkeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mkeyActionPerformed
    


// TODO add your handling code here:
    }//GEN-LAST:event_mkeyActionPerformed

   
    
    
    
    
    
    
    //removed access modifier (public) 
    //reason: should not run directly || can only be ran in the main class
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
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lprompt;
    private javax.swing.JPasswordField mkey;
    private javax.swing.JButton mlogin;
    // End of variables declaration//GEN-END:variables
}
