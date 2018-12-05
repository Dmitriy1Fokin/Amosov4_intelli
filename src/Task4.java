import java.util.ArrayList;

public class Task4
{
    private static ArrayList<String> arrOfSolutions;
    private static Criterion listOfCriterion;

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

        listOfCriterion = new Criterion();
        listOfCriterion.add("Стоимость авто", (float)0.2);
        listOfCriterion.add("Клиренс", (float)0.05);
        listOfCriterion.add("Стоимость обслуживания", (float)0.10);
        listOfCriterion.add("Надежность", (float)0.15);
        listOfCriterion.add("Безопасность", (float)0.2);
        listOfCriterion.add("Комплектация", (float)0.1);
        listOfCriterion.add("Ликвидность", (float)0.1);
        listOfCriterion.add("Расход топлива", (float)0.1);

        listOfCriterion.show();

        int [] costAuto = {-723, -561, -627, -965, -572, -499, -772};
        int [] klirens = {15, 18, 17, 16, 18, 17, 15};
        int [] costService = {1, 2, 1, 0, 1, 0, 1};
        int [] reliability = {1, 0, 1, 2, 1, 1, 1};
        int [] safety = {1, 0, 1, 2, 0, 1, 1};
        int [] equipment = {2, 0, 1, 2, 1, 1, 2};
        int [] liquidity = {1, 1, 1, 1, 0, 1, 1};
        int [] fuelConsumption = {1, 1, 1, 0, 0, 1, 1};

        ArrayList<int[]> arrOfPriority = new ArrayList<>();
        arrOfPriority.add(costAuto);
        arrOfPriority.add(klirens);
        arrOfPriority.add(costService);
        arrOfPriority.add(reliability);
        arrOfPriority.add(safety);
        arrOfPriority.add(equipment);
        arrOfPriority.add(liquidity);
        arrOfPriority.add(fuelConsumption);




        CreateBO(listOfCriterion, arrOfSolutions, arrOfPriority);

    }

    private static void showSolutions()
    {
        int i = 0;
        for (String str : arrOfSolutions)
        {
            System.out.println(++i + ".\t" + str);
        }
    }

    private static void CreateBO(Criterion criterion, ArrayList<String> solutions, ArrayList<int[]> arrOfPriority)
    {
        ArrayList<Byte[][]> arrOfBO = new ArrayList<>();
        byte[][] bo = new byte[solutions.size()][solutions.size()];
        for(int i = 0; i < criterion.getCount(); i++)
        {

        }

        System.out.println(criterion.getCount());
    }
}
