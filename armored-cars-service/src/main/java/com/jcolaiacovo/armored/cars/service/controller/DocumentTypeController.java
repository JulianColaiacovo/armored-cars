package com.jcolaiacovo.armored.cars.service.controller;

import com.jcolaiacovo.armored.cars.domain.model.DocumentType;
import com.jcolaiacovo.armored.cars.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Julian on 15/01/2017.
 */
@RestController
@RequestMapping("/document-type")
public class DocumentTypeController {

    private DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping
    public List<DocumentType> getAllDocumentTypes() {
        return this.documentTypeService.getAllDocumentTypes();
    }

}
