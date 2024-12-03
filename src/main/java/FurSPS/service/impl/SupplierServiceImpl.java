package FurSPS.service.impl;

import FurSPS.dao.ISupplierDAO;
import FurSPS.dao.impl.SupplierDAOImpl;
import FurSPS.models.SupplierModel;
import FurSPS.service.ISupplierService;

public class SupplierServiceImpl implements ISupplierService{
	ISupplierDAO supplierDAO = new SupplierDAOImpl();

	@Override
	public SupplierModel findOne(int id) {
		return supplierDAO.findOne(id);
	}

}