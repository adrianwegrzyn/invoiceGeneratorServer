package com.service.impl;

import com.service.MethodService;
import org.springframework.stereotype.Service;

@Service
public class MethodServiceImpl implements MethodService {
    @Override
    public double setQuantity(double cenaJednostki, double ilosc) {
        return cenaJednostki*ilosc;
    }

    @Override
    public double setVat(double cenaCalosci, double procentVat) {
        return procentVat/(double)100*cenaCalosci;
    }

    @Override
    public double setCalkowitaCena(double cenaCalosci, double procentVat) {
        return cenaCalosci+procentVat;
    }

    @Override
    public  String translacja(long liczba) {
        String[] jedności = { "", "jeden ", "dwa ", "trzy ", "cztery ",
                "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć ", };

        String[] nastki = { "", "jedenaście ", "dwanaście ", "trzynaście ",
                "czternaście ", "piętnaście ", "szesnaście ", "siedemnaście ",
                "osiemnaście ", "dziewiętnaście ", };

        String[] dziesiątki = { "", "dziesięć ", "dwadzieścia ",
                "trzydzieści ", "czterdzieści ", "pięćdziesiąt ",
                "sześćdziesiąt ", "siedemdziesiąt ", "osiemdziesiąt ",
                "dziewięćdziesiąt ", };

        String[] setki = { "", "sto ", "dwieście ", "trzysta ", "czterysta ",
                "pięćset ", "sześćset ", "siedemset ", "osiemset ",
                "dziewięćset ", };

        String[][] grupy = { { "", "", "" },
                { "tysiąc ", "tysiące ", "tysięcy " },
                { "milion ", "miliony ", "milionów " },
                { "miliard ", "miliardy ", "miliardów " },
                { "bilion ", "biliony ", "bilionów " },
                { "biliard ", "biliardy ", "biliardów " },
                { "trylion ", "tryliony ", "trylionów " }, };


        long j = 0 , n = 0, d = 0, s = 0, g = 0, k = 0;
        String słownie = "";
        String znak = "";



        if (liczba < 0) {
            znak = "minus ";
            liczba = -liczba;
        }
        if (liczba == 0) {
            znak = "zero";
        }

        while (liczba != 0) {
            s = liczba % 1000 / 100;
            d = liczba % 100 / 10;
            j = liczba % 10;

            if (d == 1 & j > 0)
            {
                n = j;
                d = 0;
                j = 0;
            } else {
                n = 0;
            }


            if (j == 1 & s + d + n == 0) {
                k = 0;

                if (s + d == 0 && g > 0)
                {

                    j = 0;
                    słownie = grupy[(int) g][(int) k] + słownie;
                }
            } else if (j == 2) {
                k = 1;
            } else if (j == 3) {
                k = 1;
            } else if (j == 4) {
                k = 1;
            } else {
                k = 2;
            }


            if (s+d+n+j > 0) {
                słownie = setki[(int) s] + dziesiątki[(int) d] + nastki[(int) n]
                        + jedności[(int) j] + grupy[(int) g][(int) k] + słownie;
            }


            liczba = liczba / 1000;

            g = g + 1;
        }

        słownie = znak + słownie;
        return słownie;

    }
}
