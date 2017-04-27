package com.jcolaiacovo.armored.cars.domain.service;

import com.jcolaiacovo.armored.cars.domain.dao.AbstractDao;
import com.jcolaiacovo.armored.cars.domain.dao.ArmoredDao;
import com.jcolaiacovo.armored.cars.domain.model.Armored;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Julian on 21/02/2017.
 */
@Transactional
@Service
public class ArmoredService extends AbstractDaoService<Armored> {

    private ArmoredDao armoredDao;
    private ArmoredExcelHelper armoredExcelHelper;

    @Autowired
    public ArmoredService(ArmoredDao armoredDao, ArmoredExcelHelper armoredExcelHelper) {
        this.armoredDao = armoredDao;
        this.armoredExcelHelper = armoredExcelHelper;
    }

    @Override
    protected AbstractDao<Armored> getDao() {
        return this.armoredDao;
    }

    public List<Armored> getAll() {
        return this.armoredDao.getAll();
    }

    public Optional<Armored> findByCode(int code) {
        return this.armoredDao.findByCode(code);
    }

    public void loadExcel(MultipartFile excel) {
        try {
            Workbook workbook = new HSSFWorkbook(excel.getInputStream());
            List<Armored> armoreds = this.armoredExcelHelper.parse(workbook);
            List<Armored> bindArmoreds = this.bind(armoreds);
            this.armoredDao.saveAll(bindArmoreds);
        } catch (IOException e) {
            throw new RuntimeException("Invalid excel", e);
        }
    }

    public List<Armored> bind(List<Armored> armoreds) {
        return armoreds.parallelStream().map(this::bind).collect(Collectors.toList());
    }

    private Armored bind(Armored armored) {
        Optional<Armored> armoredOptional = this.findByCode(armored.getCode());
        if (armoredOptional.isPresent()) {
            Armored oldArmored = armoredOptional.get();
            armored.setId(oldArmored.getId());
            armored.getCar().setId(oldArmored.getCar().getId());
            armored.getBillingAndReference().setId(oldArmored.getBillingAndReference().getId());
        }
        return this.armoredDao.merge(armored);
    }

}
