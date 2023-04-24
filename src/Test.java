public class Test {
    public static void main(String [] args){

        
        State test = new State();
        System.out.println(test);
        int[] tester = test.pop();
        test.pop();
        for(int i = 0; i < 10; i++){
            System.out.print(tester[i] + " ");
        }

        //System.out.println(test);
        //test.append();
        System.out.println(test);
        test.remove(1);
        System.out.println(test);
    }

}
