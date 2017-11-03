package com.sens.examples.jpa;

import com.sens.examples.jpa.interfaces.ContactSummaryService;
import com.sens.examples.models.jpa.ContactSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Sensible on 03.11.2017.
 */

@Service("contactSummaryService")
@Repository
@Transactional
public class ContactSummaryServiceImpl implements ContactSummaryService {

    @PersistenceContext(unitName = "emf")
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<ContactSummary> findAll() {
        List<ContactSummary> result = em.createQuery(
                "select new com.sens.examples.models.jpa.ContactSummary(" +
                        "c.firstName, c.lastName, t.telNumber) " +
                        "from Contact c left join c.contactTelDetails t " +
                        "where t.telType='Home'", ContactSummary.class).getResultList();
        return result;
    }
}
