package com.pibox.knwh.service;

import com.pibox.knwh.entity.Warehouse;
import javassist.NotFoundException;

import java.util.List;

public interface WarehouseService {

    Warehouse createOrUpdateCompany(Warehouse warehouse);

    Warehouse findWarehouseById(Long id) throws NotFoundException;

    List<Warehouse> findAllWarehouses();

    void deleteWarehouseById(Long id) throws NotFoundException;
}
