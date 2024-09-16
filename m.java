import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static lib.BscUi.*;

public class m {

    public static void test422() {
        // Define data
        String month = "2024-09";
        List<Map<String, String>> li = List.of(
                Map.of("name", "nme111"),
                Map.of("name", "nme222")
        );
        Map<String, Object> data = new HashMap<>();
        data.put("yyyymm", month);
        data.put("list2024", li);
        data.put("foot", "====================");

        // Define file paths
        String prjdir = "/path/to/your/project";  // This variable is defined but not used
        String tmpltf = "./cfg/rpt_month_tmplt.md";

        // Read template file
        String template;
        try {
            template = Files.readString(Paths.get(tmpltf));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Render template

         var mkdwn2 = renderTemplate4vue(template, data);  // ????

        // Print results
        System.out.println(mkdwn2.toString());
        System.out.println(9999);
    }

    public static void main(String[] args) {
        test422();
    }
}
