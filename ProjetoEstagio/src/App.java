import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
        double[] valores = {67836.43, 36678.66, 29229.88, 27165.48, 19849.53};
        Scanner ler = new Scanner(System.in);
        int n;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Soma Sequencial");
            System.out.println("2. Sequencial Fibonacci");
            System.out.println("3. Vetor Faturamento");
            System.out.println("4. Percentual Representativo");
            System.out.println("5. Inverter String");
            System.out.println("6. Sair");
            System.out.print("Digite sua escolha: ");
            n = ler.nextInt();

            switch (n) {
                case 1:
                    somaSequencial();
                    break;
                case 2:
                    int fi;
                    System.out.println("Informe um número: ");
                    fi = ler.nextInt();
                    sequencialFibonacci(fi);
                    break;
                case 3:
                    vetorFaturamento();
                    break;
                case 4:
                    percentualRepresentativo(valores);
                    break;
                case 5:
                    String palavra;
                    System.out.println("Informe uma palavra: ");
                    palavra = ler.next();
                    inverterString(palavra);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (n != 6);

        ler.close();
    }

    public static void somaSequencial(){
        int indice = 13;
        int soma = 0;
        int k = 0;

        while(k < indice){
            k++;
            soma = soma + k;
        }
        System.out.println("O valor da soma é " + soma);
    }
    public static void sequencialFibonacci(int num) {
        if (num < 0) {
            System.out.println("Número deve ser maior que 0.");
            return;
        }

        int ant = 0;
        int f = 1;

        if (num == 0) {
            System.out.println(ant + " ");
            return;
        }
        System.out.print(ant + " ");
        System.out.print(f + " ");

        for (int i = 2; i <= num; i++) {
            int temp = f;
            f += ant;
            ant = temp;

            System.out.print(f + " ");
        }
    }

    public static void vetorFaturamento(){
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/dados.json")));
            JSONArray jsonArray = new JSONArray(content);

            double menorValor = Double.MAX_VALUE;
            double maiorValor = Double.MIN_VALUE;
            double somaValores = 0.0;
            int diasComFaturamento = 0;

            List<Double> valores = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double valor = jsonObject.getDouble("valor");

                if (valor > 0) {
                    valores.add(valor);
                    somaValores += valor;
                    if (valor < menorValor) menorValor = valor;
                    if (valor > maiorValor) maiorValor = valor;
                }
            }

            double mediaMensal = somaValores / valores.size();

            for (double valor : valores) {
                if (valor > mediaMensal) diasComFaturamento++;
            }

            System.out.println("Menor valor de faturamento ocorrido em um dia do mês: " + menorValor);
            System.out.println("Maior valor de faturamento ocorrido em um dia do mês: " + maiorValor);
            System.out.println("Número de dias no mês em que o valor de faturamento diário foi superior à média mensal: " + diasComFaturamento);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void percentualRepresentativo(double [] valor){
        if (valor == null || valor.length == 0) {
            System.out.println("O array está vazio ou é nulo.");
            return;
        }
        double soma = 0;

        for (double v : valor) {
            soma += v;
        }
        if (soma == 0) {
            System.out.println("A soma dos valores é zero.");
            return;
        }
        for (double v : valor) {
            double percentual = (v / soma) * 100;
            System.out.printf("Percentual: %.2f%%\n", percentual);
        }
    }
    public static void inverterString(String palavra){
        String invertida = "";
        for(int i= palavra.length()-1; i>=0; i--){
            invertida += palavra.charAt(i);
        }
        System.out.println(invertida);
    }
}
