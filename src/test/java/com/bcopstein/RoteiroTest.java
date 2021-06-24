package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RoteiroTest {
    private List<Bairro> bairros;

    @BeforeEach
    public void setup() {
        bairros = new ArrayList<>();
        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30, 40), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Moinhos de Vento", new Ponto(20, 30), 20, 10, 30.0));
        bairros.add(Bairro.novoBairroRetangular("Auxiliadora", new Ponto(40, 30), 20, 10, 20.0));
        bairros.add(Bairro.novoBairroRetangular("Boa Vista", new Ponto(40, 20), 20, 10, 20.0));
    }

    @ParameterizedTest
    @CsvSource({ 
        "1,4,40,35,50,15",
        "0,1,20,35,40,35",
        "3,4,50,25,50,15",
        "0,4,20,35,50,15",
    })
    public void testaRota(int indiceInicio, int indiceFim, int p1X, int p1Y, int p2X, int p2Y) {
        Roteiro roteiro = new Roteiro(bairros.get(indiceInicio), bairros.get(indiceFim), bairros);
        Reta rotaEsp = new Reta(new Ponto(p1X, p1Y), new Ponto(p2X, p2Y));
        assertEquals(rotaEsp, roteiro.getRota());
    }

    @Test
    public void testaBairrosPercorridos(){
        Roteiro roteiro = new Roteiro(bairros.get(1), bairros.get(4), bairros);
        Collection<Bairro> esperado = new ArrayList<>();
        esperado.add(bairros.get(1));
        esperado.add(bairros.get(2));
        esperado.add(bairros.get(3));
        esperado.add(bairros.get(4));
        Collection<Bairro> observado = roteiro.bairrosPercoridos();
        assertEquals(esperado, observado);
    }
}