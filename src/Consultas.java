import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Consultas {

    private RepositorioDAO repo;

    private Predicate<RegistroDoTempo> filtroCorrente;

    public Consultas(RepositorioDAO umRepo){
       this.repo = umRepo;
       this.filtroCorrente = (RegistroDoTempo reg) -> false;
    }

    public List<String> datasEmQueChouveuMaisDe(double milimetros){
        return repo.getRegistros()
            .stream()
            .filter(r->r.getPrecipitacao() > milimetros)
            .map(r->r.getDia()+"/"+r.getMes()+"/"+r.getAno())
            .collect(Collectors.toList());
            //.toList();
    }

    public String diaQueMaisChoveuNoAno(int ano){
        RegistroDoTempo registro = repo.getRegistros()
        .stream()
        .filter(reg->reg.getAno() == ano)
        .max(Comparator.comparing(RegistroDoTempo::getPrecipitacao))
        .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia()+"/"+registro.getMes()+"/"+registro.getAno()+", "+registro.getPrecipitacao();
        return resp;
    }

    void alteraConsultaPadrao(Predicate<RegistroDoTempo> consulta) {
        filtroCorrente = consulta;
    }

    List<Data> diasEmQue() {
       List<Data> result = repo.getRegistros()
                .stream()
                .filter(reg-> filtroCorrente.test(reg))
                .map(r->new Data(r.getDia(), r.getMes(), r.getAno()))
                .collect(Collectors.toList());
        return result;                
    }
}
