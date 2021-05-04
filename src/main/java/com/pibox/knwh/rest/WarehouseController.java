package com.pibox.knwh.rest;

import com.pibox.knwh.entity.Warehouse;
import com.pibox.knwh.service.WarehouseService;
import com.pibox.knwh.utils.MapValidationErrorService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
@CrossOrigin
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final MapValidationErrorService mapValidationErrorService;

    public WarehouseController(WarehouseService warehouseService,
                               MapValidationErrorService mapValidationErrorService) {
        this.warehouseService = warehouseService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrUpdateWarehouse(@Valid @RequestBody Warehouse warehouse, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        Warehouse newWarehouse = warehouseService.createOrUpdateCompany(warehouse);
        return new ResponseEntity<>(newWarehouse, HttpStatus.CREATED);
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<?> getWarehouseById(@PathVariable Long warehouseId) throws NotFoundException {
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Warehouse> getAllCompanies() {
        return warehouseService.findAllWarehouses();
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable Long warehouseId) throws NotFoundException {
        warehouseService.deleteWarehouseById(warehouseId);
        return new ResponseEntity<>("Company with ID '" + warehouseId + "' was deleted successfully.", HttpStatus.OK);
    }
}
