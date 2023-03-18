import java.util.function.Predicate;


// classe exemplo para uso do strategy
// não tem utilidade pois podem ser 
// utilizadas expressões lambda 
public class TempAcima41 implements Predicate<RegistroDoTempo> {
    public boolean test(RegistroDoTempo rt) {
        return rt.getTemperaturaMaxima()>41;
    
    }    
}
