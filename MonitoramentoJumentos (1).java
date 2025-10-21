import java.util.Scanner;
import java.text.DecimalFormat;

//Nome Completo: [GUSTAVO MICHAEL ALVES PEREIRA]
 //Matrícula: [4251920199]
 //Projeto S.O.S. Jumento Nordestino_Uma Solução Modular
 //Arquivo: MonitoramentoJumentos.java
public class MonitoramentoJumentos
{

    private static final int POPULACAO_INICIAL_REF = 1400000;
    private static final int POPULACAO_ATUAL_ESTIMADA = 84000;
    private static final int ABATES_ANUAIS_SIMULADOS = 50000;

    public static double calcularTaxaReducao(int populacaoInicial, int populacaoAtual) {
    
        return (1.0 - ((double) populacaoAtual / populacaoInicial)) * 100.0;
    }

    public static boolean estaEmRiscoCritico(double taxaReducao) {
        
        return taxaReducao >= 90.0;
    }

    public static int simularImpactoAbate(int populacaoAtual, int abatesAnuais, int anosSimulados) {
        
        return populacaoAtual - (abatesAnuais * anosSimulados);
    }

    public static void exibirDeclaracaoFinal(int populacaoRestante, int anos) {
        System.out.println("\n--- RESULTADO DA SIMULAÇÃO ---");
        System.out.println("População restante após " + anos + " anos: " + populacaoRestante + " jumentos.");

        if (populacaoRestante <= 0) {
            System.out.println("\n*** ALERTA EXTREMO! A EXTINÇÃO PODE TER SIDO ALCANÇADA NESTE PERÍODO SIMULADO. ***");
            System.out.println("Ação imediata e drástica é necessária.");
        } else {
            System.out.println("\nAinda há esperança! Iniciativas de conservação são vitais.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("=== S.O.S. JUMENTO NORDESTINO - SISTEMA DE MONITORAMENTO ===");
        System.out.println("População inicial de referência: " + POPULACAO_INICIAL_REF);
        System.out.println("População atual estimada: " + POPULACAO_ATUAL_ESTIMADA);
        System.out.println("Abates anuais (simulados): " + ABATES_ANUAIS_SIMULADOS);
        System.out.println();

        double taxaReducao = calcularTaxaReducao(POPULACAO_INICIAL_REF, POPULACAO_ATUAL_ESTIMADA);
        System.out.println("[Análise Inicial] Taxa de Redução Histórica: " + df.format(taxaReducao) + "%");

        boolean riscoCritico = estaEmRiscoCritico(taxaReducao);

        if (riscoCritico) {
            System.out.println("[Status] Espécie em Risco CRÍTICO (Redução > 90%)!");
        } else {
            System.out.println("[Status] Espécie em Alto Risco (Redução < 90%).");
        }
        System.out.println();

        System.out.print("Quantos anos futuros você deseja simular o impacto dos abates (Ex: 1, 3, 5)? ");
        int anosSimulados;
        try {
            anosSimulados = scanner.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, digite um número inteiro.");
            scanner.close();
            return;
        }

        int populacaoRestante = simularImpactoAbate(
            POPULACAO_ATUAL_ESTIMADA,
            ABATES_ANUAIS_SIMULADOS,
            anosSimulados
        );

        exibirDeclaracaoFinal(populacaoRestante, anosSimulados);

        System.out.println("\nPrograma de Monitoramento Finalizado.");
        scanner.close();
    }
}