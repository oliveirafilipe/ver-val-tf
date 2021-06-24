package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bcopstein.entidades.Passageiro;
import com.bcopstein.entidades.Roteiro;
import com.bcopstein.entidades.Viagem;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.Bairro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViagemTest {
    LocalDateTime rightNow;
    Passageiro passageiro;
    Roteiro roteiro;

    @BeforeEach
    public void setup(){
        rightNow = LocalDateTime.now();
        passageiro = Passageiro.novoPassageiro("12346789000", "João das Couves");
        List<Bairro> bairros = new ArrayList<>();
        bairros.add(Bairro.novoBairroRetangular("Bom Fim", new Ponto(10, 40), 20, 10, 10.0));
        bairros.add(Bairro.novoBairroRetangular("Independencia", new Ponto(30, 40), 20, 10, 20.0));
        roteiro = new Roteiro(bairros.get(0), bairros.get(1), bairros);
    }

    @Test
    public void shouldInstanciateClassCorrectly(){
        Viagem viagem = new Viagem(1, rightNow, roteiro, passageiro, 20);

        assertEquals(viagem.getDataHora(), rightNow);
        assertEquals(viagem.getRoteiro(), roteiro);
        assertEquals(viagem.getPassageiro(), passageiro);
        assertEquals(viagem.getValorCobrado(), 20);
        assertEquals(viagem.getId(), 1);
    }
}
