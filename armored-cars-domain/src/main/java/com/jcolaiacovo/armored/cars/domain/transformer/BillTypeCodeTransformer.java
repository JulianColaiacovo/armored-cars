package com.jcolaiacovo.armored.cars.domain.transformer;

import com.jcolaiacovo.armored.cars.api.model.BillTypeCodeDTO;
import com.jcolaiacovo.armored.cars.domain.model.BillTypeCode;
import org.springframework.stereotype.Component;

/**
 * Created by Julian on 09/04/2017.
 */
@Component
public class BillTypeCodeTransformer extends AbstractApiTransformer<BillTypeCode, BillTypeCodeDTO> {

    @Override
    public BillTypeCode transform(BillTypeCodeDTO value) {
        return BillTypeCode.valueOf(value.getId());
    }

    @Override
    public BillTypeCodeDTO transformToDTO(BillTypeCode billTypeCode) {
        BillTypeCodeDTO billTypeCodeDTO = new BillTypeCodeDTO();
        billTypeCodeDTO.setId(billTypeCode.name());
        billTypeCodeDTO.setBillType(billTypeCode.getBillType().name());
        billTypeCodeDTO.setBillCode(billTypeCode.getBillCode().name());
        billTypeCodeDTO.setActive(billTypeCode.isActive());
        return billTypeCodeDTO;
    }

}
