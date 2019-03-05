package com.service.impl;

import com.listPDF.PDF;
import com.service.PdfListService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

@Service
public class PdfListServiceImpl implements PdfListService {
    @Override
    public PDF getListPDF() {
        File file = null;
        File[] paths;
        ArrayList<String> listaPdf = new ArrayList<>();

        try {

            // tworzenie pliku jak go nie ma
            file = new File("D:/PDF");

            // zwraca liste wszystkich plików w folderze PDF
            paths = file.listFiles();

            // dodanie nazw do ArrayListy
            for (File path : paths) {

                listaPdf.add(path.toString());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return new PDF(listaPdf);
    }
}

