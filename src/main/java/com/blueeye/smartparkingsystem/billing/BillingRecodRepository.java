package com.blueeye.smartparkingsystem.billing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRecodRepository extends JpaRepository<BillingRecord,Long> {
}
