package com.diploma.demo.core.installedequipment.repository;

import com.diploma.demo.core.act.Act;
import com.diploma.demo.core.equipment.Equipment;
import com.diploma.demo.core.installedequipment.InstalledEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalledEquipmentRepository extends JpaRepository<InstalledEquipment, Long> {

}
