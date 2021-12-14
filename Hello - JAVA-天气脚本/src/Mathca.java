import java.util.List;

public class Mathca {
    public List<String> generateParenthesis(int n) {
        _generate(0,0,n,"");
        return null;
    }
    private void _generate(int left,int right, int n, String s) {
        //terminator
        if(left==n && right == n) {
            System.out.println(s);
            return;
        }
        //process
        String s1=s+"(";
        String s2=s+")";
        //drill down
        if(left<n) {
            _generate(left + 1, right,n, s1);
        }
        if(left>right) {
            _generate(left,right + 1, n, s2);
        }
    }

    public static void main(String[] args) {
        aries a=new aries();
        a.output();
        // TODO We need to calculate

        Mathca sol=new Mathca();
        System.out.println(sol.generateParenthesis(3));
        System.out.println("Hello World");


    }
}