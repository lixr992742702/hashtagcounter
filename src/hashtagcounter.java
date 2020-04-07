import com.fheap;
import com.node;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class hashtagcounter {
    public static void main(String[] args) throws IOException {
        // write your code here
        String inputFileName = args[0];
        //String inputFileName = "/Users/xiangruili/Desktop/agorithm/ADS_project/src/input_file.txt";
        List<String> lines = readInputFile(inputFileName);
        fheap fibHeap = new fheap();
        // Output file name
        String outputFile = args[1];
        //String outputFile = "/Users/xiangruili/Desktop/agorithm/ADS_project/src/output_file.txt";


        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try{


            for(String line:lines){
                if(line.charAt(0)=='#'){
                    String[] s=line.replace("#","").split(" ");
                    String hashtag=s[0];
                    int frequency=Integer.parseInt(s[1]);
                    fibHeap.insert(hashtag,frequency);
                }else{
                    int n = Integer.parseInt(line);
                    String[] hat=new String[n];
                    int[] fre=new int[n];
                    for(int i=0;i<n;i++){
                        hat[i]=fibHeap.getMax().gethashtag();
                        fre[i]=fibHeap.getMax().getFrequency();
                        fibHeap.removeMax();
                        //System.out.println("Max:"+hat[i]);
                        if(i!=n-1){
                            bufferedWriter.write(hat[i]+",");
                        }else{
                            bufferedWriter.write(hat[i]);
                        }
                    }
                    for(int i=0;i<n;i++){
                        fibHeap.insert(hat[i],fre[i]);
                    }

                    bufferedWriter.newLine();

                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    public static List<String> readInputFile(String FilePath) throws IOException{
        List<String> lines=new ArrayList<String>();
        BufferedReader input=null;
        try {
            input = new BufferedReader(new FileReader(FilePath));
            String inputline;
            while ((inputline = input.readLine()) != null) {
                if (!inputline.equals(new String("stop"))) {
                    lines.add(inputline);
                } else {
                    break;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if(input != null){
                input.close();
            }
        }

        return lines;
    }


}
