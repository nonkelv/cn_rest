package org.exercise3;

import org.exercise3.model.CarRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRegistrationRepository extends JpaRepository<CarRegistration,Long> {
}
