package com.service;

public interface MethodService  {

    double setQuantity(double cenaJednostki, double ilosc);

    double setVat(double cenaCalosci, double procentVat);

    double setCalkowitaCena(double cenaCalosci, double procentVat);

    String translacja(long liczba);

}
