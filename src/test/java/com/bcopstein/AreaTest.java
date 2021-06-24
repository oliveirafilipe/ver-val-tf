package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AreaTest {
    private Area area;

    @BeforeEach
    public void setup(){
        area = new Area(new Ponto(10,50),new Ponto(60,10));
    }

    @Test
    public void testaPontoCentral(){
        Ponto p = area.pontoCentral();
        assertEquals(35,p.getX());
        assertEquals(30,p.getY());
    }

    @Test
    public void foo(){
        Area a = new Area(new Ponto(20, 30), new Ponto(40, 20));    
        assertEquals(SituacaoReta.TODA_FORA, a.classifica(new Reta(new Ponto(40, 35), new Ponto(50, 15))));
    }

    @ParameterizedTest
    @CsvSource({
        "9,10,9,50,TODA_FORA",
        "61,10,61,50,TODA_FORA",
        "10,51,60,51,TODA_FORA",
        "10,9,60,9,TODA_FORA",
        //
        "10,50,60,50,TODA_DENTRO",
        "10,10,10,50,TODA_DENTRO",
        "60,10,60,50,TODA_DENTRO",
        "10,10,60,10,TODA_DENTRO",
        //
        "70,60,0,0,INTERSECTA",
        "70,60,35,30,INTERSECTA",
        // Caso de falha descoberto realizando teste de roteiro
        "60,60,65,5,TODA_FORA"
    })
    public void testaClassifica(int x1,int y1,int x2,int y2,String classificacao){
        Reta reta = new Reta(new Ponto(x1,y1), new Ponto(x2,y2));
        SituacaoReta sitEsp = switch(classificacao){
            case "TODA_DENTRO" -> SituacaoReta.TODA_DENTRO;
            case "TODA_FORA" -> SituacaoReta.TODA_FORA;
            case "INTERSECTA" -> SituacaoReta.INTERSECTA;
            default -> SituacaoReta.TODA_DENTRO;
        };
        SituacaoReta sitObs = area.classifica(reta);
        assertEquals(sitEsp, sitObs);
    }
}