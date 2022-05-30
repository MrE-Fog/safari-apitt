package com.osemdeveloper.BmtJdbc.service;

import java.util.List;

import com.osemdeveloper.BmtJdbc.domain.Admin;
import com.osemdeveloper.BmtJdbc.domain.BusDetails;
import com.osemdeveloper.BmtJdbc.util.AdminAuth;

public interface AdminService {
	public Admin addAdmin(Admin admin);
    ///////
	public Admin getAdmin(Integer adminId);

	public BusDetails addBusDetails(BusDetails details);
	//////

	public void deleteBusDetails(BusDetails busDtls);

	public List<BusDetails> getAllBusDetails();

	public Admin adminLogin(AdminAuth auth);
	


}
