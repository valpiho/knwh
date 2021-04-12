package com.pibox.knwh.repository;

import com.pibox.knwh.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Warehouse findWarehouseById(Long id);

    Warehouse findWarehouseByTitle(String title);
}
