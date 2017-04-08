package com.jcolaiacovo.armored.cars.domain.service;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.model.StockStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@Service
public class StockStatusService {

    public List<StockStatus> getAll() {
        return Lists.newArrayList(StockStatus.values());
    }

}
