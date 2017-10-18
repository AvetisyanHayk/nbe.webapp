package be.hayk.testapp.data;

import javax.sql.DataSource;

/**
 *
 * @author Hayk
 */
abstract class AbstractRepository {

    public static final String JNDI_NAME = "jdbc/testappdb";
    
    protected DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
