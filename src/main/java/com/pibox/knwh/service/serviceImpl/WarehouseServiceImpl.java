package com.pibox.knwh.service.serviceImpl;

import com.pibox.knwh.entity.Warehouse;
import com.pibox.knwh.exception.domain.UniqueFieldExistException;
import com.pibox.knwh.repository.WarehouseRepository;
import com.pibox.knwh.service.WarehouseService;
import javassist.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    public Warehouse createOrUpdateCompany(Warehouse warehouse) {
        try {
            return warehouseRepository.save(warehouse);
        } catch (DataIntegrityViolationException exception) {
            if (warehouseRepository.findWarehouseByTitle(warehouse.getTitle()) != null) {
                throw new UniqueFieldExistException("Warehouse title '" + warehouse.getTitle() + "' already exists");
            }
            throw exception;
        }
    }

    @Override
    public Warehouse findWarehouseById(Long id) throws NotFoundException {
        Warehouse warehouse = warehouseRepository.findWarehouseById(id);
        if (warehouse == null) {
            throw new NotFoundException("Company with ID '" + id + "' is not found");
        }
        return warehouse;
    }

    @Override
    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public void deleteWarehouseById(Long id) throws NotFoundException {
        Warehouse warehouse = warehouseRepository.findWarehouseById(id);
        if (warehouse == null) {
            throw new NotFoundException("Company with ID '" + id + "' is not found");
        }
        warehouseRepository.delete(warehouse);
    }
}
