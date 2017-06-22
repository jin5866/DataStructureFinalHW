/**
 * Created by user on 2017-06-06.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import  java.io.BufferedReader;
import  java.io.IOException;
import java.io.*;

public class HW5 {
    public  static  void main(String args[]) throws IOException
    {
        String ol = System.getProperty("line.separator");

        String[] inputArr = {"test01"};
        String[] searchArr = {"search01"};
        String[] outputArr = {"testout01"};

        for(int i = 0; i < inputArr.length; i++)
        {

            int insert = 0;
            int delete = 0;
            int miss = 0;
            RBTree rbTree = new RBTree();

            String path = HW5.class.getResource("").getPath();
            String inputPath = path + inputArr[i] + ".txt";
            String  searchPath = path + searchArr[i] + ".txt";
            String outputPath = path + outputArr[i] + ".txt";

            BufferedReader br = null;
            BufferedReader bsr = null;
            BufferedWriter bw = null;

            File file = new File(inputPath);
            FileReader fr = new FileReader(file);
            //int tmp = fr.read();

            File ofile = new File(outputPath);
            FileWriter fw = new FileWriter(ofile,false);
            bw = new BufferedWriter(fw);

            File sfile = new File(searchPath);
            FileReader fsr = new FileReader(sfile);
            bsr = new BufferedReader(fsr);


            try {
                br = new BufferedReader(fr);
            }
            catch (Exception e)
            {

            }

            // 트리 인서트
            while (true)
            {
                String g = "  ";
                String line;
                try {
                    line = br.readLine();
                    line = line.replaceAll(" ","");
                }
                catch (IOException e)
                {
                    line = null;
                }
                if(line == null)
                {
                    break;
                }

                int val = 0;

                try {
                    val = Integer.parseInt(line);
                }
                catch (Exception e)
                {

                }



                if(val > 0)
                {
                    rbTree.Insert(new Node(val));
                    insert++;
                }
                else if(val < 0)
                {
                    if(rbTree.Delete(-val))
                    {
                        delete++;
                    }
                    else
                    {
                        miss++;
                    }
                }
                else
                {
                    break;
                }

                //rbTree.tree_print(rbTree.root,0);
                //System.out.println();

            }

            List list = rbTree.toList();

            //찾기
            while (true)
            {
                String line;
                try {
                    line = bsr.readLine();
                    line = line.replaceAll(" ","");
                }
                catch (IOException e)
                {
                    line = null;
                }
                catch (Exception e)
                {
                    //System.out.write(e.getMessage());
                    line = null;
                }
                if(line == null)
                {
                    break;
                }

                int val = 0;

                try {
                    val = Integer.parseInt(line);
                }
                catch (Exception e)
                {

                }

                String[] result = list.searchONSorted(val);

                bw.write(result[0] + " " );
                bw.write(result[1] + " " );
                bw.write(result[2] + ol );



            }










            // 줄넘김 문자





            bw.close();
            br.close();
            bsr.close();

        }




    }
}
