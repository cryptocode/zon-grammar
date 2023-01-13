import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import zon.zonLexer;
import zon.zonParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * To run the example, generate the zon parser with ANTLR and add the ANTLR runtime to the classpath
 */
public class ZonReader {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("build.zig.zon");

        zonLexer lexer = new zonLexer(new ANTLRInputStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        zonParser parser = new zonParser(tokens);
        zonParser.ConfigContext cnf = parser.config();
        List<zonParser.TagsContext> tags = cnf.tags();

        for (zonParser.TagsContext tag : tags)
        {
            if (tag.name() != null) {
                System.out.println("name = " + tag.name().Value().getText());
            }

            if (tag.version() != null) {
                System.out.println("version = " + tag.version().Value().getText());
            }

            if (tag.dependencies() != null) {
                for (zonParser.DependencyContext dep : tag.dependencies().dependency())
                {
                    if (dep.dependencyname() != null) {
                        System.out.println("dependency name: " + dep.dependencyname().getText());
                    }

                    if (dep.assignment() != null) {
                        for (zonParser.AssignmentContext assn : dep.assignment()) {
                            if (assn.Url() != null) {
                                System.out.println("   url  = " + assn.Url().getText());
                            }

                            if (assn.Hash() != null) {
                                System.out.println("   hash = " + assn.Hash().getText());
                            }
                        }
                    }
                }
            }
        }
    }
}
