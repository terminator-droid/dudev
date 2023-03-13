package com.dudev.listener;

import com.dudev.entity.Audit;
import org.hibernate.event.spi.AbstractPreDatabaseOperationEvent;
import org.hibernate.event.spi.PreDeleteEvent;
import org.hibernate.event.spi.PreDeleteEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

public class AuditTableListener implements PreDeleteEventListener, PreInsertEventListener {


    @Override
    public boolean onPreDelete(PreDeleteEvent preDeleteEvent) {
        getAudit(preDeleteEvent, Audit.Operation.DELETE);
        return false;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        getAudit(preInsertEvent, Audit.Operation.INSERT);
        return false;
    }

    private static void getAudit(AbstractPreDatabaseOperationEvent preDeleteEvent, Audit.Operation operation) {
        if (preDeleteEvent.getEntity().getClass() != Audit.class) {
            Audit audit = Audit.builder()
                    .entityId(preDeleteEvent.getId())
                    .entityName(preDeleteEvent.getEntityName())
                    .entityContent(preDeleteEvent.toString())
                    .operation(operation)
                    .build();
            preDeleteEvent.getSession().save(audit);
        }
    }
}
