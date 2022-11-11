package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Movie;
import hr.algebra.model.MovieArchive;
import hr.algebra.model.Person;
import hr.algebra.myinterface.PersonTransferable;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import jakarta.xml.bind.JAXBException;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author Leo
 */
public class EditPanel extends javax.swing.JPanel {

    public EditPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        directorsList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        actorsList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        movieList = new javax.swing.JList<>();
        btnSaveMoviesArchive = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1280, 700));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        directorsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(directorsList);

        actorsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(actorsList);

        movieList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(movieList);

        btnSaveMoviesArchive.setText("Save to archive");
        btnSaveMoviesArchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveMoviesArchiveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(562, 562, 562)
                .addComponent(btnSaveMoviesArchive, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(btnSaveMoviesArchive, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void btnSaveMoviesArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveMoviesArchiveActionPerformed
        try {
            JAXBUtils.save(new MovieArchive(movies), FILENAME);
            MessageUtils.showInformationMessage("Info", "Movies saved");
        } catch (JAXBException ex) {
            MessageUtils.showErrorMessage("Error", "Unable to save movies");
            Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveMoviesArchiveActionPerformed

    private static final String FILENAME = "moviearchive.xml";
    private Repository repository;

    private List<Person> actors;
    private List<Person> directors;
    private List<Movie> movies;

    private DefaultListModel<Person> actorsListModel = new DefaultListModel<>();
    private DefaultListModel<Person> directorsListModel = new DefaultListModel<>();
    private DefaultListModel<Movie> moviesListModel = new DefaultListModel<>();

    private final DefaultListModel[] modelsList = new DefaultListModel[3];
    private final List[] allEntitiesList = new List[3];
    private final JList<Person>[] dragLists = new JList[2];

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<Person> actorsList;
    private javax.swing.JButton btnSaveMoviesArchive;
    private javax.swing.JList<Person> directorsList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<Movie> movieList;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initRepository();
            new Thread(() -> {
                try {
                    initLists();
                    initArrays();
                    initListModels();
                    initDragAndDrop();
                } catch (Exception ex) {
                    Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
                    MessageUtils.showErrorMessage("Unrecoverable", "Exiting...");
                    System.exit(1);
                }
            }).start();

        } catch (Exception ex) {
            Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Data loading issue");
        }
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void initLists() throws Exception {
        actors = repository.selectActors();
        directors = repository.selectDirectors();
        movies = repository.selectMovies();
    }

    private void initListModels() throws InterruptedException {
        loadModels(allEntitiesList, modelsList);
    }

    private void initArrays() {
        modelsList[0] = actorsListModel;
        modelsList[1] = directorsListModel;
        modelsList[2] = moviesListModel;

        allEntitiesList[0] = actors;
        allEntitiesList[1] = directors;
        allEntitiesList[2] = movies;

        dragLists[0] = actorsList;
        dragLists[1] = directorsList;
    }

    private void loadModels(List[] allLists, DefaultListModel[] modelsList) {

        Arrays.asList(modelsList).forEach(modelList -> modelList.clear());

        allLists[0].forEach(actor -> actorsListModel.addElement((Person) actor));
        allLists[1].forEach(director -> directorsListModel.addElement((Person) director));
        allLists[2].forEach(movie -> moviesListModel.addElement((Movie) movie));
        actorsList.setModel(actorsListModel);
        movieList.setModel(moviesListModel);
        directorsList.setModel(directorsListModel);
    }

    private void initDragAndDrop() {
        List<JList<Person>> asList = Arrays.asList(dragLists);
        asList.forEach(list -> list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION));
        asList.forEach(list -> list.setDragEnabled(true));
        asList.forEach(list -> list.setTransferHandler(new ExportTransferHandler()));

        movieList.setDropMode(DropMode.ON);
        movieList.setTransferHandler(new ImportTransferHandler());
    }

    private class ExportTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new PersonTransferable(((JList<Person>)c).getSelectedValue());
        }
    }

    private class ImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Person person = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                Movie movie = moviesListModel.getElementAt(movieList.getDropLocation().getIndex());
                new Thread(() -> {
                    try {
                        String response = "";
                        if (actors.contains(person)) {
                            response = repository.addSinglePersonToMovie(person.getId(), movie.getId(), true);
                        } else {
                            response = repository.addSinglePersonToMovie(person.getId(), movie.getId(), false);
                        }
                        MessageUtils.showInformationMessage("Info", response);
                    } catch (Exception ex) {
                        Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
                        MessageUtils.showErrorMessage("Error", "Pairing with movie issue");
                    }
                }).start();

            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(EditPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }
}
