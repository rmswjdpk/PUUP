package com.hanb.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanb.dao.QandADao;

@Service
public class QandAService {
	
	@Autowired
	private QandADao dao;
	
	public boolean insertQandA(int p_no, String p_ref){
		if(p_ref == null)
			return dao.insertQandA(p_no, p_no, 0, 0) == 1 ? true : false;
		else
			return insertQandA(p_no, Integer.parseInt(p_ref));
	}
	
	private boolean insertQandA(int p_no, int p_ref){
		Map<String, Integer> QA = dao.getDetailQandA(p_ref);
		int ref = Integer.parseInt(String.valueOf(QA.get("P_REF")));
		int step = Integer.parseInt(String.valueOf(QA.get("P_STEP")));
		int deep = Integer.parseInt(String.valueOf(QA.get("P_DEEP")));
		dao.updateStep(ref, step);
		step++; deep++;
		return dao.insertQandA(p_no, ref, deep, step) == 1 ? true : false;
	}
}
