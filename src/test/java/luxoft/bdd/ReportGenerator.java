package luxoft.bdd;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator extends RunListener {

    @Override
    public void testRunFinished(Result result) throws Exception {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber/json/cucumber.json");

        Configuration configuration = new Configuration(reportOutputDirectory, "cucumber-jvm");
        configuration.setStatusFlags(true, false, true);
        configuration.setParallelTesting(false);
        configuration.setJenkinsBasePath("");
        configuration.setRunWithJenkins(false);
        configuration.setBuildNumber("1");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
