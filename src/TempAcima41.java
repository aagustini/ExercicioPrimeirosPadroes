import java.util.function.Predicate;

public class TempAcima41 implements Predicate<RegistroDoTempo> {
    public boolean test(RegistroDoTempo rt) {
        return rt.getTemperaturaMaxima()>41;
    
    }    
}
