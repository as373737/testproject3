package com.web.user.dao;

import java.util.List;







public interface IRecordDao {

	List<RecordBean> getAllRecords(String buyer);
	void deleteById(int record_id);
	RecordBean update(RecordBean recordBean );

}