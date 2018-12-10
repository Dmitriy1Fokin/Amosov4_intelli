import java.util.ArrayList;

public class Task4
{
    private static ArrayList<String> arrOfSolutions;
    private static ArrayList<Criterion> arrOfCriterion;
    private static ArrayList<byte[][]> arrOfBO;

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

        System.out.println("Возможные решения:");
        ShowSolutions(arrOfSolutions);
        System.out.println();

        int [] costAuto = {-723, -561, -627, -965, -572, -499, -772};
        int [] klirens = {15, 18, 17, 16, 18, 17, 15};
        int [] costService = {1, 2, 1, 0, 1, 0, 1};
        int [] reliability = {1, 0, 1, 2, 1, 1, 1};
        int [] safety = {1, 0, 1, 2, 0, 1, 1};
        int [] equipment = {2, 0, 1, 2, 1, 1, 2};
        int [] liquidity = {1, 1, 1, 1, 0, 1, 1};
        int [] fuelConsumption = {1, 1, 1, 0, 0, 1, 1};

        arrOfCriterion = new ArrayList<>();
        arrOfCriterion.add(new Criterion("Стоимость авто", 0.2, costAuto));
        arrOfCriterion.add(new Criterion("Клиренс", 0.05, klirens));
        arrOfCriterion.add(new Criterion("Стоимость обслуживания", 0.10, costService));
        arrOfCriterion.add(new Criterion("Надежность", 0.15, reliability));
        arrOfCriterion.add(new Criterion("Безопасность", 0.2, safety));
        arrOfCriterion.add(new Criterion("Комплектация", 0.1, equipment));
        arrOfCriterion.add(new Criterion("Ликвидность", 0.1, liquidity));
        arrOfCriterion.add(new Criterion("Расход топлива", 0.1, fuelConsumption));

        System.out.println("Критерии:");
        ShowCriterion(arrOfCriterion);
        System.out.println();

        arrOfBO = new ArrayList<>();
        arrOfBO = CreateBO(arrOfSolutions, arrOfCriterion);

        System.out.println("Бинарные отношения для каждого критерия значимости:");
        ShowBO(arrOfBO);
        System.out.println();

        System.out.println("Механизм доминирования:");
        int[] sumOfDominence;
        sumOfDominence = MechanismDominance(arrOfBO);
        System.out.println();

        System.out.println("Общее решение по механизму доминирования:");
        ShowSumOFDominanceLockng(sumOfDominence);
        System.out.println();

        System.out.println("Наилучший вариант:");
        BestDominanceLocking(sumOfDominence);
        System.out.println();

        System.out.println("Механизм блокировок:");
        int[] sumOfLocking;
        sumOfLocking = MechanismLocking(arrOfBO);
        System.out.println();

        System.out.println("Общее решение по механизму блокировки:");
        ShowSumOFDominanceLockng(sumOfLocking);
        System.out.println();

        System.out.println("Наилучший вариант:");
        BestDominanceLocking(sumOfLocking);
        System.out.println();

        System.out.println("Механизм турнирный:");
        double[] sumOfTournament = MechanismTournament(arrOfBO);
        System.out.println();

        System.out.println("Общее решение по турнирному механизму:");
        int index = 0;
        for(String str : arrOfSolutions)
        {
            System.out.println(str + "- " + sumOfTournament[index++]);
        }


    }

    private static void ShowSolutions(ArrayList<String> arr)
    {
        int i = 0;
        for (String str : arr)
            System.out.println(++i + ".\t" + str);
    }

    private static void ShowCriterion(ArrayList<Criterion> arr)
    {
        int i = 0;
        for(Criterion crit : arr)
            System.out.println(++i + ".\t" + crit.getName());

    }

    private static ArrayList<byte[][]> CreateBO(ArrayList<String> solutions, ArrayList<Criterion> arrOfCriterion)
    {
        ArrayList<byte[][]> arr = new ArrayList<>();
        int index = 0;

        for(Criterion crit : arrOfCriterion)
        {
            arr.add(new byte[solutions.size()][solutions.size()]);
            byte[][] bo = arr.get(index++);
            for(int i = 0; i < solutions.size(); i++)
                for(int j = 0; j < solutions.size(); j++)
                    if(crit.getArrOfPriority()[i] >= crit.getArrOfPriority()[j])
                        bo[i][j] = 1;
                    else
                        bo[i][j] = 0;
        }

        return  arr;
    }

    private static void ShowBO(ArrayList<byte[][]> arr)
    {
       int index = 0;
       for(byte[][] printBO : arr)
       {
           System.out.println(arrOfCriterion.get(index++).getName() + ":");
           for(int i = 0; i < arrOfSolutions.size(); i++)
           {
               for (int j = 0; j < arrOfSolutions.size(); j++)
                   System.out.print(printBO[i][j] + "\t");
               System.out.println();
           }
       }
    }

    private  static int[] MechanismDominance(ArrayList<byte[][]> arrBO)
    {
        int count;
        int index = 0;
        int[] sum = new int[arrOfSolutions.size()];

        for(byte[][] arr : arrBO)
        {
            System.out.print(++index + ". " + arrOfCriterion.get(index-1).getName() + ": ");
            for(int i = 0; i < arrOfSolutions.size(); i++)
            {
                count = 0;
                for (int j = 0; j < arrOfSolutions.size(); j++)
                    if ((i != j) && (arr[i][j] == 1))
                        count++;

                if(count == arrOfSolutions.size() - 1)
                    {
                        System.out.print(i + 1 + " ");
                        sum[i] ++;
                    }
            }
            System.out.println();
        }
        return sum;
    }

    private static void ShowSumOFDominanceLockng(int[] arr)
    {
        int i = 0;

        for(String str : arrOfSolutions)
        {
            System.out.println(++i + ". " + str + ": " + arr[i-1]);
        }
    }

    private static void BestDominanceLocking(int[] arr)
    {
        int max = 0;
        for(int i = 0; i < arrOfSolutions.size(); i++)
        {
            if(max < arr[i])
                max = arr[i];
        }
        for(int i = 0; i < arrOfSolutions.size(); i++)
        {
            if(arr[i] == max)
                System.out.println(i + 1 + ". " + arrOfSolutions.get(i) + " - " + max);
        }
    }

    private static int[] MechanismLocking(ArrayList<byte[][]> arrBO)
    {
        int count;
        int index = 0;
        int[] sum = new int[arrOfSolutions.size()];

        for(byte[][] arr : arrBO)
        {
            System.out.print(++index + ". " + arrOfCriterion.get(index - 1).getName() + ": ");
            for (int j = 0; j < arrOfSolutions.size(); j++) {
                count = 0;
                for (int i = 0; i < arrOfSolutions.size(); i++)
                    if ((j != i) && (arr[i][j] == 1))
                        count++;

                if (count == arrOfSolutions.size() - 1) {
                    System.out.print(j + 1 + " ");
                    sum[j]++;
                }
            }
            System.out.println();
        }
        return sum;
    }

    private static double[] MechanismTournament(ArrayList<byte[][]> arrBO)
    {
        double[] sum = new double[arrOfSolutions.size()];
        double[] totalWeight = new double[arrOfSolutions.size()];
        int index = 0;
        ArrayList<String> arrOfSolutionsSorted = new ArrayList<>(arrOfSolutions);
        double[] sumSorted = new double[arrOfSolutions.size()];

        for(byte[][] arr : arrBO)
        {
            System.out.println(++index + ". " + arrOfCriterion.get(index - 1).getName() + ": ");
            for(int i = 0; i < arrOfSolutions.size(); i++)
            {
                for (int j = 0; j < arrOfSolutions.size(); j++) {
                    if ((arr[i][j] == 1) && (i != j))
                    {
                        if (arr[j][i] == 0)
                            sum[i] += 1;
                        else
                            sum[i] += 0.5;
                    } else {
                        if (arr[j][i] == 0)
                            sum[i] += 0.5;
                    }

                }
                sum[i] = sum[i] * arrOfCriterion.get(index - 1).getCriteria();
                System.out.println(i+1 + ". " + arrOfSolutions.get(i) + " - " + sum[i]);
                totalWeight[i] += sum[i];
                //System.out.println(totalWeight[i]);
            }

            System.out.println();
        }

        sumSorted = totalWeight;
        for(int i = 0; i < arrOfSolutions.size(); i++)
        {
            for(int j = 0; j < arrOfSolutions.size(); j++)
            {
                if(sumSorted[i] < sumSorted[j])
                {
                    double temp = sumSorted[i];
                    sumSorted[i] = sumSorted[j];
                    sumSorted[j] = temp;
                    String str = arrOfSolutionsSorted.get(i);
                    arrOfSolutionsSorted.set(i, arrOfSolutionsSorted.get(j));
                    arrOfSolutionsSorted.set(j, str);
                }
            }
        }


        for(int i = 0; i < arrOfSolutionsSorted.size(); i++)
        {
            System.out.println(arrOfSolutionsSorted.get(i) + " - " + sumSorted[i]);
        }
        return totalWeight;
    }

}
