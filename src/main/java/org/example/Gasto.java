package org.example;

import java.util.Objects;

public sealed class Gasto permits GastoEducacao, GastoSaude{
    private static long idCount;
    private long id;
    private String descricao;
    private String cnpj;
    private double valor;

    public Gasto(long id, String descricao, String cnpj, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.valor = valor;
    }

    public static long novoId(){
        return idCount++;
    }

    @Override
    public String toString() {
        return String.format("ID = %d | Descrição = %s | CNPJ = %s | Valor = %.2f",id,descricao,cnpj,valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gasto gasto = (Gasto) o;
        return id == gasto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public long getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getCnpj() {
        return cnpj;
    }
    public double getValor() {
        return valor;
    }


}
