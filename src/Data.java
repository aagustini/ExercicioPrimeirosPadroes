public class Data {
    private int dia;
    private int mes;
    private int ano;
    
    public Data(int umDia, int umMes, int umAno) {
        this.dia = umDia;
        this.mes = umMes;
        this.ano = umAno;
    }

    @Override
    public String toString(){
        return String.format("%d/%d/%d",dia,mes,ano);
    }
}
