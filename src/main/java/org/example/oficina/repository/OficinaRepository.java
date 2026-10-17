package org.example.oficina.repository;

import org.example.oficina.model.Oficina;
import org.example.oficina.model.SituacaoOficina;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OficinaRepository {

    private final List<Oficina> OFCINAS = new ArrayList<>();

    private Long contador = 1L;

    public void adicionar(Oficina oficina) {
        oficina.setId(contador++);
        OFCINAS.add(oficina);
    }

    public List<Oficina> getTodos() {
        return OFCINAS;
    }

    public Oficina buscarId(Long id){
        for (Oficina o : OFCINAS){
            if (o.getId().equals(id)){
                return o;
            }
        }
        return null;
    }

    public List<Oficina> listarPublicadas() {
        List<Oficina> buscarPublicadas = new ArrayList<>();
        for (Oficina o : OFCINAS){
            if(o.getSituacaoOficina() == SituacaoOficina.PUBLICADA){
                buscarPublicadas.add(o);
            }
        }
        return buscarPublicadas;
    }

    public int contarPublicadas() {
        int cont = 0;
        for (Oficina o : OFCINAS){
            if (o.getSituacaoOficina() == SituacaoOficina.PUBLICADA){
                cont ++;
            }
        }
        return cont;
    }



}
