package org.example;

public final class GastoSaude extends Gasto {
    public static final double MAX_GASTO_SAUDE = 1_500.0;
    private String registroConselho;

    public GastoSaude(long id, String descricao, String cnpj, double valor, String registroConselho) {
        super(id, descricao, cnpj, valor);
        this.registroConselho = registroConselho;
    }

    @Override
    public String toString() {
        return "Gasto com Educacao |  " + super.toString() + " | " + this.registroConselho;
    }
}
