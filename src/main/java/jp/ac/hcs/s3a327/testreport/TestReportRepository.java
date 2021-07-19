package jp.ac.hcs.s3a327.testreport;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * 受験報告情報のデータを管理する.
 * - TestReportテーブル
 */
@Repository
public class TestReportRepository {

	/** SQL 全件取得（ID昇順） */
	private static final String SQL_SELECT_ALL = "SELECT * FROM testreport order by id";

//	/** SQL 1件追加 */
//	private static final String SQL_INSERT_ONE = "INSERT INTO testreport(user_id, encrypted_password, user_name, darkmode, role) VALUES(?, ?, ?, ?, ?)";
//
//	/** SQL 1件更新 */
//	private static final String SQL_UPDATE_ONE_WITH_PASSWORD = "UPDATE testreport SET encrypted_password = ?, user_name = ?, role = ? WHERE user_id = ?";

	/** SQL 1件削除 */
	private static final String SQL_DELETE_ONE = "DELETE FROM testreport WHERE id = ?";

	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Testreportテーブルから全データを取得.
	 * @return TestReportEntity
	 * @throws DataAccessException
	 */
	public TestReportEntity selectAll() throws DataAccessException {
		List<Map<String, Object>> resultList = jdbc.queryForList(SQL_SELECT_ALL);
		TestReportEntity testReportEntity = mappingSelectResult(resultList);
		return testReportEntity;
	}


	/**
	 * TestReportテーブルから取得したデータをTestReportEntity形式にマッピングする.
	 * @param resultList TestReportテーブルから取得したデータ
	 * @return TestReportEntity
	 */
	private TestReportEntity mappingSelectResult(List<Map<String, Object>> resultList) {
		TestReportEntity entity = new TestReportEntity();

		for (Map<String, Object> map : resultList) {
			TestReportData data = new TestReportData();
			data.setId((Integer) map.get("id"));
			data.setStatus((String) map.get("status"));
			data.setUser_name((String) map.get("user_name"));
			data.setUser_class((String) map.get("user_class"));
			data.setAttendance_number((Integer) map.get("attendance_number"));
			data.setDay((Date) map.get("day"));
			
			entity.getTestreportlist().add(data);
		}
		return entity;
	}

}