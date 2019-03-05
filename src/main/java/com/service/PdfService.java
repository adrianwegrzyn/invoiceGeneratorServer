package com.service;

import com.model.Order;

public interface PdfService {
    void createPDF(Order order, String path);
}
