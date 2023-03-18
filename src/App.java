import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        Consultas consultas = new Consultas(new TempoRepositorio("poa_temps.txt"));
        //consultas.carregaDados();
        System.out.println("Dia em que mais choveu no ano de 1980: ");
        System.out.println(consultas.diaQueMaisChoveuNoAno(1980));
        System.out.println("Datas em que choveu mais de 90 milimetros");
        consultas.datasEmQueChouveuMaisDe(90)
            .forEach(System.out::println);

        
        System.out.println("\nDias em que... default");
        consultas.diasEmQue().forEach(System.out::println);
        System.out.println(consultas.diasEmQue().size());

        System.out.println("\nDias em que... tempMedia > 31");
        consultas.alteraConsultaPadrao((reg)->reg.getTemperaturaMedia()>31);
        consultas.diasEmQue().forEach(System.out::println);
        System.out.println(consultas.diasEmQue().size());

        System.out.println("\nDias em que... umidade do ar < 41");
        consultas.alteraConsultaPadrao((reg)->reg.getUmidadeRelativaDoAr() < 41 &&
                                        reg.getUmidadeRelativaDoAr() != -1);
        consultas.diasEmQue().forEach(System.out::println);
        System.out.println(consultas.diasEmQue().size());

        System.out.println("\nDias em que... temperatura > 41");
        consultas.alteraConsultaPadrao(new TempAcima41());
        consultas.diasEmQue().forEach(System.out::println);
        System.out.println(consultas.diasEmQue().size());        
        
        System.out.println("\nDias em que... umidade do ar < 40");
        consultas.alteraConsultaPadrao(new Predicate<RegistroDoTempo>() {
            public boolean test(RegistroDoTempo rt) {
                return rt.getUmidadeRelativaDoAr() < 40;
            }
        });
        consultas.diasEmQue().forEach(System.out::println);
        System.out.println(consultas.diasEmQue().size());


    }
}
