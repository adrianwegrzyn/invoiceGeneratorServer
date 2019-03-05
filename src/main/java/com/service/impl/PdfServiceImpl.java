package com.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.model.Order;
import com.service.PdfService;

import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private MethodServiceImpl methodServiceImpl;


    @Override
    public void createPDF(Order order, String path) {
        Calendar calendar = Calendar.getInstance();


        try {
            //Tworzenie  pliku
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path, false));
            document.open();

            //Ustawianie czcionki

            BaseFont inna = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font bold = new Font(inna, 12, Font.BOLD);
            Font white = new Font(inna, 12, Font.BOLD, BaseColor.WHITE);
            Font polishMark = new Font(inna, 12, Font.NORMAL);

            //tutuł
            Paragraph title = new Paragraph("Faktura nr " + calendar.get(Calendar.DAY_OF_MONTH) + " / " + calendar.get(Calendar.MONTH + 1) + " / " + calendar.get(Calendar.YEAR), bold);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            //Tworzenie Tabeli Sprzedawaca i Nabywca
            PdfPTable table = new PdfPTable(3);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);


            PdfPCell[] cell = new PdfPCell[15];
            //1 wiersz
            cell[0] = new PdfPCell(new Phrase("Sprzedawca:", bold));
            cell[1] = new PdfPCell(new Phrase(""));
            cell[2] = new PdfPCell(new Phrase("Nabywaca:", bold));
            //2 wiersz
            cell[3] = new PdfPCell(new Phrase(order.getSeller().getSellerCompany(), polishMark));
            cell[4] = new PdfPCell(new Phrase(""));
            cell[5] = new PdfPCell(new Phrase(""));
            //3 wiersz
            cell[6] = new PdfPCell(new Phrase(order.getSeller().getSellerName(), polishMark));
            cell[7] = new PdfPCell(new Phrase(""));
            cell[8] = new PdfPCell(new Phrase(order.getBuyer().getBuyerName(), polishMark));
            //4 wiersz
            cell[9] = new PdfPCell(new Phrase(order.getSeller().getSellerAdress(), polishMark));
            cell[10] = new PdfPCell(new Phrase(""));
            cell[11] = new PdfPCell(new Phrase(order.getBuyer().getBuyerAdress(), polishMark));
            //5 wiersz
            cell[12] = new PdfPCell(new Phrase(order.getSeller().getSellerPostcode() + " " + order.getSeller().getSellerCity(), polishMark));
            cell[13] = new PdfPCell(new Phrase(""));
            cell[14] = new PdfPCell(new Phrase(order.getBuyer().getBuyerPostcode() + " " + order.getBuyer().getBuyerCity(), polishMark));

            for (int i = 0; i < 15; i++) {
                cell[i].setBorder(Rectangle.NO_BORDER);
                cell[i].setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell[i]);
            }
            document.add(table);
            document.add(Chunk.NEWLINE);
            //Druga Tabelka opis,liczba,cena jedn.,koszt

            PdfPTable table2 = new PdfPTable(4);
            table2.setWidthPercentage(100);
            table2.setWidths(new int[]{95, 35, 35, 35});
            PdfPCell[] cell2Tabela = new PdfPCell[20];

            //wiersz1
            cell2Tabela[0] = new PdfPCell(new Phrase("Opis", white));
            cell2Tabela[1] = new PdfPCell(new Phrase("Liczba", white));
            cell2Tabela[2] = new PdfPCell(new Phrase("Cena jedn.", white));
            cell2Tabela[3] = new PdfPCell(new Phrase("Koszt", white));
            //wiersz2
            cell2Tabela[4] = new PdfPCell(new Phrase(order.getDescription(), polishMark));
            cell2Tabela[5] = new PdfPCell(new Phrase(order.getQuantity()+""));
            cell2Tabela[6] = new PdfPCell(new Phrase(order.getNetPrice() + " zł", polishMark));

                double priceNetto =methodServiceImpl.setQuantity(order.getNetPrice(), order.getQuantity());
                       priceNetto = (double) Math.round(priceNetto * 100.0) / 100.0;

            cell2Tabela[7] = new PdfPCell(new Phrase( priceNetto+" zł", polishMark));

            //wiersz3
            cell2Tabela[8] = new PdfPCell(new Phrase(""));
            cell2Tabela[9] = new PdfPCell(new Phrase(""));
            cell2Tabela[10] = new PdfPCell(new Phrase("Netto"));
            cell2Tabela[11] = new PdfPCell(new Phrase(priceNetto+" zł", polishMark));
            //wiersz4
            cell2Tabela[12] = new PdfPCell(new Phrase(""));
            cell2Tabela[13] = new PdfPCell(new Phrase("Podatek"));
            cell2Tabela[14] = new PdfPCell(new Phrase(order.getTaxRate() + "%"));

                double priceVAT =methodServiceImpl.setVat(methodServiceImpl.setQuantity(order.getNetPrice(), order.getQuantity()), order.getTaxRate());
                       priceVAT = (double) Math.round(priceVAT * 100.0) / 100.0;

            cell2Tabela[15] = new PdfPCell(new Phrase(priceVAT+" zł", polishMark));

            //wiersz5
            cell2Tabela[16] = new PdfPCell(new Phrase(""));
            cell2Tabela[17] = new PdfPCell(new Phrase(""));
            cell2Tabela[18] = new PdfPCell(new Phrase("Łacznie", polishMark));
            //cena calosciowa
                double priceAll = methodServiceImpl.setCalkowitaCena(priceNetto,priceVAT);
                       priceAll = (double) Math.round(priceAll * 100.0) / 100.0;

            cell2Tabela[19] = new PdfPCell(new Phrase(priceAll + " zł", polishMark));


            for (int i = 0; i < 20; i++) {
                cell2Tabela[i].setUseVariableBorders(true);
                if (i < 4) {
                    cell2Tabela[i].setFixedHeight(32);
                    cell2Tabela[i].setBackgroundColor(BaseColor.GRAY);
                    cell2Tabela[i].setBorderColor(BaseColor.LIGHT_GRAY);
                } else if (i >= 5 && i < 8 || i >= 14 && i < 16 || i == 19 || i == 11) {
                    cell2Tabela[i].setHorizontalAlignment(Element.ALIGN_RIGHT);
                }


                if (i >= 4 && i < 20) {
                    cell2Tabela[i].setBorderColor(BaseColor.WHITE);
                    cell2Tabela[i].setBorderColorRight(BaseColor.LIGHT_GRAY);
                    cell2Tabela[i].setBorderColorBottom(BaseColor.BLACK);

                    if (i == 7 || i == 11 || i == 15 || i == 19) {
                        cell2Tabela[i].setBorderColorRight(BaseColor.WHITE);
                    }
                    if (i >= 8 && i < 12) {
                        cell2Tabela[i].setBorderColorBottom(BaseColor.LIGHT_GRAY);
                    }
                }

                table2.addCell(cell2Tabela[i]);
            }
            document.add(table2);
            document.add(Chunk.NEWLINE);

            //Wypisywanie slowne
            String cenaString = String.valueOf(priceAll);
            String tab[] = cenaString.split("\\.");

            int zlotych = Integer.parseInt(tab[0]);
            int grosze = Integer.parseInt(tab[1]);

            Paragraph doZaplaty = new Paragraph();

            Chunk chunk1 = new Chunk("Do zaplaty: ", bold);
            Chunk chunk2 = null;

            doZaplaty.add(chunk1);
            if(grosze != 0){
                chunk2 = new Chunk(priceAll + " złote", polishMark);
                doZaplaty.add(chunk2);

            }else {
                chunk2 = new Chunk(zlotych + " złote", polishMark);
                doZaplaty.add(chunk2);
            }

            document.add(doZaplaty);
            document.add(Chunk.NEWLINE);

            Paragraph slownie = new Paragraph();
            Chunk chunk3 = new Chunk("Słownie: ", bold);
            Chunk chunk4 = new Chunk(methodServiceImpl.translacja(zlotych)+"złote ",polishMark);

            slownie.add(chunk3);
            slownie.add(chunk4);

            if(grosze != 0){
                Chunk chunk5 = new Chunk("i "+methodServiceImpl.translacja(grosze)+"groszy",polishMark);
                slownie.add(chunk5);
            }

            document.add(slownie);
            document.close();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }


    }
}


