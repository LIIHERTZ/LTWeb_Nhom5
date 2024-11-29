package FurSPS.dao;

import java.util.List;

import FurSPS.models.SupplierModel;

public interface ISupplierDAO {
	SupplierModel findOne(int id);
    List<SupplierModel> findAll();
}
