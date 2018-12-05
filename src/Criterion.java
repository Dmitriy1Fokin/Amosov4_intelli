import java.util.HashMap;
import java.util.Map;

public class Criterion
{
    private float sumOfCriteriaWeight;
    private Map<String, Float> mapCriteria;

    public Criterion()
    {
        sumOfCriteriaWeight = 0;
        mapCriteria = new HashMap<>();
    }
    public void add(String nameCriteria, float criterionWeight)
    {
        if((sumOfCriteriaWeight + criterionWeight) > 1)
            System.out.println("Total weight of all critetion can not be more then 1!!!");
        else
        {
            mapCriteria.put(nameCriteria, criterionWeight);
            sumOfCriteriaWeight += criterionWeight;
        }
    }

    public void delete(String nameCriteria)
    {
        sumOfCriteriaWeight -= mapCriteria.get(nameCriteria);
        mapCriteria.remove(nameCriteria);
    }

    public void show()
    {
        System.out.println("Critetrion:");
        //mapCriteria.forEach((k, v) -> System.out.println(k + "\t" + v));
        int i = 0;
        for(Map.Entry<String, Float> entry : mapCriteria.entrySet())
        {
            //i ++;
            String key = entry.getKey();
            float value = entry.getValue();
            System.out.println(++i + ".\t " + key + " = " + value);
        }

    }

    public int getCount()
    {
        return mapCriteria.size();
    }
}
