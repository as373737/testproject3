package com.web.user.service;

import java.util.List;

import com.web.user.model.RecordBean;



public interface IRecordService {
	List<RecordBean> getAllRecords(String buyer);
	void deleteById(int record_id);
	RecordBean update(RecordBean recordBean );
}