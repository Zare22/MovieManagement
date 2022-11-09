package hr.algebra.view.model;

import hr.algebra.model.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Leo
 */
public class MovieTableViewModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"ID", "Title", "Picture", "Director", "Main Actor"};

    private List<Movie> movies;

    public MovieTableViewModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getOriginalName();
            case 2:
                return movies.get(rowIndex).getImagePath();
            case 3:
                if (movies.get(rowIndex).getDirector() != null) {
                    return movies.get(rowIndex).getDirector().toString();
                }
                return "";
            case 4:
                if (movies.get(rowIndex).getActors().isEmpty()) {
                    return "";
                }
                return movies.get(rowIndex).getActors().get(0);

        }
        throw new RuntimeException("No such column");
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

}
