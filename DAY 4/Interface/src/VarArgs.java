public class VarArgs {

    // multiple inputs can be entered in the constructor no fix inputs....
    public void addsum(int ...sum){
        int nums =0 ;
        for(int i : sum){
            nums = nums +i;
        }
        System.out.println(nums);
    }
}
