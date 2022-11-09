package hr.algebra.jdialog;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Person;
import hr.algebra.utils.MessageUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class PersonCreator extends javax.swing.JDialog {

    private final Person person;

    /**
     * Creates new form PersonCreator
     *
     * @param parent
     * @param modal
     * @param person
     */
    public PersonCreator(java.awt.Frame parent, boolean modal, Person person) {
        super(parent, modal);
        initComponents();
        this.person = person;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbGroup = new javax.swing.ButtonGroup();
        btnCreate = new javax.swing.JButton();
        txtFieldFirstName = new javax.swing.JTextField();
        rbActor = new javax.swing.JRadioButton();
        txtFieldLastName = new javax.swing.JTextField();
        rbDirector = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        rbGroup.add(rbActor);
        rbActor.setText("Actor");

        rbGroup.add(rbDirector);
        rbDirector.setText("Director");

        jLabel1.setText("Last name:");

        jLabel2.setText("First name:");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtFieldFirstName)
                    .addComponent(txtFieldLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(rbActor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                .addComponent(rbDirector)
                .addGap(110, 110, 110))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbActor)
                    .addComponent(rbDirector))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (rbActor.isSelected()) {
            new Thread(() -> {
                try {
                    repository.createNewPerson(txtFieldFirstName.getText().trim(), txtFieldLastName.getText().trim(), true);
                    close();
                } catch (Exception ex) {
                    Logger.getLogger(PersonCreator.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        } else {
            new Thread(() -> {
                try {
                    repository.createNewPerson(txtFieldFirstName.getText().trim(), txtFieldLastName.getText().trim(), false);
                    close();
                } catch (Exception ex) {
                    Logger.getLogger(PersonCreator.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (rbActor.isSelected()) {
            new Thread(() -> {
                try {
                    person.setFirstName(txtFieldFirstName.getText().trim());
                    person.setLastName(txtFieldLastName.getText().trim());
                    repository.updatePerson(person, true);
                    close();
                } catch (Exception ex) {
                    Logger.getLogger(PersonCreator.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        } else {
            new Thread(() -> {
                try {
                    repository.updatePerson(person, false);
                    close();
                } catch (Exception ex) {
                    Logger.getLogger(PersonCreator.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (rbActor.isSelected()) {
            new Thread(() -> {
                try {
                    repository.deletePerson(person.getId(), true);
                    close();
                } catch (Exception ex) {
                    Logger.getLogger(PersonCreator.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        } else {
            new Thread(() -> {
                try {
                    repository.deletePerson(person.getId(), false);
                    close();
                } catch (Exception ex) {
                    Logger.getLogger(PersonCreator.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            PersonCreator dialog = new PersonCreator(new javax.swing.JFrame(), true, new Person());
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    private Repository repository;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton rbActor;
    private javax.swing.JRadioButton rbDirector;
    private javax.swing.ButtonGroup rbGroup;
    private javax.swing.JTextField txtFieldFirstName;
    private javax.swing.JTextField txtFieldLastName;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initRepository();
            initSelectedPerson();
        } catch (Exception ex) {
            Logger.getLogger(PersonCreator.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable", "Exiting...");
            System.exit(1);
        }
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void initSelectedPerson() {
        if (person != null) {
            txtFieldFirstName.setText(person.getFirstName());
            txtFieldLastName.setText(person.getLastName());
        }
    }

    private void close() {
        MessageUtils.showInformationMessage("Info", "Action successful");
        this.dispose();
    }
    
    public void checkActor() {
        rbActor.setSelected(true);
        rbDirector.setEnabled(false);
    }
    
    public void checkDirector() {
        rbDirector.setSelected(true);
        rbActor.setEnabled(false);
    }
}
