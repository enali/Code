package example.extralib;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.internal.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JCommanderExample {
    @Parameter
    public List<String> parameters = Lists.newArrayList();

    @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
    public int verbose = 1;

    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    public String groups;

    @Parameter(names = "-debug", description = "Debug mode")
    public boolean debug = false;

    @DynamicParameter(names = "-D", description = "Dynamic parameters go here")
    public Map<String, String> dynamicParams = new HashMap<>();

    public static void main(String[] args) {
        JCommanderExample jct = new JCommanderExample();
        String[] argv = {"-log", "2", "-groups", "unit1,unit2,unit3","-debug", "-Doption=value", "a", "b", "c"};
        JCommander.newBuilder().addObject(jct).build().parse(argv);

        System.out.println(jct.verbose);
    }
}
