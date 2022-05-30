package com.osemdeveloper.BmtJdbc.customRepository;

import java.util.List;

import com.osemdeveloper.BmtJdbc.domain.Admin;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;

public interface AdminSqlRepository {

	public int save(Admin a);

	public int update(Admin a);

	public int saveBus(BusDetails b);

	public int updateBus(BusDetails b);

	public int deleteBus(Integer busNumber);

	List<BusDetails> listBusesOn();
	


	public Admin findByAdminEmail(String adminEmail);

	public BusDetails findById(Integer busNumber);
	
	public Admin findByAdminId(Integer adminId);



}
