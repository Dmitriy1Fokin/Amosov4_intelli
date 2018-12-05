import java.util.ArrayList;

public class Task4
{
    private static ArrayList<String> arrOfSolutions;
//    private static Criterion listOfCriterion;

    public static void main(String[] args)
    {
        arrOfSolutions = new ArrayList<>();
        arrOfSolutions.add("Kia rio");
        arrOfSolutions.add("Renault Logan");
        arrOfSolutions.add("Hyundai Solaris");
        arrOfSolutions.add("Ford Focus");
        arrOfSolutions.add("Nissan Almera");
        arrOfSolutions.add("Volkswagen Polo");
        arrOfSolutions.add("Skoda Rapid");
        showSolutions();

//        listOfCriterion = new Criterion();
//        listOfCriterion.add("Стоимость авто", (float)0.2);
//        listOfCriterion.add("Клиренс", (float)0.05);
//        listOfCriterion.add("Стоимость обслуживания", (float)0.10);
//        listOfCriterion.add("Надежность", (float)0.15);
//        listOfCriterion.add("Безопасность", (float)0.2);
//        listOfCriterion.add("Комплектация", (float)0.1);
//        listOfCriterion.add("Ликвидность", (float)0.1);
//        listOfCriterion.add("Расход топлива", (float)0.1);
//
//        listOfCriterion.show();

        int [] costAuto = {-723, -561, -627, -965, -572, -499, -772};
        int [] klirens = {15, 18, 17, 16, 18, 17, 15};
        int [] costService = {1, 2, 1, 0, 1, 0, 1};
        int [] reliability = {1, 0, 1, 2, 1, 1, 1};
        int [] safety = {1, 0, 1, 2, 0, 1, 1};
        int [] equipment = {2, 0, 1, 2, 1, 1, 2};
        int [] liquidity = {1, 1, 1, 1, 0, 1, 1};
        int [] fuelConsumption = {1, 1, 1, 0, 0, 1, 1};

        ArrayList<CriterionTest> arrOfCriterion = new ArrayList<>();
        arrOfCriterion.add(new CriterionTest("Стоимость авто", (float)0.2, costAuto));
        arrOfCriterion.add(new CriterionTest("Клиренс", (float)0.05, klirens));
        arrOfCriterion.add(new CriterionTest("Стоимость обслуживания", (float)0.10, costService));
        arrOfCriterion.add(new CriterionTest("Надежность", (float)0.15, reliability));
        arrOfCriterion.add(new CriterionTest("Безопасность", (float)0.2, safety));
        arrOfCriterion.add(new CriterionTest("Комплектация", (float)0.1, equipment));
        arrOfCriterion.add(new CriterionTest("Ликвидность", (float)0.1, liquidity));
        arrOfCriterion.add(new CriterionTest("Расход топлива", (float)0.1, fuelConsumption));

        CreateBO(arrOfSolutions, arrOfCriterion);

//        ArrayList<int[]> arrOfPriority = new ArrayList<>();
//        arrOfPriority.add(costAuto);
//        arrOfPriority.add(klirens);
//        arrOfPriority.add(costService);
//        arrOfPriority.add(reliability);
//        arrOfPriority.add(safety);
//        arrOfPriority.add(equipment);
//        arrOfPriority.add(liquidity);
//        arrOfPriority.add(fuelConsumption);




//        CreateBO(listOfCriterion, arrOfSolutions, arrOfPriority);

    }

    private static void showSolutions()
    {
        int i = 0;
        for (String str : arrOfSolutions)
        {
            System.out.println(++i + ".\t" + str);
        }
    }

//    private static void CreateBO(Criterion criterion, ArrayList<String> solutions, ArrayList<int[]> arrOfPriority)
//    {
//        ArrayList<Byte[][]> arrOfBO = new ArrayList<>();
//        byte[][] bo = new byte[solutions.size()][solutions.size()];
//        for(int i = 0; i < criterion.getCount(); i++)
//        {
//
//        }
//
//        System.out.println(criterion.getCount());
//    }

    private static void CreateBO(ArrayList<String> solutions, ArrayList<CriterionTest> arrOfCriterion)
    {
        ArrayList<byte[][]> arrOfBO = new ArrayList<>();
        byte[][] bo = new byte[solutions.size()][solutions.size()];/////////////////////////////////

        for(CriterionTest crit : arrOfCriterion)
        {

            for(int i = 0; i < solutions.size(); i++)
                for(int j = 0; j < solutions.size(); j++)
                    if(crit.getArrOfPriority()[i] > crit.getArrOfPriority()[j])
                        bo[i][j] = 1;
                    else
                        bo[i][j] = 0;

             arrOfBO.add(bo);/////////////////////////////////////////
        }

        for(byte[][] printBO : arrOfBO)
        {
            for(int i = 0; i < solutions.size(); i++)
            {
                for (int j = 0; j < solutions.size(); j++)
                    System.out.print(printBO[i][j] + "\t");
                System.out.println();
            }
            System.out.println();
        }
    }
}
