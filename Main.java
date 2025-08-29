public class Main {
    public static void main(String[] args){
        int k = 5;
        int l=0;
        for(int i=0;i<=5;i+=1){
            String str = "";
            for(int j=0;j<(k-i);j++){
                str+=" ";
            }
            for(int j=0;j<2*l;j++){
                str+="@";
            }
            l+=1;
            str+="@";
            System.out.println("-------------------");
            
            System.out.println(str);
            
        }
    }
}
