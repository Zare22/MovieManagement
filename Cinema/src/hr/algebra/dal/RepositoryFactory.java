package hr.algebra.dal;

import hr.algebra.dal.sql.SQLRepository;

/**
 *
 * @author Leo
 */
public class RepositoryFactory {

    public RepositoryFactory() {
    }

    public static Repository getRepository() throws Exception {
        return new SQLRepository();
    }
}
