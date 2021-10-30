package com.web.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.user.dao.IRecordDao;
import com.web.user.model.RecordBean;
import com.web.user.service.IRecordService;


@Service
public class RecordService implements IRecordService {

	IRecordDao recordDao;
	
	@Autowired
	public RecordService(IRecordDao recordDao) {
	
		this.recordDao = recordDao;
	}

	@Transactional
	@Override
	public List<RecordBean> getAllRecords(String buyer) {
		// TODO Auto-generated method stub
		return recordDao.getAllRecords(buyer);
	}
	
	@Transactional
	@Override
	public void deleteById(int record_id) {
		recordDao.deleteById(record_id);
		
	}
	@Transactional
	@Override
	public RecordBean update(RecordBean recordBean) {
	
		return recordDao.update(recordBean);
	}

}
