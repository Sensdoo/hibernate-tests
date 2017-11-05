package com.sens.examples.springdata.interfaces;

import com.sens.examples.models.springdata.ContactAudit;

import java.util.List;

/**
 * Created by Sensible on 04.11.2017.
 */

public interface ContactAuditService {
    List<ContactAudit> findAll();
    ContactAudit findById(Long id);
    ContactAudit save(ContactAudit contactAudit);
}
