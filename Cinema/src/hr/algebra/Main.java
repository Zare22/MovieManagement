package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.utils.MessageUtils;
import hr.algebra.view.AdminPanel;
import hr.algebra.view.EditPanel;
import hr.algebra.view.HomePanel;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPaneContent = new javax.swing.JTabbedPane();
        loginPanel = new javax.swing.JPanel();
        lblLoginUsername = new javax.swing.JLabel();
        lblLoginPassword = new javax.swing.JLabel();
        txtFieldLoginUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtFieldLoginPassword = new javax.swing.JPasswordField();
        registerPanel = new javax.swing.JPanel();
        lblRegisterUsername = new javax.swing.JLabel();
        lblRegisterPassword = new javax.swing.JLabel();
        txtFieldRegisterUsername = new javax.swing.JTextField();
        btnRegister = new javax.swing.JButton();
        txtFieldRegisterPassword = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        loginPanel.setPreferredSize(new java.awt.Dimension(1300, 750));

        lblLoginUsername.setText("Username:");

        lblLoginPassword.setText("Password:");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblLoginUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLoginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFieldLoginUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFieldLoginPassword))
                .addContainerGap(557, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldLoginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldLoginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnLogin)
                .addContainerGap(374, Short.MAX_VALUE))
        );

        tabbedPaneContent.addTab("Login", loginPanel);

        lblRegisterUsername.setText("Username:");

        lblRegisterPassword.setText("Password:");

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registerPanelLayout = new javax.swing.GroupLayout(registerPanel);
        registerPanel.setLayout(registerPanelLayout);
        registerPanelLayout.setHorizontalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRegisterUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRegisterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFieldRegisterUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFieldRegisterPassword))
                .addContainerGap(557, Short.MAX_VALUE))
        );
        registerPanelLayout.setVerticalGroup(
            registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPanelLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegisterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldRegisterUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(registerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegisterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldRegisterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnRegister)
                .addContainerGap(374, Short.MAX_VALUE))
        );

        tabbedPaneContent.addTab("Register", registerPanel);

        jMenu1.setText("Options");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jMenu1.add(btnLogout);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabbedPaneContent)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabbedPaneContent, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            new Thread(() -> {
                try {
                    boolean[] checkUserCredentials = repository.checkUserCredentials(txtFieldLoginUsername.getText().trim(), txtFieldLoginPassword.getText().trim());
                    if (checkUserCredentials[0]) {
                        MessageUtils.showInformationMessage("Info", "You have logged in successfully");
                        configurePanels(checkUserCredentials);
                        inputFields.forEach(f -> f.setText(""));
                    } else {
                        MessageUtils.showInformationMessage("Info", "Wrong credentials");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Something went wrong!");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        try {
            new Thread(() -> {
                try {
                    String response = repository.createNewUser(txtFieldRegisterUsername.getText().trim(), txtFieldRegisterPassword.getText().trim());
                    MessageUtils.showInformationMessage("Info", response);
                    inputFields.forEach(f -> f.setText(""));
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Something went wrong!");
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        tabbedPaneContent.removeAll();
        tabbedPaneContent.add("Login", loginPanel);
        tabbedPaneContent.add("Register", registerPanel);
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    private Repository repository;
    private List<JTextField> inputFields;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JMenuItem btnLogout;
    private javax.swing.JButton btnRegister;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblLoginPassword;
    private javax.swing.JLabel lblLoginUsername;
    private javax.swing.JLabel lblRegisterPassword;
    private javax.swing.JLabel lblRegisterUsername;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel registerPanel;
    private javax.swing.JTabbedPane tabbedPaneContent;
    private javax.swing.JPasswordField txtFieldLoginPassword;
    private javax.swing.JTextField txtFieldLoginUsername;
    private javax.swing.JPasswordField txtFieldRegisterPassword;
    private javax.swing.JTextField txtFieldRegisterUsername;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initRepository();
            initFields();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unexpected error");
        }
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void configurePanels(boolean[] isSucces) {
        if (isSucces[1]) {
            tabbedPaneContent.removeAll();
            tabbedPaneContent.add("Admin", new AdminPanel());
        } else {
            tabbedPaneContent.removeAll();
            tabbedPaneContent.add("Movie ", new HomePanel());
            tabbedPaneContent.add("Edit", new EditPanel());
        }

    }

    private void initFields() {
        inputFields = Arrays.asList(txtFieldLoginUsername, txtFieldRegisterUsername, txtFieldLoginPassword, txtFieldRegisterPassword);
    }

}
