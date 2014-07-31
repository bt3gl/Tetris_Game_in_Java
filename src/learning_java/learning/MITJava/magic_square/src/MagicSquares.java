import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MagicSquares {

    public static void main(String[] args) throws IOException {
        String line = null;
        String[] tokens = null;
        int line_sum = 0;
        int constant = 0;
        boolean mSquare = true;
        ArrayList<String> files = new ArrayList<String>();
       

        // path names are absolute
        try {
            files.add("/home/bytegirl/workspace/magic_square/src/Luna.txt");
            files.add("/home/bytegirl/workspace/magic_square/src/Mercury.txt");

            // for each file
            for (String paths : files) {
                
                // open file
                FileReader fr = new FileReader(paths);
                BufferedReader br = new BufferedReader(fr);


                // While the file has more lines
                while ((line = br.readLine()) != null) {
                    // handle empty lines
                    if (line.isEmpty()) {
                        continue;
                    }

                    line_sum = 0;

                    // Convert line to tokens
                    tokens = line.split("\t");

                    // sum the lines
                    for (String token : tokens) {
                        line_sum += Integer.valueOf(token);
                    }

                    // compare constant to line_sum and see if they match
                    if (constant == 0) {
                        constant = line_sum;
                    } else {
                        if (constant != line_sum) {
                            mSquare = false;
                        } else {
                            continue;
                        }
                    }


                }
                br.close();
                if (mSquare) {
                    System.out.println("Success: " + paths + " is a Magic Square");
                } else {
                    System.out.println("Failure: " + paths + " is not a Magic Square");
                }
            
            
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}