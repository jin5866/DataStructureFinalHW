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
        String[] inputArr = {"test01","test02","test03","test04","test05","test06"};
        String[] outputArr = {"testout01","testout02","testout03","testout04","testout05","testout06"};

        for(int i = 0; i < inputArr.length; i++)
        {

            int insert = 0;
            int delete = 0;
            int miss = 0;
            RBTree rbTree = new RBTree();

            String path = HW5.class.getResource("").getPath();
            String inputPath = path + inputArr[i] + ".txt";
            String outputPath = path + outputArr[i] + ".txt";

            BufferedReader br = null;
            BufferedWriter bw = null;

            File file = new File(inputPath);
            FileReader fr = new FileReader(file);
            //int tmp = fr.read();

            File ofile = new File(outputPath);
            FileWriter fw = new FileWriter(ofile,false);
            bw = new BufferedWriter(fw);


            try {
                br = new BufferedReader(fr);
            }
            catch (Exception e)
            {

            }


            //br = new BufferedReader(new FileReader("input.txt"));
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


            br.close();

            String ol = System.getProperty("line.separator");

            int total = rbTree.GetTotal();
            int nb = rbTree.GetBlackNode();
            int bh = rbTree.GetBlackHeight();

            bw.write("filename = " + inputArr[i] + ".txt" + ol);
            bw.write("total = " + total + ol);
            bw.write("insert = " + insert + ol);
            bw.write("delete = " + delete + ol);
            bw.write("miss = " + miss + ol);
            bw.write("nb = " + nb + ol);
            bw.write("bh = " + bh + ol);

            rbTree.InorderTraversal(bw);

             bw.close();


        }




    }
}
