package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/project.properties"
})
public interface ProjectConfig extends Config {

    @Key("browserStack.project")
    String getProjectName();

    @Key("browserStack.build")
    String getBuildName();

    @Key("browserStack.testName")
    String getTestName();
}