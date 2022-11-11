package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Movie;
import hr.algebra.parsers.rss.MovieParser;
import hr.algebra.utils.MessageUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Leo
 */
public class AdminPanel extends javax.swing.JPanel {
    
    public AdminPanel() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnUploadMovies = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        isMovie = new javax.swing.JList<>();
        btnDeleteAllData = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setPreferredSize(new java.awt.Dimension(1250, 700));

        btnUploadMovies.setText("Upload movies");
        btnUploadMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadMoviesActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(isMovie);

        btnDeleteAllData.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleteAllData.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDeleteAllData.setText("Delete all data");
        btnDeleteAllData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1236, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUploadMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 664, Short.MAX_VALUE)
                        .addComponent(btnDeleteAllData, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteAllData, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUploadMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadMoviesActionPerformed
        try {
            new Thread(() -> {
                try {
                    List<Movie> movies = MovieParser.parse();
                    repository.createMovies(movies);
                    loadModel();
                } catch (IOException ex) {
                    Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                } catch (XMLStreamException ex) {
                    Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                } catch (Exception ex) {
                    Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();
        } catch (Exception ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Greška pri uploadu");
        }
    }//GEN-LAST:event_btnUploadMoviesActionPerformed

    private void btnDeleteAllDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllDataActionPerformed
        try {
            new Thread(() -> {
                if (MessageUtils.showConfirmDialog("Delete all data", "Are you sure you want to delete all data") == JOptionPane.YES_OPTION) {
                    try {
                        repository.deleteAllData();
                        for (File file : new File("./assets").listFiles()) {
                            if (!file.isDirectory()) {
                                file.delete();
                            }
                        }
                        loadModel();
                    } catch (Exception ex) {
                        Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                        MessageUtils.showErrorMessage("Error", "Data loading issue");
                    }
                }
            }).start();

        } catch (Exception ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteAllDataActionPerformed

    private Repository repository;
    private DefaultListModel<Movie> model;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteAllData;
    private javax.swing.JButton btnUploadMovies;
    private javax.swing.JList<Movie> isMovie;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            repository = RepositoryFactory.getRepository();
            model = new DefaultListModel<>();
            loadModel();
        } catch (Exception ex) {
            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadModel() throws Exception {
        model.clear();
        new Thread(() -> {
            try {
                List<Movie> movies = repository.selectMovies();
                movies.forEach(model::addElement);
                isMovie.setModel(model);
            } catch (Exception ex) {
                Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Data loading issue");
            }
        }).start();
    }
}
