import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Task4
{
    private static ArrayList<String> arrOfSolutions;
    private static ArrayList<Criterion> arrOfCriterion;
    private static ArrayList<byte[][]> arrOfBO;
    private static FileWriter writer;

    public static void main(String[] args) throws IOException
    {
        writer = new FileWriter("Output");

        arrOfSolutions = new ArrayList<>();
        arrOfSolutions.add("Kia rio");
        arrOfSolutions.add("Renault Logan");
        arrOfSolutions.add("Hyundai Solaris");
        arrOfSolutions.add("Ford Focus");
        arrOfSolutions.add("Nissan Almera");
        arrOfSolutions.add("Volkswagen Polo");
        arrOfSolutions.add("Skoda Rapid");

        String text = "Возможные решения:\n";
        writer.write(text);
        ShowSolutions(arrOfSolutions);
        writer.write("\n");

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

        text = "Критерии:\n";
        writer.write(text);
        ShowCriterion(arrOfCriterion);
        writer.write("\n");

        arrOfBO = new ArrayList<>();
        arrOfBO = CreateBO(arrOfSolutions, arrOfCriterion);

        text = "Бинарные отношения для каждого критерия значимости:\n";
        writer.write(text);
        ShowBO(arrOfBO);
        writer.write("\n");

        text = "Механизм доминирования:\n";
        writer.write(text);
        int[] sumOfDominence;
        sumOfDominence = MechanismDominance(arrOfBO);
        writer.write("\n");

        text = "Общее решение по механизму доминирования:\n";
        writer.write(text);
        ShowSumOFDominanceLockng(sumOfDominence);
        writer.write("\n");

        text = "Наилучший вариант:\n";
        writer.write(text);
        BestDominanceLocking(sumOfDominence);
        writer.write("\n");

        text = "Механизм блокировок:\n";
        writer.write(text);
        int[] sumOfLocking;
        sumOfLocking = MechanismLocking(arrOfBO);
        writer.write("\n");

        text = "Общее решение по механизму блокировки:\n";
        writer.write(text);
        ShowSumOFDominanceLockng(sumOfLocking);
        writer.write("\n");

        text = "Наилучший вариант:\n";
        writer.write(text);
        BestDominanceLocking(sumOfLocking);
        writer.write("\n");

        text = "Механизм турнирный:\n";
        writer.write(text);
        double[] sumOfTournament = MechanismTournament(arrOfBO);
        writer.write("\n");

        text = "Общее решение по турнирному механизму:\n";
        writer.write(text);
        int index = 0;
        for(String str : arrOfSolutions)
        {
            text = str + "- " + sumOfTournament[index++] + "\n";
            writer.write(text);
        }
        writer.write("\n");

        text = "Наилучший вариант:\n";
        writer.write(text);
        int maxTour = 0;
        for(int i = 0; i < sumOfTournament.length; i++)
            if(sumOfTournament[i] > maxTour)
                maxTour = i;
        text = maxTour + ". " + arrOfSolutions.get(maxTour) + " - " + sumOfTournament[maxTour] + "\n";
        writer.write(text);
        writer.write("\n");

        text = "Оптимальность:\n";
        writer.write(text);
        Optimal(arrOfBO);

        writer.close();

    }

    private static void ShowSolutions(ArrayList<String> arr) throws IOException
    {
        int i = 0;
        for (String str : arr)
        {
            String text = ++i + ".\t" + str + '\n';
            writer.write(text);
        }
    }

    private static void ShowCriterion(ArrayList<Criterion> arr) throws IOException
    {
        int i = 0;
        for(Criterion crit : arr)
        {
            String text = ++i + ".\t" + crit.getName() + "\n";
            writer.write(text);
        }

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

    private static void ShowBO(ArrayList<byte[][]> arr) throws IOException
    {
       int index = 0;
       String text;

       for(byte[][] printBO : arr)
       {
           text = arrOfCriterion.get(index++).getName() + ":\n";
           writer.write(text);
           for(int i = 0; i < arrOfSolutions.size(); i++)
           {
               for (int j = 0; j < arrOfSolutions.size(); j++)
               {
                   text = printBO[i][j] + "\t";
                   writer.write(text);
               }
               writer.write("\n");
           }
       }
    }

    private  static int[] MechanismDominance(ArrayList<byte[][]> arrBO) throws IOException
    {
        int count;
        int index = 0;
        int[] sum = new int[arrOfSolutions.size()];
        String text;

        for(byte[][] arr : arrBO)
        {
            text = ++index + ". " + arrOfCriterion.get(index-1).getName() + ": ";
            writer.write(text);
            for(int i = 0; i < arrOfSolutions.size(); i++)
            {
                count = 0;
                for (int j = 0; j < arrOfSolutions.size(); j++)
                    if ((i != j) && (arr[i][j] == 1))
                        count++;

                if(count == arrOfSolutions.size() - 1)
                    {
                        text = i + 1 + " ";
                        writer.write(text);
                        sum[i] ++;
                    }
            }
            writer.write("\n");
        }
        return sum;
    }

    private static void ShowSumOFDominanceLockng(int[] arr) throws IOException
    {
        int i = 0;
        String  text;

        for(String str : arrOfSolutions)
        {
            text = ++i + ". " + str + ": " + arr[i-1] + "\n";
            writer.write(text);
        }
    }

    private static void BestDominanceLocking(int[] arr) throws IOException
    {
        int max = 0;
        String text;

        for(int i = 0; i < arrOfSolutions.size(); i++)
        {
            if(max < arr[i])
                max = arr[i];
        }
        for(int i = 0; i < arrOfSolutions.size(); i++)
        {
            if(arr[i] == max)
            {
                text = i + 1 + ". " + arrOfSolutions.get(i) + " - " + max + "\n";
                writer.write(text);
            }
        }
    }

    private static int[] MechanismLocking(ArrayList<byte[][]> arrBO) throws IOException
    {
        int count;
        int index = 0;
        int[] sum = new int[arrOfSolutions.size()];
        String text;

        for(byte[][] arr : arrBO)
        {
            text = ++index + ". " + arrOfCriterion.get(index - 1).getName() + ": ";
            writer.write(text);
            for (int j = 0; j < arrOfSolutions.size(); j++)
            {
                count = 0;
                for (int i = 0; i < arrOfSolutions.size(); i++)
                    if ((j != i) && (arr[i][j] == 1))
                        count++;

                if (count == arrOfSolutions.size() - 1)
                {
                    text =  j + 1 + " ";
                    writer.write(text);
                    sum[j]++;
                }
            }
            writer.write("\n");
        }
        return sum;
    }

    private static double[] MechanismTournament(ArrayList<byte[][]> arrBO) throws IOException
    {
        double[] sum = new double[arrOfSolutions.size()];
        double[] totalWeight = new double[arrOfSolutions.size()];
        int index = 0;
        String text;
        //ArrayList<String> arrOfSolutionsSorted = new ArrayList<>(arrOfSolutions);
        //double[] sumSorted = new double[arrOfSolutions.size()];

        for(byte[][] arr : arrBO)
        {
            text = ++index + ". " + arrOfCriterion.get(index - 1).getName() + ": \n";
            writer.write(text);
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
                text = i+1 + ". " + arrOfSolutions.get(i) + " - " + sum[i] + "\n";
                writer.write(text);
                totalWeight[i] += sum[i];
                //System.out.println(totalWeight[i]);
            }
            writer.write("\n");
        }

//        sumSorted = totalWeight;
//        for(int i = 0; i < arrOfSolutions.size(); i++)
//        {
//            for(int j = 0; j < arrOfSolutions.size(); j++)
//            {
//                if(sumSorted[i] < sumSorted[j])
//                {
//                    double temp = sumSorted[i];
//                    sumSorted[i] = sumSorted[j];
//                    sumSorted[j] = temp;
//                    String str = arrOfSolutionsSorted.get(i);
//                    arrOfSolutionsSorted.set(i, arrOfSolutionsSorted.get(j));
//                    arrOfSolutionsSorted.set(j, str);
//                }
//            }
//        }
//
//
//        for(int i = 0; i < arrOfSolutionsSorted.size(); i++)
//        {
//            System.out.println(arrOfSolutionsSorted.get(i) + " - " + sumSorted[i]);
//        }
        return totalWeight;
    }

    private static void Optimal(ArrayList<byte[][]> arrBO) throws  IOException
    {
        int count;
        int index = 0;
        String text;

        text = "Максимальная альтернатива:\n";
        writer.write(text);
        for(byte[][] arr : arrBO)
        {
            text = ++index + ". " + arrOfCriterion.get(index - 1).getName() + ": \n";
            writer.write(text);
            for (int i = 0; i < arrOfSolutions.size(); i++)
            {
                count = 0;
                for (int j = 0; j < arrOfSolutions.size(); j++)
                {
                    if (arr[i][j] == 1)
                        count += 1;
                    if(arr[i][j] == 0 && arr[j][i] == 0)
                        count += 1;
                }

                if (count == arrOfSolutions.size())
                {
                    text = i + 1 + ". " + arrOfSolutions.get(i) + "\n";
                    writer.write(text);
                }
            }
            writer.write("\n");
        }

        index = 0;
        text = "Строго максимальная альтернатива:\n";
        writer.write(text);
        for(byte[][] arr : arrBO)
        {
            text = ++index + ". " + arrOfCriterion.get(index - 1).getName() + ": \n";
            writer.write(text);
            for (int i = 0; i < arrOfSolutions.size(); i++)
            {
                count = 0;
                for (int j = 0; j < arrOfSolutions.size(); j++)
                {
                    if ((arr[i][j] == 1 && arr[j][i] == 0) || i == j)
                        count += 1;
                    if(arr[i][j] == 0 && arr[j][i] == 0)
                        count += 1;
                }

                if (count == arrOfSolutions.size())
                {
                    text = i + 1 + ". " + arrOfSolutions.get(i) + "\n";
                    writer.write(text);
                }
            }
            writer.write("\n");
        }

        index = 0;
        text = "Наибольшая альтернатива:\n";
        writer.write(text);
        for(byte[][] arr : arrBO)
        {
            text = ++index + ". " + arrOfCriterion.get(index - 1).getName() + ": \n";
            writer.write(text);
            for (int i = 0; i < arrOfSolutions.size(); i++)
            {
                count = 0;
                for (int j = 0; j < arrOfSolutions.size(); j++)
                {
                    if (arr[i][j] == 1)
                        count += 1;
                }

                if (count == arrOfSolutions.size())
                {
                    text = i + 1 + ". " + arrOfSolutions.get(i) + "\n";
                    writer.write(text);
                }
            }
            writer.write("\n");
        }

        index = 0;
        text = "Строго наибольшая альтернатива:\n";
        writer.write(text);
        for(byte[][] arr : arrBO)
        {
            text = ++index + ". " + arrOfCriterion.get(index - 1).getName() + ": \n";
            writer.write(text);
            for (int i = 0; i < arrOfSolutions.size(); i++)
            {
                count = 0;
                for (int j = 0; j < arrOfSolutions.size(); j++)
                {
                    if ((arr[i][j] == 1 && arr[j][i] == 0) || i == j)
                        count += 1;
                }

                if (count == arrOfSolutions.size())
                {
                    text = i+1 + ". " + arrOfSolutions.get(i) + "\n";
                    writer.write(text);
                }
            }
            writer.write("\n");
        }
    }
}
