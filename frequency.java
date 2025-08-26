public class frequency {
    public static void main(String[] args){

        int count0 = 0;
        int count1= 0;
        int count4 = 0;
        int count7 = 0;
        int count2 = 0;
        int count3 = 0;
        int count5 = 0;
        int count6 = 0;
        int count8 = 0;
        int count9 = 0;

        int[] array1 = {2,5,6,3,2,5,6,3,2,6};
        for(int i = 0;i<=9;i++){
            if(array1[i]==2){
                count2++;
            }if(array1[i]==3){
                count3++;
            }if(array1[i]==5){
                count5++;
            }if(array1[i]==6){
                count6++;
            }if(array1[i]==0){
                count0++;
            }if(array1[i]==1){
                count1++;
            }if(array1[i]==4){
                count4++;
            }if(array1[i]==7){
                count7++;
            }if(array1[i]==8){
                count8++;
            }if(array1[i]==9){
                count9++;
            }
        }
        System.out.println("Number of 2 : "+count2);
        System.out.println("Number of 3 : "+count3);
        System.out.println("Number of 5 : "+count5);
        System.out.println("Number of 0 : "+count0);
        System.out.println("Number of 1 : "+count1);
        System.out.println("Number of 4 : "+count4);
        System.out.println("Number of 7 : "+count7);
        System.out.println("Number of 6 : "+count6);
        System.out.println("Number of 8 : "+count8);
        System.out.println("Number of 9 : "+count9);
    }
}