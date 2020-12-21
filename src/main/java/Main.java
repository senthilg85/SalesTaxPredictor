import com.salestax.utils.Utilities;

import java.io.IOException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> fileNames = Utilities.getFileNames(args);
        Utilities.processData(fileNames);
    }

}
