package com.jcolaiacovo.armored.cars.service;

import com.jcolaiacovo.armored.cars.domain.model.Aliquot;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Julian on 29/01/2017.
 */
@Transactional
@Service
public class AliquotService {

    public AliquotService() {

    }

    public List<BigDecimal> getAllAliquots() {
        return Arrays.stream(Aliquot.values()).map(Aliquot::getValue).collect(Collectors.toList());
    }

}
