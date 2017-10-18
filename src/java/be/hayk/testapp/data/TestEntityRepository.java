package be.hayk.testapp.data;

import be.hayk.testapp.domain.TestEntity;
import be.hwyk.testapp.ex.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayk
 */
public class TestEntityRepository extends AbstractRepository {

    private static final String SQL = "SELECT * FROM testentity";
    private static final String SQL_READ = SQL + " WHERE id = ?";
    private static final String SQL_FIND_ALL = SQL + " ORDER BY name";

    public TestEntity read(long id) {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_READ)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return build(resultSet);
                }
            }
            return null;
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
    }

    public List<TestEntity> findAll() {
        List<TestEntity> entities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL)) {
            while (resultSet.next()) {
                entities.add(build(resultSet));
            }
        } catch (SQLException ex) {
            throw new DBException(ex);
        }
        return entities;
    }

    private TestEntity build(ResultSet resultSet)
            throws SQLException {
        return new TestEntity(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description")
        );
    }
}
