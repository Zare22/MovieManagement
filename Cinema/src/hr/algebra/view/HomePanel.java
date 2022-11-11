package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.jdialog.PersonCreator;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.IconUtils;
import hr.algebra.utils.MessageUtils;
import hr.algebra.view.model.MovieTableViewModel;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Leo
 */
public class HomePanel extends javax.swing.JPanel {

    public HomePanel() {
        initComponents();
        main = (JFrame) SwingUtilities.getAncestorNamed("Main", this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovies = new javax.swing.JTable();
        imgMovie = new javax.swing.JLabel();
        lblOriginalName = new javax.swing.JLabel();
        lblDirector = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaDescription = new javax.swing.JTextArea();
        btnAddMovie = new javax.swing.JButton();
        btnUpdateMovie = new javax.swing.JButton();
        btnDeleteMovie = new javax.swing.JButton();
        btnChooseImage = new javax.swing.JButton();
        txtFieldOriginalName = new javax.swing.JTextField();
        lblDescriptionError = new javax.swing.JLabel();
        lblOriginalNameError = new javax.swing.JLabel();
        lblDirectorError = new javax.swing.JLabel();
        lblActorError = new javax.swing.JLabel();
        lblImagePath = new javax.swing.JLabel();
        lblImagePathError = new javax.swing.JLabel();
        comboBoxDirector = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        actorsList = new javax.swing.JList<>();
        comboBoxActor = new javax.swing.JComboBox<>();
        btnCrudDirector = new javax.swing.JButton();
        btnCrudActor = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1288, 768));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tblMovies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMoviesMouseClicked(evt);
            }
        });
        tblMovies.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblMoviesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblMovies);

        imgMovie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/no_image.png"))); // NOI18N
        imgMovie.setMaximumSize(new java.awt.Dimension(395, 286));
        imgMovie.setMinimumSize(new java.awt.Dimension(395, 286));

        lblOriginalName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOriginalName.setText("Title:");
        lblOriginalName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblDirector.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDirector.setText("Director:");
        lblDirector.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Actors:");

        jLabel2.setText("Description:");

        txtAreaDescription.setColumns(20);
        txtAreaDescription.setLineWrap(true);
        txtAreaDescription.setRows(5);
        txtAreaDescription.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtAreaDescription);

        btnAddMovie.setText("Add new movie");
        btnAddMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMovieActionPerformed(evt);
            }
        });

        btnUpdateMovie.setText("Update selected movie");
        btnUpdateMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMovieActionPerformed(evt);
            }
        });

        btnDeleteMovie.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleteMovie.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDeleteMovie.setText("Delete selected movie");
        btnDeleteMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMovieActionPerformed(evt);
            }
        });

        btnChooseImage.setText("Change image");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

        lblDescriptionError.setForeground(java.awt.Color.red);

        lblOriginalNameError.setForeground(java.awt.Color.red);

        lblDirectorError.setForeground(java.awt.Color.red);

        lblActorError.setForeground(java.awt.Color.red);

        lblImagePath.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N

        lblImagePathError.setForeground(java.awt.Color.red);

        actorsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        actorsList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                actorsListKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(actorsList);

        comboBoxActor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboBoxActorKeyReleased(evt);
            }
        });

        btnCrudDirector.setText("CRUD Director");
        btnCrudDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudDirectorActionPerformed(evt);
            }
        });

        btnCrudActor.setText("CRUD Actor");
        btnCrudActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudActorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdateMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnChooseImage)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblImagePath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(imgMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblOriginalName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblDirector, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                                    .addComponent(lblImagePathError, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboBoxDirector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFieldOriginalName)
                                    .addComponent(jScrollPane4)
                                    .addComponent(comboBoxActor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDirectorError, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblOriginalNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblActorError, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCrudActor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCrudDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblOriginalName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFieldOriginalName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblOriginalNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBoxDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblDirectorError, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblActorError, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(imgMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 54, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnChooseImage, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(lblImagePath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblImagePathError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(comboBoxActor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddMovie)
                            .addComponent(btnUpdateMovie))
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrudDirector)
                            .addComponent(btnCrudActor))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        File selectedImage = FileUtils.uploadFile("Images", "jpg", "jpeg", "png");
        if (selectedImage == null) {
            return;
        }

        lblImagePath.setText(selectedImage.getAbsolutePath());
        setMovieIcon(imgMovie, selectedImage);
    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void btnAddMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMovieActionPerformed
        if (!formValid()) {
            return;
        }

        try {
            //Spremanje prije nego što se okine
            String localPicturePath = uploadPicture();
            Movie movie = new Movie(txtFieldOriginalName.getText().trim(),
                    ((Person) comboBoxDirector.getSelectedItem()),
                    selectedMovieActors,
                    txtAreaDescription.getText().trim(),
                    localPicturePath);

            new Thread(() -> {
                try {
                    repository.createMovieAndDirector(movie);
                    tableModel.setMovies(repository.selectMovies());
                } catch (Exception ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();

        } catch (IOException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "There was a problem with adding a movie");
        }

        clearForm();
    }//GEN-LAST:event_btnAddMovieActionPerformed

    private void btnUpdateMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMovieActionPerformed
        if (selectedMovie == null) {
            MessageUtils.showInformationMessage("Info", "Please choose a movie to update");
            return;
        }

        if (!formValid()) {
            return;
        }

        try {
            if (!lblImagePath.getText().equals(selectedMovie.getImagePath())) {
                if (selectedMovie.getImagePath() != null) {
                    Files.deleteIfExists(Paths.get(selectedMovie.getImagePath()));
                }
                String localPicturePath = uploadPicture();
                selectedMovie.setImagePath(localPicturePath);
            }

            selectedMovie.setOriginalName(txtFieldOriginalName.getText().trim());
            selectedMovie.setDirectorID(comboBoxDirector.getItemAt(comboBoxDirector.getSelectedIndex()).getId());
            selectedMovie.setDescription(txtAreaDescription.getText().trim());
            selectedMovie.setActors(selectedMovieActors);

            new Thread(() -> {
                try {
                    repository.updateMovie(selectedMovie.getId(), selectedMovie);
                    tableModel.setMovies(repository.selectMovies());
                } catch (Exception ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();

            clearForm();
        } catch (IOException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "There was a problem with update");
        }
    }//GEN-LAST:event_btnUpdateMovieActionPerformed

    private void btnDeleteMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMovieActionPerformed
        if (selectedMovie == null) {
            MessageUtils.showInformationMessage("Info", "Please select a movie");
            return;
        }

        if (MessageUtils.showConfirmDialog("Delete movie", "Are you sure you want to delete selected movie?") == JOptionPane.YES_OPTION) {
            try {
                if (selectedMovie.getImagePath() != null) {
                    Files.deleteIfExists(Paths.get(selectedMovie.getImagePath()));
                }

                new Thread(() -> {
                    try {
                        repository.deleteMovie(selectedMovie.getId());
                        tableModel.setMovies(repository.selectMovies());
                    } catch (Exception ex) {
                        Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                        MessageUtils.showErrorMessage("Error", "Data loading issue");
                    }
                }).start();

                clearForm();
            } catch (IOException ex) {
                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showInformationMessage("Info", "Movie deletion went wrong");
            }
        }
    }//GEN-LAST:event_btnDeleteMovieActionPerformed

    private void tblMoviesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMoviesMouseClicked
        showMovie();
    }//GEN-LAST:event_tblMoviesMouseClicked

    private void tblMoviesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblMoviesKeyReleased
        showMovie();
    }//GEN-LAST:event_tblMoviesKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void comboBoxActorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboBoxActorKeyReleased
        if (comboBoxActor.isFocusOwner() && evt.getKeyCode() == KeyEvent.VK_ENTER) {
            selectedMovieActors.add((Person) comboBoxActor.getSelectedItem());
            comboBoxActor.setSelectedIndex(-1);
            loadActorsListModel(selectedMovieActors);
        }
    }//GEN-LAST:event_comboBoxActorKeyReleased

    private void btnCrudDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudDirectorActionPerformed
        PersonCreator personCreator = new PersonCreator(main, true, (Person) comboBoxDirector.getSelectedItem());
        personCreator.checkDirector();
        personCreator.setVisible(true);
        personCreator.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                fillActorsToComboBox();
            }

        });
    }//GEN-LAST:event_btnCrudDirectorActionPerformed

    private void btnCrudActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudActorActionPerformed
        PersonCreator personCreator = new PersonCreator(main, true, (Person) comboBoxActor.getSelectedItem());
        personCreator.checkActor();
        personCreator.setVisible(true);
        personCreator.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                fillActorsToComboBox();
            }

        });
    }//GEN-LAST:event_btnCrudActorActionPerformed

    private void actorsListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_actorsListKeyReleased
        if (!actorsList.isSelectionEmpty() && evt.getKeyCode() == KeyEvent.VK_DELETE) {
            comboBoxActor.addItem(actorsList.getSelectedValue());
            selectedMovieActors.remove(actorsList.getSelectedValue());
            loadActorsListModel(selectedMovieActors);
        }
    }//GEN-LAST:event_actorsListKeyReleased

    private final JFrame main;

    private static final String DIR = "assets";
    private Repository repository;

    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;

    private Movie selectedMovie;
    private MovieTableViewModel tableModel;

    private List<Person> selectedMovieActors;
    private DefaultListModel<Person> actorsListModel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Person> actorsList;
    private javax.swing.JButton btnAddMovie;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnCrudActor;
    private javax.swing.JButton btnCrudDirector;
    private javax.swing.JButton btnDeleteMovie;
    private javax.swing.JButton btnUpdateMovie;
    private javax.swing.JComboBox<Person> comboBoxActor;
    private javax.swing.JComboBox<Person> comboBoxDirector;
    private javax.swing.JLabel imgMovie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblActorError;
    private javax.swing.JLabel lblDescriptionError;
    private javax.swing.JLabel lblDirector;
    private javax.swing.JLabel lblDirectorError;
    private javax.swing.JLabel lblImagePath;
    private javax.swing.JLabel lblImagePathError;
    private javax.swing.JLabel lblOriginalName;
    private javax.swing.JLabel lblOriginalNameError;
    private javax.swing.JTable tblMovies;
    private javax.swing.JTextArea txtAreaDescription;
    private javax.swing.JTextField txtFieldOriginalName;
    // End of variables declaration//GEN-END:variables

    private void setMovieIcon(JLabel imgMovie, File selectedImage) {
        try {
            imgMovie.setIcon(IconUtils.createIcon(selectedImage, imgMovie.getWidth(), imgMovie.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Something went wrong with image upload!");
        }
    }

    private void init() {
        try {
            initValidation();
            initRepository();
            initMovieTable();
            fillDirectorsToComboBox();
            fillActorsToComboBox();
            actorsListModel = new DefaultListModel<>();
            selectedMovieActors = new ArrayList<>();

        } catch (Exception ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable", "Exiting...");
            System.exit(1);
        }
    }

    private void initValidation() {
        validationFields = Arrays.asList(txtFieldOriginalName, txtAreaDescription);
        errorLabels = Arrays.asList(lblOriginalNameError, lblDescriptionError);
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void initMovieTable() throws Exception {
        tblMovies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblMovies.setAutoCreateRowSorter(true);
        tblMovies.setRowHeight(25);

        new Thread(() -> {
            try {
                tableModel = new MovieTableViewModel(repository.selectMovies());
                if (tableModel != null) {
                    tblMovies.setModel(tableModel);
                }
            } catch (Exception ex) {
                Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Data loading issue");
            }
        }).start();
    }

    private boolean formValid() {
        boolean ok = true;

        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
        }

        if (lblImagePath.getText().isEmpty() || comboBoxDirector.getSelectedIndex() == -1) {
            lblImagePathError.setText("X");
            lblDirectorError.setText("X");
            ok = false;
        } else {
            lblImagePathError.setText("");
            lblDirectorError.setText("");
        }
        return ok;
    }

    private void setImage(JLabel label, File file) {
        try {
            label.setIcon(IconUtils.createIcon(file, label.getWidth(), label.getHeight()));
        } catch (IOException ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Info", "Unable to set image");
        }
    }

    private String uploadPicture() throws IOException {
        String path = lblImagePath.getText();
        String extension = path.substring(path.lastIndexOf("."));
        String pictureName = UUID.randomUUID() + extension;
        String localPath = DIR + File.separator + pictureName;
        FileUtils.copy(path, localPath);
        return localPath;
    }

    private void clearForm() {
        validationFields.forEach(e -> e.setText(""));
        errorLabels.forEach(e -> e.setText(""));
        comboBoxDirector.setSelectedIndex(-1);
        lblImagePath.setText("");
        imgMovie.setIcon(new ImageIcon(getClass().getResource("/assets/no_image.png")));
        selectedMovie = null;
        actorsListModel.clear();
        actorsList.setModel(actorsListModel);
    }

    private void showMovie() {
        clearForm();
        int selectedRow = tblMovies.getSelectedRow();
        int rowIndex = tblMovies.convertRowIndexToModel(selectedRow);
        int selectedMovieID = (int) tableModel.getValueAt(rowIndex, 0);

        try {
            new Thread(() -> {
                try {
                    Optional<Movie> optionalMovie = repository.selectMovie(selectedMovieID);
                    if (optionalMovie.isPresent()) {
                        selectedMovie = optionalMovie.get();
                        fillForm(selectedMovie);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();

        } catch (Exception ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to select movie");
        }
    }

    private void fillForm(Movie movie) {

        txtFieldOriginalName.setText(movie.getOriginalName());
        comboBoxDirector.setSelectedItem(movie.getDirector());
        txtAreaDescription.setText(movie.getDescription());

        try {
            new Thread(() -> {
                try {
                    selectedMovieActors = repository.selectMovieActors(movie.getId());
                    if (!(selectedMovieActors == null)) {
                        loadActorsListModel(selectedMovieActors);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();

        } catch (Exception ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Can't load actors list");
        }

        if (movie.getImagePath() != null && Files.exists(Paths.get(movie.getImagePath()))) {
            lblImagePath.setText(movie.getImagePath());
            setImage(imgMovie, new File(movie.getImagePath()));
        }

    }

    private void fillDirectorsToComboBox() {
        try {
            new Thread(() -> {
                try {
                    List<Person> selectedDirectors = repository.selectDirectors();
                    selectedDirectors.forEach(d -> comboBoxDirector.addItem(d));
                    comboBoxDirector.setSelectedIndex(-1);
                } catch (Exception ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();

        } catch (Exception ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Problem with loading directors");
        }
    }

    private void fillActorsToComboBox() {

        try {
            new Thread(() -> {
                try {
                    List<Person> selectedActors = repository.selectActors();
                    selectedActors.forEach(a -> comboBoxActor.addItem(a));
                    comboBoxActor.setSelectedIndex(-1);
                } catch (Exception ex) {
                    Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Error", "Data loading issue");
                }
            }).start();

        } catch (Exception ex) {
            Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Problem with loading actors");
        }
    }

    private void loadActorsListModel(List<Person> selectedMovieActors) {
        actorsListModel.clear();
        selectedMovieActors.forEach(actorsListModel::addElement);
        selectedMovieActors.forEach(a -> comboBoxActor.removeItem(a));
        actorsList.setModel(actorsListModel);
    }

}
