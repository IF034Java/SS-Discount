package com.springapp.mvc.repository.implementation;

import com.springapp.mvc.entity.Logging;
import com.springapp.mvc.repository.implementation.EntityRepository;
import com.springapp.mvc.repository.ILoggingRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LoggingRepository extends EntityRepository<Logging> implements ILoggingRepository {

    protected LoggingRepository() {
        super(Logging.class);
    }
}
