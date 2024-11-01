package org.example;

public final class DeclaracaoSimplificada extends Declaracao {

    public DeclaracaoSimplificada(long id, double ganhoTributavel,double valorPago) {
        super(id, valorPago, ganhoTributavel);
    }

    @Override
    public double getValorImposto() {
        if(getGanhoTributavel() < 22_847.88) return 0;
        return (getGanhoTributavel() - 22_847.88) * 0.2;
    }

    @Override
    public String toString() {
        return "Declaração Simplificada \n= " + super.toString() + "\n";
    }
}
