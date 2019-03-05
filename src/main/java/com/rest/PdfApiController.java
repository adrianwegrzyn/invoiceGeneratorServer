package com.rest;


import com.listPDF.PDF;
import com.model.Order;
import com.service.PdfListService;
import com.service.impl.PdfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.time.ZonedDateTime;
@Controller
@RequestMapping(value = "/api")

public class PdfApiController {

    @Autowired
    private PdfServiceImpl pdfServiceImpl;

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public ResponseEntity<Void> createFile(@RequestBody Order userDataDto) {

        String path = "D:\\PDF";
        File file = new File(path);

        long time = ZonedDateTime.now().toEpochSecond();
        pdfServiceImpl.createPDF(userDataDto,"D:\\PDF\\invoice_"+time+".pdf");

        return file.exists()? new ResponseEntity<>(HttpStatus.CREATED):
                              new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Autowired
    private PdfListService pdfList;

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    public ResponseEntity<PDF> getText() {


        return !pdfList.getListPDF().getList().isEmpty() ?
                new ResponseEntity<>(pdfList.getListPDF(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
