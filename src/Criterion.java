public class Criterion
{
    private String name;
    private double criteria;
    private int[] arrOfPriority;

    public Criterion(String name, double criteria, int[] arrOfPriority)
    {
        this.name = name;
        this.criteria = criteria;
        this.arrOfPriority = arrOfPriority;
    }

    public String getName()
    {
        return name;
    }

    public double getCriteria()
    {
        return criteria;
    }

    public int[] getArrOfPriority()
    {
        return arrOfPriority;
    }

    public void changeValue(String name)
    {
        this.name = name;
    }

    public void changeValue(double criteria)
    {
        this.criteria = criteria;
    }

    public void changeValue(int[] arrOfPriority)
    {
        this.arrOfPriority = arrOfPriority;
    }
}
