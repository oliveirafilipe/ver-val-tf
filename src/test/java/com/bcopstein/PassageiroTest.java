package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.bcopstein.entidades.Passageiro;

public class PassageiroTest {
    private Passageiro passageiro;

    @BeforeEach
    public void setup(){
        passageiro = Passageiro.novoPassageiro("12346789000", "João das Couves");
    }

    @Test
    public void passangerShouldStartWithScore8() {
        assertEquals(8, passageiro.getPontuacaoAcumulada());
    }

    @Test
    public void infoPontuacaoShouldNotAllowNegativeOrZero() {
        Exception exceptionNegative = assertThrows(IllegalArgumentException.class, () ->  { passageiro.infoPontuacao(-1); });
        Exception exceptionZero = assertThrows(IllegalArgumentException.class, () ->  { passageiro.infoPontuacao(0); });
    
        String expectedMessage = "Pontucao invalida !";
        String actualMessageNegative = exceptionNegative.getMessage();
        String actualMessageZero = exceptionZero.getMessage();
    
        assertEquals(actualMessageNegative, expectedMessage);
        assertEquals(actualMessageZero, expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
        "40, 20, 2",
        "55, 2, 27",
        "75, 10, 7",
     })
    public void shouldReturnCorrectAverageScore(int pontuacaoAcumulada, int qtdadeAvaliacoes, int mediaEsperada) {
        passageiro = Passageiro.passageiroExistente(passageiro.getCPF(), passageiro.getNome(), pontuacaoAcumulada, qtdadeAvaliacoes);
        assertEquals(mediaEsperada, passageiro.getPontuacaoMedia());
    }
}
