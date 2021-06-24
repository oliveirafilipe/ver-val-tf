package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bcopstein.entidades.Bairro;
import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;
import com.bcopstein.entidades.geometria.Reta;
import com.bcopstein.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BairroTest {
    private Area a1,a2;
    private Reta reta, reta1;

    @BeforeEach
    public void setup(){
        reta = new Reta(new Ponto(10,10),new Ponto(20,10));
        reta1 = new Reta(new Ponto(10,10),new Ponto(20,20));
        a1 = mock(Area.class);
        when(a1.classifica(reta)).thenReturn(SituacaoReta.TODA_DENTRO);

        a2 = mock(Area.class);
        when(a2.classifica(reta)).thenReturn(SituacaoReta.TODA_FORA);
        when(a2.classifica(reta1)).thenReturn(SituacaoReta.INTERSECTA);
        when(a2.pontoCentral()).thenReturn(new Ponto(20,20));
    }

    @Test
    // Teste unitário: testa exclusivamente o funcionamento de bairro
    // Usa um duble de "area"
    // Verifica se "Bairro" chama adequadamente os métodos de "Area"
    public void testaPontoCentral(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        Ponto ptCentralEsperado = new Ponto(20,20);
        Ponto ptCentralObservado = bairro.getPontoCentral();
        assertEquals(ptCentralEsperado, ptCentralObservado);
    }

    @Test
    // Teste unitário: testa exclusivamente o funcionamento de bairro
    // Usa um duble de "area"
    // Verifica se "Bairro" chama adequadamente os métodos de "Area"
    public void testaClassificaReta(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        SituacaoReta sitEsp = SituacaoReta.INTERSECTA;
        SituacaoReta sitObs = bairro.getClassificacao(reta1);
        assertEquals(sitEsp, sitObs);
    }

    @Test
    public void shouldChangeCost(){
        Bairro bairro = new Bairro("Auxiliadora",a2,10);
        bairro.alteraCustoTransporte(21.5);
        assertEquals(21.5, bairro.getCustoTransporte());
    }

}
