package com.diploma.demo.core.equipment.repository;

import com.diploma.demo.core.act.Act;
import com.diploma.demo.core.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
