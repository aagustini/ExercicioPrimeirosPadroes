
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Repositorio implements RepositorioDAO {
    private List<RegistroDoTempo> registros;
    private String nArq;

    public Repositorio(String fileName){
        registros = new LinkedList<>();
        this.nArq = fileName;
        this.carregaDados();
    }

    public void carregaDados(){
        String currDir = Paths.get("").toAbsolutePath().toString();
        // Monta o nome do arquivo
        String nomeCompleto = currDir+"/"+nArq;
        System.out.println(nomeCompleto);
        // Cria acesso ao "diretorio" da mídia (disco)
        Path path = Paths.get(nomeCompleto);

        String linha = "";
         // Usa a classe scanner para fazer a leitura do arquivo
         try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
            // Pula o cabecalho
            sc.nextLine();
            // Le os dados
            while(sc.hasNext()){
                linha = sc.nextLine();
                String dados[] = linha.split(" ");
                // Trata a data
                String data[] = dados[0].split("/");
                int dia = Integer.parseInt(data[0]);
                int mes = Integer.parseInt(data[1]);
                int ano = Integer.parseInt(data[2]);
                // Trata demais dados

                //data precip maxima minima horas_insol temp_media um_relativa vel_vento
                double precipitacao = Double.parseDouble(dados[1]);
                double temperaturaMaxima = Double.parseDouble(dados[2]);
                double temperaturaMinima = Double.parseDouble(dados[3]);
                double horasInsolacao = Double.parseDouble(dados[4]);
                double temperaturaMedia = Double.parseDouble(dados[5]);
                double umidadeRelativaDoAr = Double.parseDouble(dados[6]);
                double velocidadeDoVento = Double.parseDouble(dados[7]);
                // Cria um registro e insere na lista
                RegistroDoTempo reg = new RegistroDoTempo(dia, mes, ano, precipitacao, temperaturaMaxima, temperaturaMinima, horasInsolacao, temperaturaMedia, umidadeRelativaDoAr, velocidadeDoVento);
                registros.add(reg);
            }
         }catch (IOException x){
             System.err.format("Erro de E/S: %s%n", x);
         }
    }    

    
    public List<RegistroDoTempo> getRegistros() {
        return registros;
    }
}

