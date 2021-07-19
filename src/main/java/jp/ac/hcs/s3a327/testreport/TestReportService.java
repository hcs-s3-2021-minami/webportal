package jp.ac.hcs.s3a327.testreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 受験報告情報を操作する。
 */
@Transactional
@Service
public class TestReportService {
	
	@Autowired
	TestReportRepository testReportRepository;
	
	/**
	 * 受験報告情報を全件取得する。
	 * @param reportId
	 * @return TestReportEntity
	 */
	public TestReportEntity selectAll() {

		TestReportEntity testReportEntity;
		try {
			testReportEntity = testReportRepository.selectAll();
			
		}catch(DataAccessException e){
			e.printStackTrace();
			testReportEntity = null;
		}
		
		return testReportEntity;
			
	}
	
}
