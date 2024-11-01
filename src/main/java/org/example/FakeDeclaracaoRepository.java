package org.example;

import java.util.*;

public class FakeDeclaracaoRepository implements DeclaracaoRepository {
    private final static Map<Long, Declaracao> db =  new LinkedHashMap<>();

    @Override
    public void save(Declaracao declaracao){
        if(db.containsKey(declaracao.getId()))
            throw new IllegalStateException("Entity already exists.");
        db.put(declaracao.getId(), declaracao);
    }

    @Override
    public void update(Declaracao declaracao){
        var replaced = db.replace(declaracao.getId(), declaracao);
        if (replaced == null)
            throw new NoSuchElementException("Entity not found.");
    }

    @Override
    public void delete(Long id){
        var deleted = db.remove(id);
        if (deleted == null)
            throw new NoSuchElementException("Entity not found.");
    }

    @Override
    public Optional<Declaracao> findById(Long id){
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<Declaracao> findAll(){
        return new ArrayList<>(db.values());
    }
}
