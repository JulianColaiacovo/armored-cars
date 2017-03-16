package com.jcolaiacovo.armored.cars.service;

import com.google.common.collect.Lists;
import com.jcolaiacovo.armored.cars.domain.model.DocumentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@Service
public class DocumentTypeService {

    public List<DocumentType> getAllDocumentTypes() {
        return Lists.newArrayList(DocumentType.values());
    }

}
