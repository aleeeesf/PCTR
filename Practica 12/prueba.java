public class prueba {
    
    public static void main(String[] args)
    {
        int aux[][];

        aux = new int[][]{{1,2,3},{1,2,3},{1,2,3}};

        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.println(aux[i][j]);
            }
            System.out.println("\n");
        }
    }
}
