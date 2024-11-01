package org.example;

import java.util.Objects;

public sealed abstract class Declaracao permits DeclaracaoCompleta, DeclaracaoSimplificada {
    private long id;
    private double valorPago;
    private double ganhoTributavel;

    public Declaracao(long id, double ganhoTributavel, double valorPago) {
        this.id = id;
        this.valorPago = valorPago;
        this.ganhoTributavel = ganhoTributavel;
    }

    // metodo template
    public final double getValorApagar(){
        return getValorImposto() - valorPago - getDespesaDedutivel();
    }

    public abstract double getValorImposto();

    public double getDespesaDedutivel() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Ganho Tributável = %.2f | Valor Pago: %.2f | Valor a Pagar: %.2f ",ganhoTributavel, valorPago,getValorApagar());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Declaracao that = (Declaracao) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public long getId() {
        return id;
    }
    public double getValorPago() {
        return valorPago;
    }
    public double getGanhoTributavel() {
        return ganhoTributavel;
    }


}
