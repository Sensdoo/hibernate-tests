package com.sens.examples.springdata.interfaces;

import com.sens.examples.models.springdata.ContactAudit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Sensible on 04.11.2017.
 */

public interface ContactAuditRepository extends CrudRepository<ContactAudit, Long> {
}
