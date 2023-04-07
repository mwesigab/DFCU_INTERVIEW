package com.ben.interview.repositories;

import com.ben.interview.models.RequestLog;
import org.springframework.data.repository.CrudRepository;

public interface RequestLoggerRepository extends CrudRepository<RequestLog, Integer> {
}
